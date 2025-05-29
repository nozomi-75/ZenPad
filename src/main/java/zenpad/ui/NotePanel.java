package zenpad.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import zenpad.views.NoteView;

public class NotePanel {
    private NoteView view;
    private String currentFilePath = null;
    private String lastTheme = null;
    private final String noDescMsg = "No description available.";
    private JFileChooser fileChooser = null;
    
    public NotePanel() {
        this.view = new NoteView();
    }
    
    public JPanel getPanel() {
        return view.getPanel();
    }
    
    public void loadTextFromResource(String filePath) {
        if (filePath == null && currentFilePath == null) {
            return;
        }
        
        if (filePath != null && filePath.equals(currentFilePath)
        && !view.getText().equals(noDescMsg)) {
            return;
        }
        
        if (filePath == null) {
            view.setText(noDescMsg);
            currentFilePath = null;
            return;
        }
        
        this.currentFilePath = filePath;
        view.setText("");
        
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
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        String line;
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
                }
                return null;
            }
            
            @Override
            protected void process(List<String> chunks) {
                for (String chunk : chunks) {
                    view.getTextArea().append(chunk);
                }
            }
            
            @Override
            protected void done() {
                view.getTextArea().setCaretPosition(0);
            }
        }.execute();
    }
    
    public void saveNoteArea(JComponent parent, String defaultFileName) {
        new SwingWorker<Boolean, Void>() {
            private String savePath = null;
            
            @Override
            protected Boolean doInBackground() throws Exception {
                if (currentFilePath == null || currentFilePath.startsWith("notes/") || currentFilePath.startsWith("samples/")) {
                    if (fileChooser == null) {
                        fileChooser = new JFileChooser();
                        fileChooser.setDialogTitle("Save Notes As");
                        fileChooser.setFileFilter(new FileNameExtensionFilter("Markdown Files (*.md)", "md"));
                    }
                    
                    if (defaultFileName != null && !defaultFileName.isEmpty()) {
                        String baseName = defaultFileName.replaceAll("[^a-zA-Z0-9-_\\.]", "").replaceAll("\\.[^.]+$", "");
                        fileChooser.setSelectedFile(new File(baseName + ".md"));
                    }
                    
                    int result = fileChooser.showSaveDialog(parent);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        if (!file.getName().toLowerCase().endsWith(".md")) {
                            file = new File(file.getParentFile(), file.getName() + ".md");
                        }
                        savePath = file.getAbsolutePath();
                        Files.write(file.toPath(), view.getText().getBytes(StandardCharsets.UTF_8));
                        return true;
                    }
                    return false;
                } else {
                    savePath = currentFilePath;
                    Files.write(Paths.get(currentFilePath), view.getText().getBytes(StandardCharsets.UTF_8));
                    return true;
                }
            }
            
            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        if (savePath != null && (currentFilePath == null || currentFilePath.startsWith("notes/") || currentFilePath.startsWith("samples/"))) {
                            currentFilePath = savePath;
                        }
                        JOptionPane.showMessageDialog(null, "Note successfully saved.", "Save Notes", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (savePath != null) {
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
    
    public void clearCurrentFilePath() {
        this.currentFilePath = null;
    }
    
    public void updateTheme() {
        String currentTheme = UIManager.getLookAndFeel().getName();
        if (!currentTheme.equals(lastTheme)) {
            view.updateTheme();
            lastTheme = currentTheme;
        }
    }
    
    public RSyntaxTextArea getTextArea() {
        return view.getTextArea();
    }
    
    public JPanel getNotePanel() {
        return view.getPanel();
    }
    
    public String getText() {
        return view.getText();
    }
    
    public void setText(String text) {
        view.setText(text);
    }
    
    public void setEditable(boolean editable) {
        view.setEditable(editable);
    }
}
