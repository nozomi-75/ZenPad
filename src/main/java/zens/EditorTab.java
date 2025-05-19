package zens;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.InputStream;

/**
 * EditorTab is responsible for creating a tab view that displays the content of a file.
 * It initializes a JTextArea to show the file content and wraps it in a JScrollPane for scrolling.
 * The file content is loaded from the specified file path when the tab is created.
 * 
 * @see TabManager
 * @param filePath The full file path to be opened in the new tab.
 * @param node The name of the node that will serve as the tab title.
 */

public class EditorTab {
    private JPanel panel;
    private RSyntaxTextArea codeArea;
    private RTextScrollPane scrollPane;
    private TabHeader tabHeader;
    private String fileName;
    
    /**
     * Constructs an EditorTab for displaying and editing the content of a file.
     *
     * @param filePath The full file path to be opened in the new tab.
     * @param node The name of the node that will serve as the tab title.
     * @param tabbedPane The parent JTabbedPane to which this tab belongs.
     */
    public EditorTab(String filePath, String node, JTabbedPane tabbedPane) {
        // Extract the file name from the file path (everything after the last '/')
        this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        panel = new JPanel(new BorderLayout());

        // Set up the code editor area with syntax highlighting and other features
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setAntiAliasingEnabled(true); // Smoother text rendering
        codeArea.setEditable(true);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);

        applyRSyntaxTheme();

        // Load the file content into the text area asynchronously
        loadFileContent(filePath);

        // Wrap the code area in a scroll pane for scrolling support
        scrollPane = new RTextScrollPane(codeArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create a custom tab header (with close button, etc.)
        tabHeader = new TabHeader(node, tabbedPane, panel);
    }

    /**
     * Loads the content of the specified resource file into the code area asynchronously.
     * Uses a SwingWorker to avoid freezing the UI while reading the file.
     *
     * @param filePath the relative path to the resource file within the classpath (e.g., "ent/sample1.txt")
     */
    private void loadFileContent(String filePath) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                // Try to load the file as a resource from the classpath
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
                    if (inputStream == null) {
                        return "Error: Resource not found: " + filePath;
                    }
                    return new String(inputStream.readAllBytes());
                }
            }
            @Override
            protected void done() {
                try {
                    codeArea.setText(get());
                } catch (Exception e) {
                    codeArea.setText("Error loading " + filePath + ": " + e.getMessage());
                }
            }
        }.execute();
    }

    /**
     * Applies the RSyntaxTextArea theme based on the current look and feel.
     * Loads the theme XML from the classpath and applies it to the code area.
     * 
     * @see LafManager
     * @see Toolbar
     */
    public void applyRSyntaxTheme() {

        // Determine if the current theme is dark
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

        try (InputStream in = getClass().getResourceAsStream(themePath)) {
            // Load the theme from the XML file
            Theme theme = Theme.load(in);
            theme.apply(codeArea);
            codeArea.setFont(FontUtils.loadFont("/fonts/FiraCode-Retina.ttf", 14f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main panel containing the editor and scroll pane.
     * @return the JPanel representing this tab's content
     * @see TabManager
     */
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Returns the current text/code in the editor area.
     * @return the code as a String
     * @see TabManager
     */
    public String getCode() {
        return codeArea.getText();
    }

    /**
     * Returns the tab header component (with close button, etc.).
     * @return headerPanel the custom tab header panel
     * 
     * @see TabHeader
     * @see TabManager
     */
    public JPanel getTabHeader() {
        return tabHeader.getHeaderPanel();
    }

    /**
     * Returns the file name of the current tab (not the full path).
     * @return fileName the name of the file being edited
     * @see TabManager
     */
    public String getFileName() {
        return fileName;
    }
}