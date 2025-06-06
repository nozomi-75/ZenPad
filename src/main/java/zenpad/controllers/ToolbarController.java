package zenpad.controllers;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import zenpad.note.NotePanel;
import zenpad.runners.CodeRunner;
import zenpad.ui.AboutDialog;
import zenpad.ui.TabManager;
import zenpad.utils.LafManager;
import zenpad.views.ToolbarView;

public class ToolbarController implements ToolbarView.Listener {
    private final TabManager tabManager;
    private final CodeRunner codeRunner;
    private final NotePanel notePanel;
    @SuppressWarnings("unused")
    private final JFrame parentFrame;
    private final ToolbarView view;
    
    public ToolbarController(TabManager tabManager, CodeRunner codeRunner, NotePanel notePanel, JFrame parentFrame) {
        this.tabManager = tabManager;
        this.codeRunner = codeRunner;
        this.notePanel = notePanel;
        this.parentFrame = parentFrame;
        
        this.view = new ToolbarView();
        this.view.setListener(this);
    }
    
    public JToolBar getToolbar() {
        return view.getToolbar();
    }
    
    public void setToolbarButtonEnabled(boolean enabled) {
        view.setToolbarButtonEnabled(enabled);
    }
    
    @Override
    public void onCopy() {
        String code = tabManager.getSelectedCode();
        StringSelection selection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }
    
    @Override
    public void onRun() {
        codeRunner.runSelectedTab(tabManager);
    }
    
    @Override
    public void onFontSizeChange(int delta) {
        tabManager.changeAllFontSizes(delta);
    }
    
    @Override
    public void onResetFontSize() {
        tabManager.resetAllFontSizes();
    }
    
    @Override
    public void onToggleTheme(boolean darkMode) {
        LafManager.toggleTheme(darkMode, view.getThemeToggleButton(), tabManager, notePanel, view.getToolbar());
    }
    
    @Override
    public void onSaveNotes() {
        String codeFileName = tabManager.getSelectedFileName();
        notePanel.saveNoteArea(view.getToolbar(), codeFileName);
    }
    
    @Override
    public void onToggleEditNotes(boolean editable) {
        notePanel.setEditable(editable);
    }
    
    @Override
    public void onShowAbout() {
        AboutDialog.show();
    }
    
    public ToolbarView getView() {
        return view;
    }
}
