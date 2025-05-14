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
        JButton aboutButton = new JButton("About");

        copyButton.setFocusPainted(false);
        runButton.setFocusPainted(false);
        aboutButton.setFocusPainted(false);

        copyButton.addActionListener(e -> copyCode());
        runButton.addActionListener(e -> runCode());
        aboutButton.addActionListener(e -> showAboutDialog());

        JToggleButton toggleButton = new JToggleButton("Dark Mode");
        toggleButton.addActionListener(e -> themeToggle(toggleButton));

        toolbar.add(copyButton);
        toolbar.add(runButton);
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
     * @see TabManager
     * @see EditorTab
     */
    private void copyCode() {
        String code = tabManager.getSelectedCode();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
    
    private void runCode() {
        String code = tabManager.getSelectedCode();
        if (!code.trim().isEmpty()) {
            codeRunner.runCode(code);
        } else {
            JOptionPane.showMessageDialog(null, "No code to run.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themeToggle(JToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            LafManager.applyDarkLaf();
            toggleButton.setText("Light Mode");
        } else {
            LafManager.applyLightLaf();
            toggleButton.setText("Dark Mode");
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

    public JToolBar getToolbar() {
        return toolbar;
    }
}
