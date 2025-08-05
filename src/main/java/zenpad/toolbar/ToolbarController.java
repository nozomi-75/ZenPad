package zenpad.toolbar;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import zenpad.code.CodePanel;
import zenpad.misc.dialog.AboutDialog;
import zenpad.misc.manager.LafManager;
import zenpad.misc.util.NoteViewer;
import zenpad.note.NotePanel;
import zenpad.runners.CodeRunner;
import zenpad.tab.TabManager;

public class ToolbarController implements ToolbarListener {
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

    @Override
    public void onPrint() {
        int selectedIndex = tabManager.getSelectedIndex();
        if (selectedIndex < 0) {
            return; // No tab is selected, do nothing.
        }

        CodePanel currentTab = tabManager.getEditorTabAt(selectedIndex);
        if (currentTab == null) {
            return; // Should not happen if index is valid, but good practice to check.
        }

        String codeContent = currentTab.getCode();
        String notesContent = notePanel.getText();
        String noteTitle = currentTab.getFileName();
        String language = currentTab.getLanguage() != null ? currentTab.getLanguage().toLowerCase() : "text";

        // Combine the notes and code into a single Markdown string for printing.
        String fullMarkdownContent = notesContent
            + "\n\n---\n\n" // Add a horizontal rule for separation
            + "## Associated Code\n\n"
            + "```" + language + "\n"
            + codeContent + "\n```";

        // Create and show the NoteViewer dialog
        NoteViewer printPreview = new NoteViewer(parentFrame, noteTitle, fullMarkdownContent);
        printPreview.setVisible(true);
    }
    
    public ToolbarView getView() {
        return view;
    }
}
