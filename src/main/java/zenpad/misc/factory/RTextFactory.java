package zenpad.misc.factory;

import java.io.IOException;
import java.io.InputStream;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;

import zenpad.misc.FontUtils;
import zenpad.misc.LafManager;
import zenpad.toolbar.Toolbar;

public class RTextFactory {
    @SuppressWarnings("unused")
    private boolean setEditable;

    /**
     * Configures the RSyntaxTextArea with default settings.
     * @param textArea
     */
    public static void configureDefaults(RSyntaxTextArea textArea, Boolean setEditable) {
        textArea.setEditable(setEditable);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setAntiAliasingEnabled(true);

        applyRSyntaxTheme(textArea);
    }

    /**
     * Applies the RSyntaxTextArea theme based on the current look and feel.
     * Loads the theme XML from the classpath and applies it to the code area.
     * 
     * @see LafManager
     * @see Toolbar
     * @param textArea
     */
    public static void applyRSyntaxTheme(RSyntaxTextArea textArea) {
        boolean isDark = LafManager.isDark();

        /**
         * Available themes:
         * dark: Sane dark mode color theme
         * default: Vibrant highlighting on white backdrop.
         * druid: Insane dark with terminal console feels.
         * eclipse: Eclipse palette on white backdrop.
         * idea: IntelliJ palette on white backdrop.
         * monokai: Aesthetic, subtle, and readable dark theme.
         * vs: Visual Studio light palette.
         */
        String themePath = isDark
            ? "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"
            : "/org/fife/ui/rsyntaxtextarea/themes/vs.xml";
        
        try (InputStream in = RTextFactory.class.getResourceAsStream(themePath)) {
            Theme theme = Theme.load(in);
            theme.apply(textArea);
            setFontJetBrains(textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setFontJetBrains(RSyntaxTextArea textArea) {
        textArea.setFont(FontUtils.loadFont("/fonts/JetBrainsMono-Regular.ttf", 14f));
    }
}
