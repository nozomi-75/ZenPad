package zenpad;

import java.awt.BorderLayout;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

public class NotePanel {
    private JPanel notePanel;
    private RSyntaxTextArea textArea;
    private RTextScrollPane scrollPane;
    private String currentFilePath;

    /**
     * Constructs a NotePanel for displaying plain text.
     */
    public NotePanel() {
        notePanel = new JPanel(new BorderLayout());

        textArea = new RSyntaxTextArea();
        RTextHelper.configureDefaults(textArea);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_MARKDOWN);

        scrollPane = new RTextScrollPane(textArea);
        scrollPane.getGutter().setLineNumbersEnabled(false);
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
     * @param filePath the resource path (e.g., "notes/HelloWorld.txt")
     */
    public void loadTextFromResource(String filePath) {
        this.currentFilePath = filePath;
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
     * Saves the current note area to a file.
     * If the current file path is null or starts with "notes/" or "samples/", a file chooser dialog is shown.
     * @param parent
     * @param defaultFileName
     * @return boolean
     */
    public boolean saveNoteArea(javax.swing.JComponent parent, String defaultFileName) {
        if (currentFilePath == null || currentFilePath.startsWith("notes/") || currentFilePath.  startsWith("samples/")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save Notes As");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Markdown Files (*.md)", "md");
            chooser.setFileFilter(filter);

            if (defaultFileName != null && !defaultFileName.isEmpty()) {
                String baseName = defaultFileName.replaceAll("\\.[^.]+$", "");
                chooser.setSelectedFile(new java.io.File(baseName + ".md"));
            }
            
            int result = chooser.showSaveDialog(parent);
            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                try {
                    java.io.File file = chooser.getSelectedFile();
                    if (!file.getName().toLowerCase().endsWith(".md")) {
                        file = new java.io.File(file.getParentFile(), file.getName() + ".md");
                    }
                    Files.write(chooser.getSelectedFile().toPath(), getText().getBytes  (StandardCharsets.UTF_8));
                    return true;
                } catch (Exception e) {
                    System.err.println(e);
                    return false;
                }
            }
            return false;
        } else {
            try {
                Files.write(Paths.get(currentFilePath), getText().getBytes(StandardCharsets.UTF_8));
                return true;
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
    }

    /**
     * Updates the colors of the text area and scroll pane to match the current Look and Feel.
     */
    public void updateTheme() {
        javax.swing.SwingUtilities.updateComponentTreeUI(notePanel);
        javax.swing.SwingUtilities.updateComponentTreeUI(scrollPane);
        javax.swing.SwingUtilities.updateComponentTreeUI(textArea);
    }

    /**
     * Gets the current text content.
     * @return The current text as a String.
     */
    public String getText() {
        return textArea.getText();
    }
    
    /**
     * Sets whether the text area is editable.
     * @param editable Whether the text area should be editable.
     */
    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
    }

    /**
     * Returns the RSyntaxTextArea instance for this panel.
     * @return the RSyntaxTextArea.
     */
    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    /**
     * Returns the main panel containing the text view.
     * @return The JPanel for this NotePanel.
     */
    public JPanel getNotePanel() {
        return notePanel;
    }
}
