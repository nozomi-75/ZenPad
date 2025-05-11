/**
 * This is the main entry point for the application.
 * It initializes the GUI and sets up the main application frame.
 * The GUI is created on the Event Dispatch Thread (EDT) to ensure thread safety.
 * The main application frame is created using the AppFrame class.
 * @see AppFrame
 */

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        // Ensure that the GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create and display the GUI
            new AppFrame("Demonstration").showWindow();
        });
    }
}