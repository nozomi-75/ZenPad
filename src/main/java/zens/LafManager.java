package zens;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.UIManager;

/**
 * LafManager is responsible for managing the Look and Feel (Laf) of the application.
 * It applies the FlatDarculaLaf theme to the UI components.
 * If the theme fails to initialize, it falls back to the system look and feel.
 * 
 * @see AppFrame
 */

public class LafManager {

    public static void applyLightLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            fallback();
        }
    }

    public static void applyDarkLaf() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            fallback();
        }
    }

    private static void fallback() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception fallbackException) {
            System.out.println("Fatal error: Could not initialize any Look and Feel.");
        }
    }
}