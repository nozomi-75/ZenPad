package zenpad.toolbar;

/**
 * Listener interface for handling toolbar actions such as copy, run, font size changes,
 * theme toggles, note editing, and showing the About dialog.
 * @see zenpad.toolbar.ToolbarController
 */
public interface ToolbarListener {
    void onCopy();
    void onRun();
    void onFontSizeChange(int delta);
    void onResetFontSize();
    void onToggleTheme(boolean darkMode);
    void onSaveNotes();
    void onToggleEditNotes(boolean editable);
    void onShowAbout();
}
