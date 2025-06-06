package zenpad.code;

import javax.swing.JTabbedPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import zenpad.tab.TabManager;

import javax.swing.JPanel;

/**
 * Facade class that assembles and exposes the MVC components of a code editing tab in ZenPad.
 * <p>
 * {@code CodePanel} wires together the {@code CodeModel}, {@code CodeView}, and {@code CodeController},
 * and provides a simplified interface for external classes to interact with the code editor.
 * It is used to initialize and manage a single editor tab associated with a specific file and language.
 * </p>
 */
public class CodePanel {
    private CodeModel model;
    private CodeView view;
    private CodeController controller;

    public CodePanel(String filePath, String node, JTabbedPane tabbedPane, TabManager tabManager, String noteFile, String language) {
        model = new CodeModel(filePath, noteFile, language);
        view = new CodeView(language);
        controller = new CodeController(model, view, tabbedPane, node, tabManager);
    }

    // Exposed functionality
    public JPanel getPanel() { return view.getPanel(); }
    public String getCode() { return view.getCodeArea().getText(); }
    public RSyntaxTextArea getCodeArea() { return view.getCodeArea(); }
    public String getFileName() { return model.getFileName(); }
    public String getNoteFile() { return model.getNoteFile(); }
    public void changeFontSize(int delta) { view.changeFontSize(delta); }
    public void resetFontSize() { view.resetFontSize(); }
    public void setNoteContent(String content) { model.setNoteContent(content); }
    public String getNoteContent() { return model.getNoteContent(); }
    public JPanel getTabHeader() { return controller.getTabHeader().getHeaderPanel(); }
    public void applyThemeIfNeeded(String themeName) { view.applyThemeIfNeeded(themeName); }
    public String getLanguage() { return model.getLanguage(); }
}
