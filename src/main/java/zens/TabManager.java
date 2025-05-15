package zens;

import javax.swing.JTabbedPane;

import java.util.ArrayList;
import java.util.List;

/**
 * TabManager is responsible for managing the tabs within the application's main window.
 * <p>
 * It provides functionality to open new editor tabs, retrieve information about open tabs,
 * and access the code or file name from the currently selected tab. The TabManager maintains
 * a reference to the JTabbedPane UI component and a list of EditorTab objects representing
 * each open tab.
 * </p>
 * 
 * <p>
 * Usage:
 * <ul>
 *   <li>Instantiate TabManager with a JTabbedPane instance.</li>
 *   <li>Call {@link #openNewTab(String, String)} to add new tabs.</li>
 *   <li>Use {@link #getSelectedCode()} and {@link #getSelectedFileName()} to access the current tab's content.</li>
 * </ul>
 * </p>
 * 
 * @see AppFrame
 * @see EditorTab
 * @see FileOpenerPanel
 */
public class TabManager {
    private JTabbedPane tabbedPane;
    private List<EditorTab> openTabs = new ArrayList<>();

    public TabManager(JTabbedPane tabbedPane) {
        // Refer to the tabbed pane passed from the AppFrame
        this.tabbedPane = tabbedPane;
    }

    /**
     * Opens a new editor tab in the managed JTabbedPane.
     * <p>
     * This method creates a new {@link EditorTab} using the provided file path and button name.
     * The tab is added to the JTabbedPane, its custom header is set, and it becomes the selected tab.
     * </p>
     *
     * @param filePath the full file path to be loaded in the new editor tab
     * @param button the display name for the tab (typically the file name or a label)
     * @see FileOpenerPanel
     */
    public void openNewTab(String filePath, String button) {
        EditorTab newTab = new EditorTab(filePath, button, tabbedPane);
        openTabs.add(newTab);
        tabbedPane.addTab(button, newTab.getPanel());

        // Set the tab's header
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, newTab.getTabHeader());

        // Set the tab to be selected
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    /**
     * Returns a list of all currently open {@link EditorTab} instances.
     *
     * @return a list containing all open editor tabs
     */
    public List<EditorTab> getOpenTabs() {
        return openTabs;
    }

    /**
     * Retrieves the code/text content from the currently selected editor tab.
     * <p>
     * This method returns the text from the editor area of the selected tab.
     * If no tab is selected or the selected index is invalid, an empty string is returned.
     * </p>
     *
     * @return the code/text from the selected tab, or an empty string if unavailable
     * @see Toolbar
     */
    public String getSelectedCode() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex >=0 && selectedIndex < openTabs.size()) {
            return openTabs.get(selectedIndex).getCode();
        }
        return "";
    }

    /**
     * Retrieves the file name associated with the currently selected editor tab.
     * <p>
     * This method returns the file name as displayed in the selected tab.
     * If no tab is selected or the selected index is invalid, an empty string is returned.
     * </p>
     *
     * @return the file name of the selected tab, or an empty string if unavailable
     * @see Toolbar
     */
    public String getSelectedFileName() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex >=0 && selectedIndex < openTabs.size()) {
            return openTabs.get(selectedIndex).getFileName();
        }
        return "";
    }
}