package zens;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * Toolbar is responsible for creating a toolbar with buttons for various actions.
 * It initializes a JToolBar and adds buttons for copy, run, and about functionalities.
 * The toolbar is designed to be non-floatable and has a specific border layout.
 * 
 * @see TabManager
 * @see CodeRunner
 * @param tabManager The TabManager instance used to manage tabs in the application.
 * @param codeRunner The CodeRunner instance used to execute code.
 */
public class Toolbar {
    private JToolBar toolbar;
    private TabManager tabManager;
    private CodeRunner codeRunner;
    
    public Toolbar(TabManager tabManager, CodeRunner codeRunner) {
        this.tabManager = tabManager;
        this.codeRunner = codeRunner;

        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));

        toolbar.add(createButton("Copy", this::copyCode));
        toolbar.add(createButton("Run", this::runCode));
        toolbar.add(createButton("Zoom in", () -> changeFontSize(1)));
        toolbar.add(createButton("Zoom out", () -> changeFontSize(-1)));
        toolbar.add(createButton("Reset zoom", this::resetFontSize));

        JToggleButton fontToggleButton = new JToggleButton("JetBrains");
        fontToggleButton.setFocusPainted(false);
        fontToggleButton.addActionListener(e -> fontToggle(fontToggleButton));
        toolbar.add(fontToggleButton);

        JToggleButton themeToggleButton = new JToggleButton("Dark Mode");
        themeToggleButton.setFocusPainted(false);
        themeToggleButton.addActionListener(e -> themeToggle(themeToggleButton));
        toolbar.add(themeToggleButton);

        toolbar.add(createButton("About", this::showAboutDialog));
    }
    
    /**
     * Creates a button with the specified text and action.
     * <p>
     * This method initializes a JButton with the given text and sets its action
     * to the provided Runnable. The button is configured to not show focus paint.
     * </p>
     * 
     * @param text The text to display on the button.
     * @param action The action to perform when the button is clicked.
     * @return A JButton configured with the specified text and action.
     */
    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.addActionListener(e -> action.run());
        return button;
    }

    /**
     * Copies the code from the currently selected tab to the system clipboard.
     * <p>
     * This method retrieves the code from the selected tab using the TabManager
     * and copies it to the system clipboard for easy access.
     * </p>
     * 
     * @see TabManager#getSelectedCode()
     * @see TabManager
     * @see EditorTab
     */
    private void copyCode() {
        String code = tabManager.getSelectedCode();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
    
    /**
     * Runs the code from the currently selected tab.
     * <p>
     * This method retrieves the code and file name from the selected tab using the TabManager
     * and executes it using the CodeRunner.
     * </p>
     * 
     * @see CodeRunner
     * @see TabManager#getSelectedCode()
     * @see TabManager#getSelectedFileName()
     */
    private void runCode() {
        String code = tabManager.getSelectedCode();
        String fileName = tabManager.getSelectedFileName();
        if (!code.trim().isEmpty()) {
            codeRunner.runCode(code, fileName);
        } else {
            JOptionPane.showMessageDialog(null, "No code to run.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Changes the font size of the code editor in all open tabs.
     * <p>
     * This method iterates through all open tabs and adjusts the font size
     * based on the provided delta value.
     * </p>
     * 
     * @param delta The amount to change the font size by (positive or negative).
     * @see EditorTab#changeFontSize(int)
     */
    private void changeFontSize(int delta) {
        for (EditorTab tab : tabManager.getOpenTabs()) {
            tab.changeFontSize(delta);
        }
    }

    /**
     * Resets the font size of the code editor in all open tabs to the default size.
     * <p>
     * This method iterates through all open tabs and resets the font size
     * to a predefined default value.
     * </p>
     * 
     * @see EditorTab#resetFontSize()
     */
    private void resetFontSize() {
        for (EditorTab tab : tabManager.getOpenTabs()) {
            tab.resetFontSize();
        }
    }

    private void fontToggle(JToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            for (EditorTab tab : tabManager.getOpenTabs()) {
                tab.setFontJetBrains();
            }
            toggleButton.setText("FiraCode");
        } else {
            for (EditorTab tab : tabManager.getOpenTabs()) {
                tab.setFontFiraCode();
            }
            toggleButton.setText("JetBrains");
        }
    }

    /**
     * Toggles the theme of the application between light and dark modes.
     * <p>
     * This method applies the selected theme using the LafManager and updates
     * all open tabs to reflect the new theme.
     * </p>
     * 
     * @param toggleButton The JToggleButton that triggers the theme change.
     * @see LafManager
     * @see EditorTab#applyRSyntaxTheme()
     * @see TabManager#getOpenTabs()
     */
    private void themeToggle(JToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            LafManager.applyDarkLaf();
            toggleButton.setText("Light Mode");
        } else {
            LafManager.applyLightLaf();
            toggleButton.setText("Dark Mode");
        }

        // Update all windows (for dialogs, etc.)
        for (Window window : Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window);
        }

        // Update RSyntaxTextArea theme for all open tabs
        for (EditorTab tab : tabManager.getOpenTabs()) {
            tab.applyRSyntaxTheme();
        }

        SwingUtilities.updateComponentTreeUI(toolbar.getTopLevelAncestor());
    }

    /**
     * Displays an "About" dialog with information about the application.
     * <p>
     * This method creates a modal dialog that shows the application's name, license,
     * and author information. The dialog is displayed when the "About" button is clicked.
     * </p>
     */
    private void showAboutDialog() {
        JOptionPane.showMessageDialog(null, "Java Programming Demo. CC-BY-NC-SA 4.0. Made by Zens.", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Returns the toolbar instance.
     * <p>
     * This method provides access to the JToolBar instance created in this class.
     * </p>
     * 
     * @return the JToolBar instance
     * @see AppFrame
     */
    public JToolBar getToolbar() {
        return toolbar;
    }
}
