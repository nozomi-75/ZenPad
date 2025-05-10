import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Dimension;

public class AppFrame extends JFrame {
    // Instance of TabManager object for external tab management logic
    private TabManager tabManager;
    // UI components
    private JTabbedPane tabbedPane;
    private JToolBar toolBar;
    private JPanel sidePanel;

    public AppFrame(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600)); // Set the size of the window
        setLocationRelativeTo(null); // Center the window

        // UI components configuration
        setLayout(new BorderLayout());
        tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 0, 5, 5));
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        sidePanel = new JPanel();

        // Pass tabbedPane to TabManager
        tabManager = new TabManager(tabbedPane);

        // Adding components to the frame
        add(toolBar, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.WEST);
    }

    public void showWindow() {
        setVisible(true);
    }
}