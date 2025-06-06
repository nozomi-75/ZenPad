package zenpad.tab;

import javax.swing.JTabbedPane;

import zenpad.code.CodePanel;

public class TabView {
    private final JTabbedPane tabbedPane;

    public TabView(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    public void addCodePanel(CodePanel codePanel, String title) {
        tabbedPane.addTab(title, codePanel.getPanel());
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, codePanel.getTabHeader());
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    public void removeTab(int index) {
        if (index >= 0 && index < tabbedPane.getTabCount()) {
            tabbedPane.remove(index);
        }
    }

    public int getSelectedIndex() {
        return tabbedPane.getSelectedIndex();
    }
}
