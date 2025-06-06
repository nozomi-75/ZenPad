package zenpad.explorer;

import javax.swing.JPanel;

import zenpad.note.NotePanel;
import zenpad.tab.TabManager;

/**
 * Facade for the file opener MVC components.
 */
public class ExplorerPanel {
    private JPanel panel;

    public ExplorerPanel(TabManager tabManager, NotePanel textPanel) {
        ExplorerModel model = new ExplorerModel();
        ExplorerView view = new ExplorerView(model.getTreeRoot());
        new ExplorerController(view.getTree(), tabManager, textPanel);

        this.panel = view.getPanel();
    }

    public JPanel getPanel() {
        return panel;
    }
}
