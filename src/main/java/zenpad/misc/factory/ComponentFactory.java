package zenpad.misc.factory;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import zenpad.core.AppFrame;
import zenpad.note.NotePanel;

/**
 * ComponentFactory provides static factory methods for creating and configuring
 * common UI components used in the {@link AppFrame}.
 * <p>
 * This class centralizes the instantiation and initial setup of components such as
 * {@link JTabbedPane} and {@link NotePanel}, ensuring consistent configuration
 * and reducing duplication throughout the codebase.
 * </p>
 */
public class ComponentFactory {
    public static JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(new EmptyBorder(5, 2, 2, 5));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        return tabbedPane;
    }

    public static NotePanel createNotePanel() {
        NotePanel notePanel = new NotePanel();
        notePanel.getNotePanel().setBorder(new EmptyBorder(2, 2, 5, 5));
        return notePanel;
    }
}