package zenpad;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Displays an "About" dialog with information about the application.
 * <p>
 * This method creates a modal dialog that shows the application's name, license,
 * and author information. The dialog is displayed when the "About" button is clicked.
 * </p>
 */
public class AboutDialog {
    public static void show(JFrame parent) {
        String message = "<html>"
            + "<strong>ZenPad</strong>"
            + "<p>Version 1.2-SNAPSHOT<br>"
            + "<p>&copy; 2025 Zens. Licensed under MIT.</p>"
            + "</html>";

        ImageIcon icon = new ImageIcon(AboutDialog.class.getResource("/icons/64x64.png"));
            
        JOptionPane.showMessageDialog(
            parent,
            message,
            "About",
            JOptionPane.INFORMATION_MESSAGE,
            icon
        );
    }
}
