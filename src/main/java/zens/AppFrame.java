package zens;

import javax.swing.JFrame;
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

    // Other UI components
    private JTabbedPane tabbedPane;

    public AppFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 0, 5, 5));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        // Pass tabbedPane to TabManager
        tabManager = new TabManager(tabbedPane);
        codeRunner = new CodeRunner();
        Toolbar toolbar = new Toolbar(tabManager, codeRunner);

        /* Create a side panel for the file opener
         * Pass tabManager to FOP
         * The FOP object will call the TabManager to open a new tab
         */
        
        fileOpenerPanel = new FileOpenerPanel(tabManager);

        // Adding components to the frame
        add(toolbar.getToolbar(), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(fileOpenerPanel.getPanel(), BorderLayout.WEST);

        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/48x48.png"));
        setIconImage(icon.getImage());
    }

    public void showWindow() {
        setVisible(true);
    }
}