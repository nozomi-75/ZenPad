package zenpad;

import java.awt.BorderLayout;
import java.io.InputStream;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TextPanel {
    private JPanel textPanel;
    private JTextArea textArea;

    public TextPanel() {
        textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(true);
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    /**
     * Loads text from a resource file asynchronously and sets it to the text area.
     * @param filePath the resource path (e.g., "samples/HelloWorld.java")
     */
    public void loadTextFromResource(String filePath) {
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
                    setText(get());
                } catch (Exception e) {
                    setText("Error loading " + filePath + ": " + e.getMessage());
                }
            }
        }.execute();
    }

    public String getText() {
        return textArea.getText();
    }

    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
    }

    public JPanel getTextPanel() {
        return textPanel;
    }
}
