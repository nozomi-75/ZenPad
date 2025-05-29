package zenpad.ui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import zenpad.TabHeader;
import zenpad.utils.RTextHelper;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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
    private String noteFile;
    private String language;

    private String noteContent = null;

    public EditorTab(String filePath, String node, JTabbedPane tabbedPane, TabManager tabManager, String noteFile, String language) {
        this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        this.language = language;
        panel = new JPanel(new BorderLayout());

        codeArea = new RSyntaxTextArea();
        RTextHelper.configureDefaults(codeArea);
        codeArea.setSyntaxEditingStyle(getSyntaxStyleForLanguage(language));

        loadFileContent(filePath);

        scrollPane = new RTextScrollPane(codeArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        tabHeader = new TabHeader(node, tabbedPane, panel, tabManager);
        this.noteFile = noteFile;
    }

    /**
     * Loads the content of the specified resource file into the code area asynchronously.
     * Uses a SwingWorker to avoid freezing the UI while reading the file.
     *
     * @param filePath the relative path to the resource file within the classpath (e.g., "ent/sample1.txt")
     */
    private void loadFileContent(String filePath) {
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
                if (inputStream == null) {
                    publish("Error: Could not load file: " + filePath);
                    return null;
                }
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                    String line;
                    StringBuilder batch = new StringBuilder();
                    int linesRead = 0;
                    while ((line = reader.readLine()) != null) {
                        batch.append(line).append("\n");
                        linesRead++;
                        if (linesRead % 500 == 0) {
                            publish(batch.toString());
                            batch.setLength(0);
                        }
                    }
                    if (batch.length() > 0) {
                        publish(batch.toString());
                    }
                }
                return null;
            }
            @Override
            protected void process(List<String> chunks) {
                // If the first chunk is an error message, show it and skip appending further
                if (!chunks.isEmpty() && chunks.get(0).startsWith("Error:")) {
                    codeArea.setText(chunks.get(0));
                } else {
                    for (String chunk : chunks) {
                        codeArea.append(chunk);
                    }
                }
            }
            @Override
            protected void done() {
                codeArea.setCaretPosition(0);
            }
        }.execute();
    }

    /**
     * Changes the font size of the code area by a specified delta.
     * Ensures that the font size does not go beyond min/max value.
     *
     * @param delta The amount to change the font size by (positive or negative).
     */
    public void changeFontSize(int delta) {
        java.awt.Font currentFont = codeArea.getFont();
        float newSize = Math.max(9f, Math.min(32f, currentFont.getSize2D() + delta));
        codeArea.setFont(currentFont.deriveFont(newSize));
    }

    public void resetFontSize() {
        codeArea.setFont(codeArea.getFont().deriveFont(14f));
    }

    public void setNoteContent(String content) {
        this.noteContent = content;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getCode() {
        return codeArea.getText();
    }

    public RSyntaxTextArea getCodeArea() {
        return codeArea;
    }

    public JPanel getTabHeader() {
        return tabHeader.getHeaderPanel();
    }

    public String getFileName() {
        return fileName;
    }

    public String getNoteFile() {
        return noteFile;
    }

    /**
     * Returns the RSyntaxTextArea syntax style string for a given language.
     */
    private String getSyntaxStyleForLanguage(String language) {
        if (language == null) return SyntaxConstants.SYNTAX_STYLE_NONE;
        switch (language.toLowerCase()) {
            case "java":
                return SyntaxConstants.SYNTAX_STYLE_JAVA;
            case "python":
                return SyntaxConstants.SYNTAX_STYLE_PYTHON;
            case "c":
                return SyntaxConstants.SYNTAX_STYLE_C;
            default:
                return SyntaxConstants.SYNTAX_STYLE_NONE;
        }
    }

    public String getLanguage() {
        return language;
    }

    /**
     * Applies a theme to the code area only if it has changed.
     * @param themeName The name of the theme to apply.
     */
    public void applyThemeIfNeeded(String themeName) {
        if (!themeName.equals(codeArea.getClientProperty("theme"))) {
            RTextHelper.applyRSyntaxTheme(codeArea);
            codeArea.putClientProperty("theme", themeName);
        }
    }
}