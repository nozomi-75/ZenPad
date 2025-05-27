package zenpad;

import javax.swing.JButton;
import javax.swing.JFrame;
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
    private NotePanel notePanel;
    private JFrame parentFrame;

    private JToggleButton themeToggleButton;
    private JToggleButton editNotesToggleButton;
    private JButton saveNotesButton;
    private String lastTheme = null;
    
    public Toolbar(TabManager tabManager, CodeRunner codeRunner, NotePanel notePanel, JFrame parentFrame) {
        this.tabManager = tabManager;
        this.codeRunner = codeRunner;
        this.notePanel = notePanel;
        this.parentFrame = parentFrame;

        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));

        initToolbarComponents();
    }

    /**
     * Initializes and adds the language combo box, theme toggle button,
     * and other toolbar buttons to the toolbar.
     * <p>
     * This method sets up the language selection combo box, theme toggle button,
     * and all action buttons, and adds them to the toolbar.
     * </p>
     */
    private void initToolbarComponents() {
        toolbar.add(createButton("Copy", this::copyCode));
        toolbar.add(createButton("Run", this::runCode));
        toolbar.add(createButton("Font+", () -> changeFontSize(1)));
        toolbar.add(createButton("Font-", () -> changeFontSize(-1)));
        toolbar.add(createButton("Reset", this::resetFontSize));

        createToggleButton("Dark mode", () -> themeToggle(themeToggleButton), btn -> themeToggleButton = btn);

        saveNotesButton = createButton("Save notes", this::saveNotes);
        toolbar.add(saveNotesButton);
        createToggleButton("Edit notes", this::toggleEditNotes, btn -> editNotesToggleButton = btn);

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
        button.setMargin(new java.awt.Insets(2, 5, 2, 5));
        return button;
    }

    /**
     * Creates a toggle button with the specified text and action.
     * <p>
     * This method initializes a JToggleButton with the given text and sets its action
     * to the provided Runnable. The toggle button is configured to not show focus paint.
     * </p>
     * 
     * @param text The text to display on the button.
     * @param action The action to perform when the button is clicked.
     */
    private void createToggleButton(String text, Runnable action, java.util.function.Consumer<JToggleButton> assignField) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> action.run());
        btn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        toolbar.add(btn);
        assignField.accept(btn);
    }

    /**
     * Sets whether the text area is editable.
     * @see NotePanel#setEditable
     */
    private void toggleEditNotes() {
        boolean editable = editNotesToggleButton.isSelected();
        notePanel.setEditable(editable);
        editNotesToggleButton.setText(editable ? "Lock notes" : "Edit notes");
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
     * Infers the programming language from the file name extension.
     * @param fileName The file name to check.
     * @return The language string ("Java", "Python", "C"), or null if unsupported.
     */
    public static String inferLanguageFromFileName(String fileName) {
        if (fileName != null) {
            int dot = fileName.lastIndexOf('.');
            String ext = (dot != -1) ? fileName.substring(dot + 1).toLowerCase() : "";
            switch (ext) {
                case "java": return "Java";
                case "py": return "Python";
                case "c": return "C";
                default: return null;
            }
        }
        return null;
    }

    /**
     * Runs the code from the currently selected tab using the selected language.
     */
    private void runCode() {
        String code = tabManager.getSelectedCode();
        String fileName = tabManager.getSelectedFileName();
        String language = inferLanguageFromFileName(fileName);
        if (language == null) {
            JOptionPane.showMessageDialog(null, "Unsupported file type for execution.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!code.trim().isEmpty()) {
            codeRunner.runCode(code, fileName, language);
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
     * Helper method for saving notes.
     * It will show a dialog with a success or error message.
     * @see NotePanel#saveNoteArea
     */
    private void saveNotes() {
        String codeFileName = tabManager.getSelectedFileName();
        notePanel.saveNoteArea(toolbar, codeFileName);
    }

    /**
     * Enables or disables the save notes button based on the current state.
     * @param enabled
     */
    public void setSaveNotesEnabled(boolean enabled) {
        if (saveNotesButton != null) {
            saveNotesButton.setEnabled(enabled);
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
        String newTheme = toggleButton.isSelected() ? "Dark" : "Light";
        if (lastTheme != null && lastTheme.equals(newTheme)) return;
        if (toggleButton.isSelected()) {
            LafManager.applyDarkLaf();
            toggleButton.setText("Light Mode");
        } else {
            LafManager.applyLightLaf();
            toggleButton.setText("Dark Mode");
        }
        lastTheme = newTheme;

        // Batch UI updates
        javax.swing.SwingUtilities.invokeLater(() -> {
            for (Window window : Window.getWindows()) {
                SwingUtilities.updateComponentTreeUI(window);
            }
            for (EditorTab tab : tabManager.getOpenTabs()) {
                RTextHelper.applyRSyntaxTheme(tab.getCodeArea());
            }
            RTextHelper.applyRSyntaxTheme(notePanel.getTextArea());
            SwingUtilities.updateComponentTreeUI(toolbar.getTopLevelAncestor());
        });
    }

    /**
     * Displays an "About" dialog with information about the application.
     * @see AboutDialog
     */
    private void showAboutDialog() {
        AboutDialog.show(parentFrame);
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
