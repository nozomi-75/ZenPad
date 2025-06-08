package zenpad.misc.manager;

import java.util.Map;
import java.util.WeakHashMap;

import zenpad.code.CodePanel;

public class FontSizeManager {
    private static float fontSize = 14f;
    private static final Map<CodePanel, Boolean> codePanels = new WeakHashMap<>();

    public static void registerPanel(CodePanel panel) {
        codePanels.put(panel, Boolean.TRUE);
        panel.changeFontSize(0);
    }

    public static void unregisterPanel(CodePanel panel) {
        codePanels.remove(panel);
    }

    public static void changeFontSize(float delta) {
        fontSize = Math.max(9f, Math.min(32f, fontSize + delta));
        for (CodePanel panel : codePanels.keySet()) {
            panel.changeFontSize(0);
        }
    }

    public static void resetFontSize() {
        fontSize = 14f;
        for (CodePanel panel : codePanels.keySet()) {
            panel.changeFontSize(0);
        }
    }

    public static float getFontSize() {
        return fontSize;
    }
}