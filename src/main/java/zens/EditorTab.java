package zens;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    
    public EditorTab(String filePath, String button) {
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
    }

    /**
     * Loads the content of the specified file into the text area.
     * If an error occurs while loading the file, an error message is displayed in the text area.
     * 
     * @param filePath The full file path to be opened in the new tab.
     */

    private void loadFileContent(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            codeArea.setText(content);
        } catch (IOException e) {
            codeArea.setText("Error loading " + filePath + " " + e.getMessage());
        }
    }

    // Getters for the entire tab view
    public JPanel getPanel() {
        return panel;
    }
}