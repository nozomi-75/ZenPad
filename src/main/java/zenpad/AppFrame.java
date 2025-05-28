package zenpad;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * AppFrame is the main application window that contains the tabbed pane and the file opener panel.
 * It initializes the GUI components and sets up the layout for the application.
 * The frame is created with a title and has a default close operation to exit the application.
 * 
 * @see TabManager
 * @see FileOpenerPanel
 * @param title The title of the application window.
 */

public class AppFrame extends JFrame {
    // Instance of Toolbar object for toolbar management
    private Toolbar toolbar;

    // Instance of TabManager object for external tab management logic
    private TabManager tabManager;

    // Instance of FileOpenerPanel object for sidebar management
    private FileOpenerPanel fileOpenerPanel;

    // Instance of CodeRunner object for code execution
    private CodeRunner codeRunner;

    // Instance of TextPanel object for bottom bar purposes
    private NotePanel notePanel;

    // Other generic UI components
    private JTabbedPane tabbedPane;
    private JSplitPane innerSplitPane;
    private JSplitPane outerSplitPane;
    private int lastSelectedTabIndex = -1;

    public AppFrame(String title) {
        super(title);
        initializeFrame();
        initializeComponents();
        setupInnerSplit();
        setupOuterSplit();
        layoutComponents();
        updateNotePanelVis();
        setAppIcon();
    }

    /**
     * Set the JFrame configuration.
     * @see AppFrame
     */
    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    /**
     * Initialize inner JFrame components.
     * Set their respective configuration if necessary.
     * @see NotePanel
     * @see TabManager
     * @see CodeRunner
     * @see FileOpenerPanel
     */
    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 2, 2, 5));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        notePanel = new NotePanel();
        notePanel.getNotePanel().setBorder(new EmptyBorder(2, 2, 5, 5));

        // Pass a callback to TabManager to update text panel visibility
        tabManager = new TabManager(tabbedPane, this::updateNotePanelVis);
        codeRunner = new CodeRunner();
        fileOpenerPanel = new FileOpenerPanel(tabManager, notePanel);

        addTabSwitchListener();
    }

    /**
     * Adds a ChangeListener to the tabbedPane to update the NotePanel
     * with the correct description when the selected tab changes.
     * This ensures the NotePanel always displays the description
     * corresponding to the currently active tab.
     */
    private void addTabSwitchListener() {
        tabbedPane.addChangeListener(e -> {
            // Save note edits for the previous tab
            if (lastSelectedTabIndex >= 0) {
                EditorTab prevTab = tabManager.getEditorTabAt(lastSelectedTabIndex);
                if (prevTab != null) {
                    prevTab.setNoteContent(notePanel.getText());
                }
            }
            // Update note area for the new tab
            updateNotePanelOnTabSwitch();
            lastSelectedTabIndex = tabbedPane.getSelectedIndex();
        });
    }

    /**
     * Updates the NotePanel with the description file associated with the currently selected tab.
     * If no description file is available, displays a default message.
     */
    private void updateNotePanelOnTabSwitch() {
        int idx = tabbedPane.getSelectedIndex();
        if (idx >= 0) {
            EditorTab editorTab = tabManager.getEditorTabAt(idx);
            if (editorTab != null) {
                String noteContent = editorTab.getNoteContent();
                if (noteContent != null) {
                    notePanel.setText(noteContent);
                } else {
                    String noteFile = editorTab.getNoteFile();
                    if (noteFile != null && !noteFile.isEmpty()) {
                        notePanel.loadTextFromResource(noteFile);
                    } else {
                        notePanel.setText("No description available.");
                    }
                }
            }
        }
    }

    /**
     * Set up inner vertical JSplit.
     * @see tabbedPane
     * @see EditorTab
     * @see NotePanel
     */
    private void setupInnerSplit() {
        notePanel.getNotePanel().setMinimumSize(new Dimension(100, 100));

        innerSplitPane = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tabbedPane,
            notePanel.getNotePanel()
        );
        // Set divider location to 85% of the height for the editor
        innerSplitPane.setDividerLocation(0.85);
        innerSplitPane.setResizeWeight(0.70);
        innerSplitPane.setDividerSize(8);
    }

    /**
     * Set up outer horizontal JSplit.
     * @see FileOpenerPanel
     * @see #setupInnerSplit()
     */
    private void setupOuterSplit() {
        fileOpenerPanel.getPanel().setMinimumSize(new Dimension(100, 100));
        tabbedPane.setMinimumSize(new Dimension(300, 100));

        outerSplitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            fileOpenerPanel.getPanel(),
            innerSplitPane
        );
        outerSplitPane.setDividerLocation(200);
        outerSplitPane.setDividerSize(8);
    }

    /**
     * Configure layout components within the JFrame.
     * @see Toolbar
     * @see #setupOuterSplit()
     */
    private void layoutComponents() {
        toolbar = new Toolbar(tabManager, codeRunner, notePanel, this);
        add(toolbar.getToolbar(), BorderLayout.NORTH);
        add(outerSplitPane, BorderLayout.CENTER);
    }

    /**
     * Handle dynamic presence of note area.
     * @see NotePanel
     * @see #setupInnerSplit()
     */
    public void updateNotePanelVis() {
        boolean wasCollapsed = (innerSplitPane.getBottomComponent() == null);
        int dividerLoc = innerSplitPane.getDividerLocation();

        if (tabManager.getOpenTabs().isEmpty()) {
            innerSplitPane.setBottomComponent(null);
            toolbar.setSaveNotesEnabled(false);
        } else {
            innerSplitPane.setBottomComponent(notePanel.getNotePanel());
            notePanel.updateTheme();
            toolbar.setSaveNotesEnabled(true);
            if (wasCollapsed) {
                innerSplitPane.setDividerLocation(0.70);
                return;
            }
        }

        innerSplitPane.setDividerLocation(dividerLoc);
    }

    private void setAppIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/48x48.png"));
        setIconImage(icon.getImage());
    }

    public void showWindow() {
        setVisible(true);
    }
}