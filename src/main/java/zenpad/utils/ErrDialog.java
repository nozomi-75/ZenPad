package zenpad.utils;

import javax.swing.JOptionPane;

public class ErrDialog {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
