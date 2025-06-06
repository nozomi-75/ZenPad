package zenpad.explorer;

import javax.swing.JPanel;

import zenpad.note.NotePanel;
import zenpad.tab.TabManager;

/**
 * Facade class that wires together the MVC components of the file explorer panel in ZenPad.
 * <p>
 * This panel displays a file browser that allows users to select and open code samples.
 * Internally, it sets up the {@link ExplorerModel}, {@link ExplorerView}, and {@link ExplorerController},
 * and exposes the assembled {@code JPanel} for integration into the main application frame.
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
