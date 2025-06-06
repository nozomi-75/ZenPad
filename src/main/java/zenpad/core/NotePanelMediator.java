package zenpad.core;

import javax.swing.JSplitPane;

import zenpad.note.NotePanel;
import zenpad.tab.TabManager;
import zenpad.toolbar.Toolbar;

/**
 * Provides a static utility method for managing the visibility and state
 * of the note panel area within {@link LayoutManager#createInnerSplitPane}.
 * <ul>
 *  <li>Show or hide note panel based on whether any tabs are open</li>
 *  <li>Update the note panel's theme properly, if necessary</li>
 *  <li>Enables or disables the toolbar buttons as needed</li>
 * </ul
 */
public class NotePanelMediator {
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
            toolbar.setButtonEnabled(false);
        } else {
            innerSplitPane.setBottomComponent(notePanel.getNotePanel());
            notePanel.updateTheme();
            toolbar.setButtonEnabled(true);
            if (wasCollapsed) {
                innerSplitPane.setDividerLocation(0.70);
                return;
            }
        }
        innerSplitPane.setDividerLocation(dividerLoc);
    }
}