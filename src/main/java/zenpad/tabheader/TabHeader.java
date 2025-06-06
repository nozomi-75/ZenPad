package zenpad.tabheader;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import zenpad.tab.TabManager;

/**
 * The {@code TabHeader} class represents a custom tab header component for use with a
 * {@link JTabbedPane} in the editor interface.
 * 
 * <ul>
 *  <li>The {@code TabHeader} is responsible for displaying the tab title and managing
 * user actions such as closing the tab.</li>
 *  <li>It integrates tightly with the main editor tab system via {@link TabManager}.</li>
 *  <li>Use {@link #getHeaderPanel()} to retrieve the header component and attach it to a tab.</li>
 * </ul>
 */
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
