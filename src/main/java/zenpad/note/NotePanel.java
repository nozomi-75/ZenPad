package zenpad.note;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

/**
 * Facade class for the MVC-based note-taking component in ZenPad.
 * <p>
 * This panel displays and manages an editable note area, which supports syntax highlighting.
 * </p>
 */
public class NotePanel {
    private final NoteView view;
    private final NoteModel model;
    private final NoteController controller;
    private String lastTheme = null;

    public NotePanel() {
        this.view = new NoteView();
        this.model = new NoteModel();
        this.controller = new NoteController(model, view);
    }

    public JPanel getPanel() {
        return view.getPanel();
    }

    public void loadTextFromResource(String path) {
        controller.loadTextFromResource(path);
    }

    public void saveNoteArea(JComponent parent, String defaultFileName) {
        controller.saveNoteArea(parent, defaultFileName);
    }

    public void clearCurrentFilePath() {
        model.clearFilePath();
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
