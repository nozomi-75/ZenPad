package zenpad.misc.manager;

import java.util.ArrayList;
import java.util.List;

import zenpad.code.CodePanel;

public class FontSizeManager {
    private static float fontSize = 14f;
    private static final List<CodePanel> registeredPanels = new ArrayList<>();

    public static void registerPanel(CodePanel panel) {
        registeredPanels.add(panel);
        panel.changeFontSize(0);
    }

    public static void unregisterPanel(CodePanel panel) {
        registeredPanels.remove(panel);
    }

    public static void changeFontSize(float delta) {
        fontSize = Math.max(9f, Math.min(32f, fontSize + delta));
        for (CodePanel panel : registeredPanels) {
            panel.changeFontSize(0);
        }
    }

    public static void resetFontSize() {
        fontSize = 14f;
        for (CodePanel panel : registeredPanels) {
            panel.changeFontSize(0);
        }
    }

    public static float getFontSize() {
        return fontSize;
    }
}