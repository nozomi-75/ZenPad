package zenpad;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import zenpad.launcher.AppFrame;
import zenpad.ui.NotePanel;

import java.awt.Dimension;

/**
 * LayoutManager provides static utility methods for constructing and configuring
 * the main split panes used in the {@link AppFrame}.
 * <p>
 * This class centralizes the setup of the vertical and horizontal split panes,
 * ensuring consistent sizing, divider locations, and layout structure throughout the application.
 * </p>
 * 
 */
public class LayoutManager {
    public static JSplitPane createInnerSplitPane(JTabbedPane tabbedPane, NotePanel notePanel) {
        notePanel.getNotePanel().setMinimumSize(new Dimension(100, 100));
        JSplitPane innerSplit = new JSplitPane(
            JSplitPane.VERTICAL_SPLIT,
            tabbedPane,
            notePanel.getNotePanel()
        );
        innerSplit.setDividerLocation(0.85);
        innerSplit.setResizeWeight(0.70);
        innerSplit.setDividerSize(8);
        return innerSplit;
    }

    public static JSplitPane createOuterSplitPane(JPanel fileOpenerPanel, JSplitPane innerSplitPane) {
        fileOpenerPanel.setMinimumSize(new Dimension(100, 100));
        innerSplitPane.setMinimumSize(new Dimension(300, 100));
        JSplitPane outerSplit = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            fileOpenerPanel,
            innerSplitPane
        );
        outerSplit.setDividerLocation(200);
        outerSplit.setDividerSize(8);
        return outerSplit;
    }
}