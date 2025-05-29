package zenpad.ui;

import javax.swing.*;

import zenpad.models.TabHeaderModel;
import zenpad.views.TabHeaderView;
import zenpad.controllers.TabHeaderController;

public class TabHeader {
    private final TabHeaderModel model;
    private final TabHeaderView view;
    @SuppressWarnings("unused")
    private final TabHeaderController controller;

    public TabHeader(String title, JTabbedPane tabbedPane, JPanel editorPanel, TabManager tabManager) {
        this.model = new TabHeaderModel(title);
        this.view = new TabHeaderView(model.getTitle());
        this.controller = new TabHeaderController(model, view, tabbedPane, editorPanel, tabManager);
    }

    public JPanel getHeaderPanel() {
        return view.getHeaderPanel();
    }
}
