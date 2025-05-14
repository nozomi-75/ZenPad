package zens;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
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
    private JTextArea codeArea;
    private JScrollPane scrollPane;
    private TabHeader tabHeader;
    
    public EditorTab(String filePath, String button, JTabbedPane tabbedPane) {
        panel = new JPanel(new BorderLayout());

        // Set the configurations for the text area
        // This will be used to display the sample code
        codeArea = new JTextArea();
        codeArea.setLineWrap(true);
        codeArea.setWrapStyleWord(true);
        codeArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        codeArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Load the file content into the text area
        loadFileContent(filePath);

        scrollPane = new JScrollPane(codeArea);
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
        try {
            // Read the file content and set it to the text area
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
                if (inputStream == null) {
                    codeArea.setText("Error: Resource not found: " + filePath);
                    return;
                }
                
                // Read the content while the stream is open
                String content = new String(inputStream.readAllBytes());
                codeArea.setText(content);
            }
            
        } catch (IOException e) {
            codeArea.setText("Error loading " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            codeArea.setText("An unexpected error occurred while loading " + filePath);
            e.printStackTrace();
        }
    }

    // Getters for the entire tab view
    public JPanel getPanel() {
        return panel;
    }

    /**
     * Returns the tab header component.
     * @return
     * 
     * @see TabHeader
     * @see TabManager
     */
    public JPanel getTabHeader() {
        return tabHeader.getHeaderPanel();
    }
}