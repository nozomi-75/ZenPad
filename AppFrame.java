import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
        setSize(new Dimension(800, 600)); // Set the size of the window
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());
        
        // Create a tabbed pane for easy access to contents
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 0, 5, 5));

        // Create a tool bar for easy access to functions
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