package zenpad;

import java.awt.BorderLayout;
import java.io.InputStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class NotePanel {
    private JPanel notePanel;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * Constructs a NotePanel for displaying plain text.
     */
    public NotePanel() {
        notePanel = new JPanel(new BorderLayout());

        // JTextArea for displaying plain text
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        notePanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets the plain text content of the panel.
     * @param text The text content to display.
     */
    public void setText(String text) {
        textArea.setText(text);
        textArea.setCaretPosition(0);
    }

    /**
     * Loads text from a resource file asynchronously and sets it as plain text.
     * @param filePath the resource path (e.g., "desc/HelloWorld.txt")
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
                    String text = get();
                    setText(text);
                } catch (Exception e) {
                    setText("Error loading " + filePath + ": " + e.getMessage());
                }
            }
        }.execute();
    }

    /**
     * Gets the current text content.
     * @return The current text as a String.
     */
    public String getText() {
        return textArea.getText();
    }

    /**
     * Returns the main panel containing the text view.
     * @return The JPanel for this NotePanel.
     */
    public JPanel getNotePanel() {
        return notePanel;
    }
}
