package zenpad.note;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import zenpad.misc.DialogUtils;

public class NoteController {
    private final NoteModel model;
    private final NoteView view;
    private JFileChooser fileChooser;
    private final String noDescMsg = "No description available.";

    public NoteController(NoteModel model, NoteView view) {
        this.model = model;
        this.view = view;
    }

    public void loadTextFromResource(String filePath) {
        if (filePath == null && model.getCurrentFilePath() == null) {
            return;
        }

        if (filePath != null && filePath.equals(model.getCurrentFilePath())
                && !view.getText().equals(noDescMsg)) {
            return;
        }

        if (filePath == null) {
            view.setText(noDescMsg);
            model.clearFilePath();
            return;
        }

        model.setCurrentFilePath(filePath);
        view.setText("");

        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
                    if (inputStream == null) {
                        publish("Error: Resource not found: " + filePath);
                        return null;
                    }

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                        StringBuilder batch = new StringBuilder();
                        int linesRead = 0;
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
                String currentFilePath = model.getCurrentFilePath();

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
                    Files.write(new File(currentFilePath).toPath(), view.getText().getBytes(StandardCharsets.UTF_8));
                    return true;
                }
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        if (savePath != null && (model.getCurrentFilePath() == null ||
                            model.getCurrentFilePath().startsWith("notes/") ||
                            model.getCurrentFilePath().startsWith("samples/"))) {
                            model.setCurrentFilePath(savePath);
                        }
                        DialogUtils.showInfo("Note saved successfully.", "Save notes");
                    } else {
                        DialogUtils.showError("Failed to save notes.", "Error");
                    }
                } catch (Exception e) {
                    DialogUtils.showError("Failed to save notes: " +e.getMessage(), "Error");
                }
            }
        }.execute();
    }
}
