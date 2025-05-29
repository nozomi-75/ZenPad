package zenpad.utils;

import com.formdev.flatlaf.FlatLightLaf;

import zenpad.core.AppFrame;
import zenpad.ui.EditorTab;
import zenpad.ui.NotePanel;
import zenpad.ui.TabManager;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import java.awt.Window;

/**
 * LafManager is responsible for managing the Look and Feel (Laf) of the application.
 * It applies the FlatDarculaLaf theme to the UI components.
 * If the theme fails to initialize, it falls back to the system look and feel.
 * 
 * @see AppFrame
 */

public class LafManager {
    private static boolean dark = false;

    public static void applyLightLaf() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            dark = false;
        } catch (Exception ex) {
            fallback();
        }
    }

    public static void applyDarkLaf() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            dark = true;
        } catch (Exception ex) {
            fallback();
        }
    }

    public static boolean isDark() {
        return dark;
    }

    private static void fallback() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception fallbackException) {
            System.out.println("Fatal error: Could not initialize any Look and Feel.");
        }
    }

    public static void toggleTheme(
            boolean darkMode,
            JToggleButton toggleButton,
            TabManager tabManager,
            NotePanel notePanel,
            JToolBar toolbar
    ) {
        if (toggleButton != null) {
            if (darkMode) {
                applyDarkLaf();
                toggleButton.setText("Light Mode");
            } else {
                applyLightLaf();
                toggleButton.setText("Dark Mode");
            }
        }
        // Batch UI updates
        javax.swing.SwingUtilities.invokeLater(() -> {
            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }
            for (EditorTab tab : tabManager.getOpenTabs()) {
                RTextHelper.applyRSyntaxTheme(tab.getCodeArea());
            }
            RTextHelper.applyRSyntaxTheme(notePanel.getTextArea());
            if (toolbar != null) {
                SwingUtilities.updateComponentTreeUI(toolbar.getTopLevelAncestor());
            }
        });
    }
}