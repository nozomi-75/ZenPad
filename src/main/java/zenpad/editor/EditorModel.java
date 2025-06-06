package zenpad.editor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EditorModel {
    private String fileName;
    private String filePath;
    private String noteFile;
    private String language;
    private String noteContent;

    public EditorModel(String filePath, String noteFile, String language) {
        this.filePath = filePath;
        this.fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        this.noteFile = noteFile;
        this.language = language;
    }

    public String loadFileContent() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new IllegalArgumentException("Error: Could not load file: " + filePath);
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    // Getters and Setters
    public String getFileName() { return fileName; }
    public String getNoteFile() { return noteFile; }
    public String getLanguage() { return language; }
    public void setNoteContent(String content) { this.noteContent = content; }
    public String getNoteContent() { return noteContent; }
}

