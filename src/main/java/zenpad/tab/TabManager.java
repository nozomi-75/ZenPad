package zenpad.tab;

import java.util.List;

import javax.swing.JTabbedPane;

import zenpad.editor.EditorTab;

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
        EditorTab newTab = new EditorTab(filePath, node, tabbedPane, this, noteFile, language);
        controller.addTab(newTab);
        view.addEditorTab(newTab, node);
    }

    public void closeTab(int index) {
        controller.removeTab(index);
        view.removeTab(index);
    }

    public List<EditorTab> getOpenTabs() {
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

    public EditorTab getEditorTabAt(int index) {
        return controller.getTabAt(index);
    }

    public void changeAllFontSizes(int delta) {
        controller.changeAllFontSizes(delta);
    }

    public void resetAllFontSizes() {
        controller.resetAllFontSizes();
    }
}
