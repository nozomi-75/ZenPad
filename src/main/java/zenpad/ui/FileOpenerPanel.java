package zenpad.ui;

import javax.swing.JPanel;

import zenpad.controllers.FileOpenerController;
import zenpad.models.FileOpenerModel;
import zenpad.views.FileOpenerView;

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
