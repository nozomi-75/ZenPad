package zens;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.UIManager;

/**
 * LafManager is responsible for managing the Look and Feel (Laf) of the application.
 * It applies the FlatDarculaLaf theme to the UI components.
 * If the theme fails to initialize, it falls back to the system look and feel.
 * 
 * @see AppFrame
 */

public class LafManager {
    public static void applyLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.out.println("Failed to initialize FlatLaf. Falling back to system look and feel.");
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception fallbackException) {
                System.out.println("Fatal error: Could not initialize any Look and Feel.");
            }
        }
    }
}