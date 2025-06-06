package zenpad.misc.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import zenpad.misc.factory.ButtonFactory;

/**
 * Displays a welcome dialog for the ZenPad application on startup.
 * <p>
 * It includes a don't show checkbox that works
 * using the {@link java.util.prefs.Preferences} API.
 * </p>
 */
public class WelcomeDialog extends JDialog {
    private static final String PREF_KEY = "zenpad_welcome_dialog_shown";

    public static void showIfNeeded(Frame parent) {
        Preferences prefs = Preferences.userRoot().node("ZenPad");
        boolean skip = prefs.getBoolean(PREF_KEY, false);
        if (!skip) {
            new WelcomeDialog(parent, prefs).setVisible(true);
        }
    }

    private WelcomeDialog(Frame parent, Preferences prefs) {
        super(parent, "Welcome to ZenPad", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 250);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel messageLabel = new JLabel(
            "<html><div>" +
            "<b>Welcome to ZenPad!</b><br><br>" +
            "ZenPad is a beginner-friendly tool for exploring and learning code.<br>" +
            "It supports Java, C, and Python in an interactive environment.<br><br>" +
            "To get started, open a file from the explorer on the left.<br><br>" +
            "<b>Note:</b> To run C or Python, please ensure GCC and Python are installed." +
            "</div></html>"
        );
        messageLabel.setBorder(new EmptyBorder(10, 15, 10, 15));

        JCheckBox dontShowAgain = new JCheckBox("Don't show this message again");

        JButton okButton = ButtonFactory.createSizedButton("OK", () -> {
            if (dontShowAgain.isSelected()) {
                prefs.putBoolean(PREF_KEY, true);
            }
            dispose();
        }, new Dimension(60, 25));

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
        buttonPanel.add(dontShowAgain, BorderLayout.WEST);
        buttonPanel.add(okButton, BorderLayout.EAST);

        add(messageLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
