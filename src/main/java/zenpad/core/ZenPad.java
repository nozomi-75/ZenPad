package zenpad.core;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import zenpad.misc.dialog.WelcomeDialog;
import zenpad.misc.manager.LafManager;

/**
 * This is the main entry point for the application.
 * It initializes the GUI and sets up the main application frame.
 * The GUI is created on the Event Dispatch Thread (EDT) to ensure thread safety.
 * The main application frame is created using the AppFrame class.
 * @see AppFrame
 */

public class ZenPad {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LafManager.applyLightLaf();
            JFrame appFrame = new AppFrame("Zenpad");
            appFrame.setVisible(true);
            WelcomeDialog.showIfNeeded(appFrame);
        });
    }
}