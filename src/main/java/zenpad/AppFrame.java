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

    // Instance of TabManager object for external tab management logic
    private TabManager tabManager;

    // Instance of FileOpenerPanel object for sidebar management
    private FileOpenerPanel fileOpenerPanel;

    // Instance of CodeRunner object for code execution
    private CodeRunner codeRunner;

    // Instance of TextPanel object for bottom bar purposes
    private TextPanel textPanel;

    // Other generic UI components
    private JTabbedPane tabbedPane;
    private JSplitPane innerSplitPane;
    private JSplitPane outerSplitPane;

    public AppFrame(String title) {
        super(title);
        initializeFrame();
        initializeComponents();
        setupInnerSplit();
        updateTextPanelVisibility();
        setupOuterSplit();
        layoutComponents();
        setAppIcon();
    }

    private void initializeFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800,600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }

    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 2, 2, 5));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        textPanel = new TextPanel();
        textPanel.getTextPanel().setBorder(new EmptyBorder(2, 2, 5, 5));

        // Pass a callback to TabManager to update text panel visibility
        tabManager = new TabManager(tabbedPane, this::updateTextPanelVisibility);
        codeRunner = new CodeRunner();
        fileOpenerPanel = new FileOpenerPanel(tabManager);
    }

    private void setupInnerSplit() {
        textPanel.getTextPanel().setMinimumSize(new Dimension(100, 100));

        innerSplitPane = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tabbedPane,
            textPanel.getTextPanel()
        );
        // Set divider location to 85% of the height for the editor
        innerSplitPane.setDividerLocation(0.85);
        innerSplitPane.setResizeWeight(0.85);
        innerSplitPane.setDividerSize(8);
    }

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

    private void layoutComponents() {
        Toolbar toolbar = new Toolbar(tabManager, codeRunner);
        add(toolbar.getToolbar(), BorderLayout.NORTH);
        add(outerSplitPane, BorderLayout.CENTER);
    }

    public void updateTextPanelVisibility() {
        if (tabManager.getOpenTabs().isEmpty()) {
            innerSplitPane.setBottomComponent(null);
        } else {
            innerSplitPane.setBottomComponent(textPanel.getTextPanel());
        }
    }

    private void setAppIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/48x48.png"));
        setIconImage(icon.getImage());
    }

    public void showWindow() {
        setVisible(true);
    }
}