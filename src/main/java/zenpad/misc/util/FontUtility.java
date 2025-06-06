package zenpad.misc.util;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;

/**
 * Utility class for loading and registering custom fonts in a Java application.
 * <p>
 * This class provides methods to load fonts from resource files and register them
 * with the local graphics environment, making them available for use throughout
 * the application.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Font customFont = FontUtils.loadFont("/fonts/MyFont.ttf", 14f);
 * </pre>
 * </p>
 */
public class FontUtility {
    public static Font loadFont(String resourcePath, float size) {
        try (InputStream getFont = FontUtility.class.getResourceAsStream(resourcePath)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, getFont).deriveFont(size);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
            return font;
        } catch (Exception e) {
            e.printStackTrace();
            return new Font(Font.MONOSPACED, Font.PLAIN, (int) size);
        }
    }
}
