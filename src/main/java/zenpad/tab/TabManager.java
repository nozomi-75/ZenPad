package zenpad.tab;

import java.util.List;

import javax.swing.JTabbedPane;

import zenpad.code.CodePanel;

/**
 * Serves as the primary coordinator for managing code editor tabs
 * within the application's tabbed interface.
 *
 * <p>This class is responsible for opening and closing tabs, accessing tab content and metadata,
 * adjusting font sizes across all tabs, and notifying listeners when the tab state changes.
 * It encapsulates a {@link JTabbedPane} and ensures the visual and logical components remain in sync.
 *
 * @see zenpad.core.AppFrame
 * @see zenpad.tabheader.TabHeader
 * @see zenpad.code.CodePanel
 */
public class TabManager {
    private final TabController controller;
    private final TabView view;
    private final JTabbedPane tabbedPane;

    public TabManager(JTabbedPane tabbedPane, Runnable onTabsChanged) {
        this.tabbedPane = tabbedPane;
        this.controller = new TabController(onTabsChanged);
        this.view = new TabView(tabbedPane);
    }

    public void openNewTab(String filePath, String node, String noteFile, String language) {
        CodePanel newTab = new CodePanel(filePath, node, tabbedPane, this, noteFile, language);
        controller.addTab(newTab);
        view.addCodePanel(newTab, node);
    }

    public void closeTab(int index) {
        controller.removeTab(index);
        view.removeTab(index);
    }

    public List<CodePanel> getOpenTabs() {
        return controller.getOpenTabs();
    }

    public String getSelectedCode() {
        int selectedIndex = view.getSelectedIndex();
        return controller.getSelectedCode(selectedIndex);
    }

    public String getSelectedFileName() {
        int selectedIndex = view.getSelectedIndex();
        return controller.getSelectedFileName(selectedIndex);
    }

    public CodePanel getEditorTabAt(int index) {
        return controller.getTabAt(index);
    }

    public void changeAllFontSizes(int delta) {
        controller.changeAllFontSizes(delta);
    }

    public void resetAllFontSizes() {
        controller.resetAllFontSizes();
    }
}
