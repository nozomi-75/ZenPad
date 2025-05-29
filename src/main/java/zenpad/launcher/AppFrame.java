package zenpad.launcher;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import zenpad.ComponentFactory;
import zenpad.LayoutManager;
import zenpad.NotePanelVisMan;
import zenpad.NoteSyncManager;
import zenpad.Toolbar;
import zenpad.runners.CodeRunner;
import zenpad.ui.FileOpenerPanel;
import zenpad.ui.NotePanel;
import zenpad.ui.TabManager;

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

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        tabbedPane = ComponentFactory.createTabbedPane();
        notePanel = ComponentFactory.createNotePanel();

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
            NoteSyncManager.syncNotesOnTabSwitch(
                lastSelectedTabIndex,
                tabManager,
                notePanel,
                tabbedPane
            );
            lastSelectedTabIndex = tabbedPane.getSelectedIndex();
        });
    }

    private void layoutComponents() {
        toolbar = new Toolbar(tabManager, codeRunner, notePanel, this);
        add(toolbar.getToolbar(), BorderLayout.NORTH);
        add(outerSplitPane, BorderLayout.CENTER);
    }

    private void setupInnerSplit() {
        innerSplitPane = LayoutManager.createInnerSplitPane(tabbedPane, notePanel);
    }

    private void setupOuterSplit() {
        outerSplitPane = LayoutManager.createOuterSplitPane(fileOpenerPanel.getPanel(), innerSplitPane);
    }

    public void updateNotePanelVis() {
        NotePanelVisMan.updateNotePanelVis(
            innerSplitPane,
            tabManager,
            notePanel,
            toolbar
        );
    }

    private void setAppIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/48x48.png"));
        setIconImage(icon.getImage());
    }

    public void showWindow() {
        setVisible(true);
    }
}