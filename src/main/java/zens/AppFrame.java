package zens;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

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

    // Other UI components
    private JTabbedPane tabbedPane;
    private JToolBar toolBar;

    public AppFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 0, 5, 5));

        toolBar = new JToolBar();
        toolBar.setFloatable(false);

        // Pass tabbedPane to TabManager
        tabManager = new TabManager(tabbedPane);

        /* Create a side panel for the file opener
         * Pass tabManager to FOP
         * The FOP object will call the TabManager to open a new tab
         */
        
        fileOpenerPanel = new FileOpenerPanel(tabManager);

        // Adding components to the frame
        add(toolBar, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(fileOpenerPanel.getPanel(), BorderLayout.WEST);
    }

    public void showWindow() {
        setVisible(true);
    }
}