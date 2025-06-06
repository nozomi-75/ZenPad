package zenpad.fileopener;

import javax.swing.JPanel;

import zenpad.ui.NotePanel;
import zenpad.ui.TabManager;

/**
 * Facade for the file opener MVC components.
 */
public class FileOpenerPanel {
    private JPanel panel;

    public FileOpenerPanel(TabManager tabManager, NotePanel textPanel) {
        FileOpenerModel model = new FileOpenerModel();
        FileOpenerView view = new FileOpenerView(model.getTreeRoot());
        new FileOpenerController(view.getTree(), tabManager, textPanel);

        this.panel = view.getPanel();
    }

    public JPanel getPanel() {
        return panel;
    }
}
