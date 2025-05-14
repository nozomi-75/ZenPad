package zens;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
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
 * @param button The name of the button that will serve as the tab title.
 */

public class EditorTab {
    private JPanel panel;
    private RSyntaxTextArea codeArea;
    private RTextScrollPane scrollPane;
    private TabHeader tabHeader;
    
    public EditorTab(String filePath, String button, JTabbedPane tabbedPane) {
        panel = new JPanel(new BorderLayout());

        // Set the configurations for the text area
        // This will be used to display the sample code
        codeArea = new RSyntaxTextArea();
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        codeArea.setCodeFoldingEnabled(true);
        codeArea.setAntiAliasingEnabled(true);
        codeArea.setEditable(true);
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);

        applyRSyntaxTheme(); // Apply the RSyntax theme

        // Load the file content into the text area
        loadFileContent(filePath);

        scrollPane = new RTextScrollPane(codeArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        tabHeader = new TabHeader(button, tabbedPane, panel);
    }

    /**
     * Loads the content of the specified resource file into the code area.
     * <p>
     * This method attempts to read the file from the application's classpath using the provided
     * {@code filePath}. If the file is found, its contents are displayed in the {@link JTextArea}.
     * If the file cannot be found or an error occurs during reading, an error message is shown instead.
     * </p>
     *
     * @param filePath the relative path to the resource file within the classpath (e.g., "ent/sample1.txt")
     * @see FileOpenerPanel
     */

    private void loadFileContent(String filePath) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
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
     * <p>
     * This method checks if the current look and feel is dark or light and applies the corresponding
     * RSyntaxTextArea theme. The themes are loaded from XML files located in the classpath.
     * </p>
     * 
     * @see LafManager
     */
    public void applyRSyntaxTheme() {
        boolean isDark = LafManager.isDark(); // You need to implement this method
        String themePath = isDark
            ? "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"
            : "/org/fife/ui/rsyntaxtextarea/themes/default.xml";
        try (InputStream in = getClass().getResourceAsStream(themePath)) {
            Theme theme = Theme.load(in);
            theme.apply(codeArea);
            codeArea.setFont(codeArea.getFont().deriveFont(14f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters for the entire tab view
    public JPanel getPanel() {
        return panel;
    }

    public String getCode() {
        return codeArea.getText();
    }

    /**
     * Returns the tab header component.
     * @return headerPanel
     * 
     * @see TabHeader
     * @see TabManager
     */
    public JPanel getTabHeader() {
        return tabHeader.getHeaderPanel();
    }
}