package zenpad.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import zenpad.utils.ButtonFactory;

public class ToolbarView {
    // This toolbar
    private JToolBar toolbar;
    
    // Toggle buttons
    private JToggleButton themeToggleButton;
    private JToggleButton editNotesToggleButton;

    // Normal action buttons
    private JButton copyButton;
    private JButton runButton;
    private JButton plusButton;
    private JButton minButton;
    private JButton resetButton;
    private JButton saveNotesButton;

    private List<JButton> actionButtons;
    
    private Listener listener;
    
    public interface Listener {
        void onCopy();
        void onRun();
        void onFontSizeChange(int delta);
        void onResetFontSize();
        void onToggleTheme(boolean darkMode);
        void onSaveNotes();
        void onToggleEditNotes(boolean editable);
        void onShowAbout();
    }
    
    public ToolbarView() {
        initializeToolbar();
        initToolbarComponents();
    }
    
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    
    public JToolBar getToolbar() {
        return toolbar;
    }
    
    public void setToolbarButtonEnabled(boolean enabled) {
        if (saveNotesButton != null) {
            actionButtons = Arrays.asList(copyButton, runButton, plusButton, minButton, resetButton, saveNotesButton);
            actionButtons.stream().forEach(button -> button.setEnabled(enabled));
        }
    }
    
    private void initializeToolbar() {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));
    }
    
    private void initToolbarComponents() {
        copyButton = ButtonFactory.createButton("Copy", () -> listener.onCopy());
        runButton = ButtonFactory.createButton("Run", () -> listener.onRun());
        plusButton = ButtonFactory.createButton("Font+", () -> listener.onFontSizeChange(1));
        minButton = ButtonFactory.createButton("Font-", () -> listener.onFontSizeChange(-1));
        resetButton = ButtonFactory.createButton("Reset", () -> listener.onResetFontSize());
        saveNotesButton = ButtonFactory.createButton("Save notes", () -> listener.onSaveNotes());
        Stream.of(copyButton, runButton, plusButton, minButton, resetButton, saveNotesButton).forEach(btn -> toolbar.add(btn));
        
        ButtonFactory.createToggleButton("Edit notes", () -> {
            boolean editable = editNotesToggleButton.isSelected();
            editNotesToggleButton.setText(editable ? "Lock notes" : "Edit notes");
            listener.onToggleEditNotes(editable);
        }, btn -> editNotesToggleButton = btn, toolbar);

        ButtonFactory.createToggleButton("Dark mode", () -> {
            boolean darkMode = themeToggleButton.isSelected();
            listener.onToggleTheme(darkMode);
        }, btn -> themeToggleButton = btn, toolbar);
        
        toolbar.add(ButtonFactory.createButton("About", () -> listener.onShowAbout()));
    }
    
    public JToggleButton getThemeToggleButton() {
        return themeToggleButton;
    }
}
