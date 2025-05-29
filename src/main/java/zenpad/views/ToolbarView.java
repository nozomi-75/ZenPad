package zenpad.views;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import zenpad.utils.ButtonFactory;

public class ToolbarView {
    private JToolBar toolbar;
    
    private JToggleButton themeToggleButton;
    private JToggleButton editNotesToggleButton;
    private JButton saveNotesButton;
    
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
    
    public void setSaveNotesEnabled(boolean enabled) {
        if (saveNotesButton != null) {
            saveNotesButton.setEnabled(enabled);
        }
    }
    
    private void initializeToolbar() {
        toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setBorder(new EmptyBorder(3, 5, 0, 0));
    }
    
    private void initToolbarComponents() {
        toolbar.add(ButtonFactory.createButton("Copy", () -> listener.onCopy()));
        toolbar.add(ButtonFactory.createButton("Run", () -> listener.onRun()));
        toolbar.add(ButtonFactory.createButton("Font+", () -> listener.onFontSizeChange(1)));
        toolbar.add(ButtonFactory.createButton("Font-", () -> listener.onFontSizeChange(-1)));
        toolbar.add(ButtonFactory.createButton("Reset", () -> listener.onResetFontSize()));
        
        ButtonFactory.createToggleButton("Dark mode", () -> {
            boolean darkMode = themeToggleButton.isSelected();
            listener.onToggleTheme(darkMode);
        }, btn -> themeToggleButton = btn, toolbar);
        
        saveNotesButton = ButtonFactory.createButton("Save notes", () -> listener.onSaveNotes());
        toolbar.add(saveNotesButton);
        
        ButtonFactory.createToggleButton("Edit notes", () -> {
            boolean editable = editNotesToggleButton.isSelected();
            editNotesToggleButton.setText(editable ? "Lock notes" : "Edit notes");
            listener.onToggleEditNotes(editable);
        }, btn -> editNotesToggleButton = btn, toolbar);
        
        toolbar.add(ButtonFactory.createButton("About", () -> listener.onShowAbout()));
    }
    
    public JToggleButton getThemeToggleButton() {
        return themeToggleButton;
    }
}
