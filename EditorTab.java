/* EditorTab takes the 
 * It creates a JPanel (tab view) that contains a JTextArea
 * The JTextArea is used to display the content of the file
 * The JTextArea is wrapped in a JScrollPane to allow scrolling
*/

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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