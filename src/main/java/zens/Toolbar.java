package zens;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
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
 * @param tabManager The TabManager instance used to manage tabs in the application.
 */

public class Toolbar {
    private JToolBar toolbar;
    private TabManager tabManager;
    
    public Toolbar(TabManager tabManager) {
        this.tabManager = tabManager;

        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));

        JButton copyButton = new JButton("Copy");
        JButton runButton = new JButton("Run");
        JButton aboutButton = new JButton("About");

        copyButton.setFocusPainted(false);
        runButton.setFocusPainted(false);
        aboutButton.setFocusPainted(false);

        copyButton.addActionListener(e -> {
            // Implement copy functionality
            // copyCode();
        });

        runButton.addActionListener(e -> {
            // Implement run functionality
            // runCode();
        });

        aboutButton.addActionListener(e -> {
            // Implement about functionality
            showAboutDialog();
        });

        toolbar.add(copyButton);
        toolbar.add(runButton);
        toolbar.add(aboutButton);
    }
    
    /*
    private void copyCode() {
        String code = tabManager.getSelectedCode();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
    */

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
