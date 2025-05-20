package zenpad;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.awt.BorderLayout;
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
    private String noteFile; // <-- add this
    
    /**
     * Constructs an EditorTab for displaying and editing the content of a file.
     *
     * @param filePath The full file path to be opened in the new tab.
     * @param node The name of the node that will serve as the tab title.
     * @param tabbedPane The parent JTabbedPane to which this tab belongs.
     * @param tabManager The TabManager instance managing the tabs.
     * @param noteFile The description file path for this tab (may be null).
     */
    public EditorTab(String filePath, String node, JTabbedPane tabbedPane, TabManager tabManager, String noteFile) {
        this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        panel = new JPanel(new BorderLayout());

        codeArea = new RSyntaxTextArea();
        RTextHelper.configureDefaults(codeArea);
        codeArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

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
                    codeArea.setCaretPosition(0);
                } catch (Exception e) {
                    codeArea.setText("Error loading " + filePath + ": " + e.getMessage());
                }
            }
        }.execute();
    }

    /**
     * Changes the font size of the code area by a specified delta.
     * Ensures that the font size does not go below a minimum value.
     *
     * @param delta The amount to change the font size by (positive or negative).
     */
    public void changeFontSize(int delta) {
        java.awt.Font currentFont = codeArea.getFont();
        float newSize = Math.max(11f, currentFont.getSize2D() + delta);
        codeArea.setFont(currentFont.deriveFont(newSize));
    }

    /**
     * Returns the code area (RSyntaxTextArea) for this tab.
     * @return the RSyntaxTextArea representing this tab's code editor
     * @see Toolbar
     */
    public void resetFontSize() {
        codeArea.setFont(codeArea.getFont().deriveFont(14f));
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
     * Returns the RSyntaxTextArea instance for this tab.
     * @return The RSyntaxTextArea.
     */
    public RSyntaxTextArea getCodeArea() {
        return codeArea;
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

    public String getNoteFile() {
        return noteFile;
    }
}