package zenpad.misc;

import javax.swing.JTabbedPane;

import zenpad.editor.EditorTab;
import zenpad.note.NotePanel;
import zenpad.tab.TabManager;

/**
 * NoteSyncManager provides static utility methods for synchronizing note content
 * between the shared {@link NotePanel} and individual {@link EditorTab} instances
 * when switching tabs in the application.
 * <p>
 * This class ensures that any unsaved edits in the note area are retained per tab,
 * and the correct note content is restored when a tab is selected.
 * </p>
 */
public class NoteSyncManager {
    public static void syncNotesOnTabSwitch(
            int lastSelectedTabIndex,
            TabManager tabManager,
            NotePanel notePanel,
            JTabbedPane tabbedPane
    ) {
        // Save note edits for the previous tab (if any tab was previously selected)
        if (lastSelectedTabIndex >= 0) {
            EditorTab prevTab = tabManager.getEditorTabAt(lastSelectedTabIndex);
            if (prevTab != null) {
                // Store the current text from the shared NotePanel into the previous tab's memory
                prevTab.setNoteContent(notePanel.getText());
            }
        }

        // Restore note content for the new tab
        int idx = tabbedPane.getSelectedIndex();
        if (idx >= 0) {
            EditorTab editorTab = tabManager.getEditorTabAt(idx);
            if (editorTab != null) {
                // Try to get any previously saved note content for this tab
                String noteContent = editorTab.getNoteContent();
                
                if (noteContent != null) {
                    // If there is saved note content, display it in the NotePanel
                    notePanel.setText(noteContent);
                } else {
                    // If there is no saved note content, try to load from the tab's note file
                    String noteFile = editorTab.getNoteFile();

                    if (noteFile != null && !noteFile.isEmpty()) {
                        notePanel.loadTextFromResource(noteFile);
                    } else {
                        notePanel.setText("No description available.");
                    }
                }
            }
        }
    }
}