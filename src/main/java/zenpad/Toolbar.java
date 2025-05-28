package zenpad;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
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

    @SuppressWarnings("unused")
    private String lastTheme = null;
    
    public Toolbar(TabManager tabManager, CodeRunner codeRunner, NotePanel notePanel, JFrame parentFrame) {
        this.tabManager = tabManager;
        this.codeRunner = codeRunner;
        this.notePanel = notePanel;
        this.parentFrame = parentFrame;

        initializeToolbar();
        initToolbarComponents();
    }

    private void initializeToolbar() {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));
    }

    private void initToolbarComponents() {
        toolbar.add(ButtonFactory.createButton("Copy", this::copyCode));
        toolbar.add(ButtonFactory.createButton("Run", this::runCode));
        toolbar.add(ButtonFactory.createButton("Font+", () -> changeFontSize(1)));
        toolbar.add(ButtonFactory.createButton("Font-", () -> changeFontSize(-1)));
        toolbar.add(ButtonFactory.createButton("Reset", this::resetFontSize));
    
        ButtonFactory.createToggleButton("Dark mode", () -> themeToggle(themeToggleButton),
        btn -> themeToggleButton = btn, toolbar);
    
        saveNotesButton = ButtonFactory.createButton("Save notes", this::saveNotes);
        toolbar.add(saveNotesButton);

        ButtonFactory.createToggleButton("Edit notes", this::toggleEditNotes,
        btn -> editNotesToggleButton = btn, toolbar);
    
        toolbar.add(ButtonFactory.createButton("About", this::showAboutDialog));
    }

    private void toggleEditNotes() {
        boolean editable = editNotesToggleButton.isSelected();
        notePanel.setEditable(editable);
        editNotesToggleButton.setText(editable ? "Lock notes" : "Edit notes");
    }

    private void copyCode() {
        String code = tabManager.getSelectedCode();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    private void runCode() {
        codeRunner.runSelectedTab(tabManager);
    }

    private void changeFontSize(int delta) {
        tabManager.changeAllFontSizes(delta);
    }

    private void resetFontSize() {
        tabManager.resetAllFontSizes();
    }

    private void saveNotes() {
        String codeFileName = tabManager.getSelectedFileName();
        notePanel.saveNoteArea(toolbar, codeFileName);
    }

    public void setSaveNotesEnabled(boolean enabled) {
        if (saveNotesButton != null) {
            saveNotesButton.setEnabled(enabled);
        }
    }

    private void themeToggle(JToggleButton toggleButton) {
        boolean darkMode = toggleButton.isSelected();
        LafManager.toggleTheme(darkMode, toggleButton, tabManager, notePanel, toolbar);
        lastTheme = darkMode ? "Dark" : "Light";
    }

    private void showAboutDialog() {
        AboutDialog.show(parentFrame);
    }

    public JToolBar getToolbar() {
        return toolbar;
    }
}
