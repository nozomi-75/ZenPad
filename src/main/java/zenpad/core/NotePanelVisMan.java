package zenpad.core;

import javax.swing.JSplitPane;

import zenpad.ui.NotePanel;
import zenpad.ui.TabManager;

/**
 * NotePanelVisMan provides a static utility method for managing the visibility and state
 * of the note panel area within the application's {@link zenpad.core.LayoutManager#createInnerSplitPane}.
 * <p>
 * This class ensures that the note panel is shown or hidden appropriately based on
 * whether any tabs are open, updates the note panel's theme, and enables or disables
 * the "Save Notes" toolbar button as needed.
 * </p>
 */
public class NotePanelVisMan {
    public static void updateNotePanelVis(
            JSplitPane innerSplitPane,
            TabManager tabManager,
            NotePanel notePanel,
            Toolbar toolbar
    ) {
        boolean wasCollapsed = (innerSplitPane.getBottomComponent() == null);
        int dividerLoc = innerSplitPane.getDividerLocation();

        if (tabManager.getOpenTabs().isEmpty()) {
            innerSplitPane.setBottomComponent(null);
            toolbar.setSaveNotesEnabled(false);
        } else {
            innerSplitPane.setBottomComponent(notePanel.getNotePanel());
            notePanel.updateTheme();
            toolbar.setSaveNotesEnabled(true);
            if (wasCollapsed) {
                innerSplitPane.setDividerLocation(0.70);
                return;
            }
        }
        innerSplitPane.setDividerLocation(dividerLoc);
    }
}