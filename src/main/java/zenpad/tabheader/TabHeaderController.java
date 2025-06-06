package zenpad.tabheader;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import zenpad.ui.TabManager;

public class TabHeaderController {
    @SuppressWarnings("unused")
    private final TabHeaderModel model;
    @SuppressWarnings("unused")
    private final TabHeaderView view;
    @SuppressWarnings("unused")
    private final TabManager tabManager;

    public TabHeaderController(TabHeaderModel model, TabHeaderView view, JTabbedPane tabbedPane, JPanel editorPanel, TabManager tabManager) {
        this.model = model;
        this.view = view;
        this.tabManager = tabManager;

        view.getCloseButton().addActionListener(e -> {
            int index = tabbedPane.indexOfComponent(editorPanel);
            if (index != -1) {
                tabManager.closeTab(index);
            }
        });
    }
}
