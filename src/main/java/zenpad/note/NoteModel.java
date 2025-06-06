package zenpad.note;

public class NoteModel {
    private String currentFilePath;
    private String textContent;

    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void clearFilePath() {
        this.currentFilePath = null;
    }
}
