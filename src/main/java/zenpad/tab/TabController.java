package zenpad.tab;

import java.util.ArrayList;
import java.util.List;

import zenpad.code.CodePanel;

public class TabController {
    private final List<CodePanel> openTabs = new ArrayList<>();
    private final Runnable onTabsChanged;

    public TabController(Runnable onTabsChanged) {
        this.onTabsChanged = onTabsChanged;
    }

    public void addTab(CodePanel tab) {
        openTabs.add(tab);
        notifyTabsChanged();
    }

    public void removeTab(int index) {
        if (index >= 0 && index < openTabs.size()) {
            openTabs.remove(index);
            notifyTabsChanged();
        }
    }

    public List<CodePanel> getOpenTabs() {
        return openTabs;
    }

    public CodePanel getTabAt(int index) {
        if (index >= 0 && index < openTabs.size()) {
            return openTabs.get(index);
        }
        return null;
    }

    public String getSelectedCode(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < openTabs.size()) {
            return openTabs.get(selectedIndex).getCode();
        }
        return "";
    }

    public String getSelectedFileName(int selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < openTabs.size()) {
            return openTabs.get(selectedIndex).getFileName();
        }
        return "";
    }

    public void changeAllFontSizes(int delta) {
        for (CodePanel tab : openTabs) {
            tab.changeFontSize(delta);
        }
    }

    public void resetAllFontSizes() {
        for (CodePanel tab : openTabs) {
            tab.resetFontSize();
        }
    }

    private void notifyTabsChanged() {
        if (onTabsChanged != null) {
            onTabsChanged.run();
        }
    }
}
