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