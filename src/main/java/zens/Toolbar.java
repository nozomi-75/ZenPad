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

        JButton copyButton = new JButton("Copy");
        JButton runButton = new JButton("Run");
        JButton increaseFontSizeButton = new JButton("Zoom in");
        JButton decreaseFontSizeButton = new JButton("Zoom out");
        JButton resetFontSizeButton = new JButton("Reset zoom");
        JButton aboutButton = new JButton("About");

        copyButton.setFocusPainted(false);
        runButton.setFocusPainted(false);
        increaseFontSizeButton.setFocusPainted(false);
        decreaseFontSizeButton.setFocusPainted(false);
        resetFontSizeButton.setFocusPainted(false);
        aboutButton.setFocusPainted(false);

        copyButton.addActionListener(e -> copyCode());
        runButton.addActionListener(e -> runCode());
        increaseFontSizeButton.addActionListener(e -> changeFontSize(1));
        decreaseFontSizeButton.addActionListener(e -> changeFontSize(-1));
        resetFontSizeButton.addActionListener(e -> resetFontSize());
        aboutButton.addActionListener(e -> showAboutDialog());

        JToggleButton toggleButton = new JToggleButton("Dark Mode");
        toggleButton.addActionListener(e -> themeToggle(toggleButton));

        toolbar.add(copyButton);
        toolbar.add(runButton);
        toolbar.add(increaseFontSizeButton);
        toolbar.add(decreaseFontSizeButton);
        toolbar.add(resetFontSizeButton);
        toolbar.add(toggleButton);
        toolbar.add(aboutButton);
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
