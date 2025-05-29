package zenpad.ui;

import java.awt.BorderLayout;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.charset.StandardCharsets;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import zenpad.utils.RTextHelper;

public class NotePanel {
    private JPanel notePanel;
    private RSyntaxTextArea textArea;
    private RTextScrollPane scrollPane;
    private String currentFilePath;
    private JFileChooser fileChooser = null;
    private String lastTheme = null;
    private String noDescMsg = "No description available.";
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
        scrollPane.getGutter().setFoldIndicatorEnabled(false);
        notePanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets the plain text content of the panel.
     * @param text The text content to display.
     */
    public void setText(String text) {
        if (!text.equals(textArea.getText())) {
            textArea.setText(text);
            textArea.setCaretPosition(0);
        }
    }

    /**
     * Loads text from a resource file asynchronously and sets it as plain text.
     * @param filePath the resource path (e.g., "notes/HelloWorld.txt")
     */
    public void loadTextFromResource(String filePath) {
        // If both filePath and currentFilePath are null, do nothing.
        // There is no note to load, nothing is currently loaded.
        if (filePath == null && currentFilePath == null) {
            return;
        }
        // Skip reloading note if already loaded and displayed.
        // If text area is showing noDescMsg, still reload.
        if (filePath !=null && filePath.equals(currentFilePath)
            && !textArea.getText().equals(noDescMsg)) {
            return;
        }
        // Sets text area to show noDescMsg if filePath is null.
        if (filePath == null) {
            setText(noDescMsg);
            currentFilePath = null;
            return;
        }

        this.currentFilePath = filePath;
        textArea.setText("");

        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
                    if (inputStream == null) {
                        publish("Error: Resource not found: " + filePath);
                        return null;
                    }
                    StringBuilder batch = new StringBuilder();
                    int linesRead = 0;
                    try (BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(inputStream))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            batch.append(line).append("\n");
                            linesRead++;
                            if (linesRead % 500 == 0) { // Publish every 500 lines
                                publish(batch.toString());
                                batch.setLength(0);
                            }
                        }
                        if (batch.length() > 0) {
                            publish(batch.toString());
                        }
                    }
                }
                return null;
            }

            @Override
                protected void process(List<String> chunks) {
                    for (String chunk : chunks) {
                        textArea.append(chunk);
                    }
                }

            @Override
            protected void done() {
                textArea.setCaretPosition(0);
            }
        }.execute();
    }

    /**
     * Saves the current note area to a file asynchronously.
     * If the current file path is null or starts with "notes/" or "samples/", a file chooser dialog is shown.
     * The result of the save operation is shown in a dialog.
     * @param parent
     * @param defaultFileName
     */
    public void saveNoteArea(javax.swing.JComponent parent, String defaultFileName) {
        new SwingWorker<Boolean, Void>() {
            private String savePath = null;

            @Override
            protected Boolean doInBackground() throws Exception {
                if (!(currentFilePath == null) || currentFilePath.startsWith("notes/") || currentFilePath.startsWith("samples/")) {
                    if (fileChooser == null) {
                        fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Save Notes As");
                        FileNameExtensionFilter filter = new FileNameExtensionFilter("Markdown Files (*.md)", "md");
                        fileChooser.setFileFilter(filter);
                    }
                    if (defaultFileName != null && !defaultFileName.isEmpty()) {
                        String baseName = defaultFileName.replaceAll("[^a-zA-Z0-9-_\\.]", "").replaceAll("\\.[^.]+$", "");
                        fileChooser.setSelectedFile(new java.io.File(baseName + ".md"));
                    }
                    int result = fileChooser.showSaveDialog(parent);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        if (!file.getName().toLowerCase().endsWith(".md")) {
                            file = new java.io.File(file.getParentFile(), file.getName() + ".md");
                        }
                        savePath = file.getAbsolutePath();
                        Files.write(file.toPath(), getText().getBytes(StandardCharsets.UTF_8));
                        return true;
                    }
                    return false; // User cancelled
                } else {
                    savePath = currentFilePath;
                    Files.write(Paths.get(currentFilePath), getText().getBytes(StandardCharsets.UTF_8));
                    return true;
                }
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        // Update currentFilePath if a new file was saved
                        if (savePath != null && (currentFilePath == null || currentFilePath.startsWith("notes/") || currentFilePath.startsWith("samples/"))) {
                             currentFilePath = savePath;
                        }
                        JOptionPane.showMessageDialog(null, "Note successfully saved.", "Save Notes", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                         // Only show error if savePath is null (user cancelled) or an exception occurred
                         if (savePath == null) {
                             // User cancelled, no message needed
                         } else {
                             JOptionPane.showMessageDialog(null, "Failed to save notes.", "Save Notes", JOptionPane.ERROR_MESSAGE);
                         }
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    JOptionPane.showMessageDialog(null, "Failed to save notes: " + e.getMessage(), "Save Notes", JOptionPane.ERROR_MESSAGE);
                }
            }
        }.execute();
    }

    /**
     * Updates the colors of the text area and scroll pane to match the current Look and Feel.
     */
    public void updateTheme() {
        String currentTheme = javax.swing.UIManager.getLookAndFeel().getName();
        if (!currentTheme.equals(lastTheme)) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                javax.swing.SwingUtilities.updateComponentTreeUI(notePanel);
                javax.swing.SwingUtilities.updateComponentTreeUI(scrollPane);
                javax.swing.SwingUtilities.updateComponentTreeUI(textArea);
            });
            lastTheme = currentTheme;
        }
    }

    public void clearCurrentFilePath() {
        this.currentFilePath = null;
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
