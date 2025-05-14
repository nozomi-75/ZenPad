package zens;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Component;

/**
 * TabManager is responsible for managing the tabs in the application.
 * It handles the logic for opening new tabs and managing their content.
 * The tabbed pane is passed to the TabManager constructor for management.
 * 
 * @see AppFrame
 * @see EditorTab
 * @see FileOpenerPanel
 * @param tabbedPane The JTabbedPane instance to be managed.
 */

public class TabManager {
    private JTabbedPane tabbedPane;

    public TabManager(JTabbedPane tabbedPane) {
        // Refer to the tabbed pane passed from the AppFrame
        this.tabbedPane = tabbedPane;
    }

    /**
     * Opens a new tab with the specified file path and button name.
     * The FOP object calls this method to handle file opening logic.
     * The file path is passed to the EditorTab object.
     * The button name is passed to the EditorTab object to serve as the tab title.
     * The tab is added to the tabbed pane and set to be selected.
     * 
     * @see EditorTab
     * @param filePath The full file path to be opened in the new tab.
     * @param button The name of the button that will serve as the tab title.
     */

    public void openNewTab(String filePath, String button) {
        EditorTab newTab = new EditorTab(filePath, button);
        tabbedPane.addTab(button, newTab.getPanel());

        // Set the tab to be selected
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    /**
     * Gets the code from the currently selected tab.
     * This method retrieves the text from the JTextArea in the selected tab.
     * It checks if the selected component is a JPanel and retrieves the text area from it.
     * If the selected component is not a JPanel, it returns an empty string.
     * @return
     * 
     * @see EditorTab
     */
    public String getSelectedCode() {
        Component comp = tabbedPane.getSelectedComponent();

        if (comp instanceof JPanel panel) {
            JScrollPane scrollPane = (JScrollPane) panel.getComponent(0);
            JTextArea textArea = (JTextArea) scrollPane.getViewport().getView();
            return textArea.getText();
        }

        return "";
    }
}