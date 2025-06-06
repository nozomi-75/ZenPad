package zenpad.toolbar;

import javax.swing.JFrame;
import javax.swing.JToolBar;

import zenpad.note.NotePanel;
import zenpad.runners.CodeRunner;
import zenpad.tab.TabManager;

/**
 * Toolbar is a facade that assembles the ToolbarView and ToolbarController,
 * and exposes a simple interface to the rest of the application.
 */
public class Toolbar {
    private final ToolbarController controller;
    private final ToolbarView view;

    public Toolbar(TabManager tabManager, CodeRunner codeRunner, NotePanel notePanel, JFrame parentFrame) {
        controller = new ToolbarController(tabManager, codeRunner, notePanel, parentFrame);
        view = controller.getView();
    }

    public JToolBar getToolbar() {
        return view.getToolbar();
    }

    public void setButtonEnabled(boolean enabled) {
        controller.setToolbarButtonEnabled(enabled);
    }
}
