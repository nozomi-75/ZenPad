import javax.swing.JTabbedPane;

public class TabManager {
    // Instance of tabbed pane inherited from AppFrame
    private JTabbedPane tabbedPane;

    public TabManager(JTabbedPane tabbedPane) {
        // Initialize the tabbed pane
        this.tabbedPane = tabbedPane;
    }

    /* The FOP object calls this method to handle file opening logic
     * This will take a full file path and a button name
     * The file path is passed to the EditorTab object
     * The button name is passed to the EditorTab object to serve as the tab title
     * The tab is added to the tabbed pane
     * The tab is set to be selected
    */

    public void openNewTab(String filePath, String button) {
        EditorTab newTab = new EditorTab(filePath, button);
        tabbedPane.addTab(button, newTab.getPanel());

        // Set the tab to be selected
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }
}