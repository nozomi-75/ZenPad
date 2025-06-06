package zenpad.editor;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import zenpad.misc.factory.RTextFactory;

public class EditorView {
    private JPanel panel;
    private RSyntaxTextArea codeArea;
    private RTextScrollPane scrollPane;

    public EditorView(String language) {
        panel = new JPanel(new BorderLayout());
        codeArea = new RSyntaxTextArea();
        RTextFactory.configureDefaults(codeArea, true);
        codeArea.setSyntaxEditingStyle(getSyntaxStyleForLanguage(language));

        scrollPane = new RTextScrollPane(codeArea);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    public void setContent(String content) {
        codeArea.setText(content);
        codeArea.setCaretPosition(0);
    }

    public void appendContent(String chunk) {
        codeArea.append(chunk);
    }

    public void changeFontSize(int delta) {
        java.awt.Font currentFont = codeArea.getFont();
        float newSize = Math.max(9f, Math.min(32f, currentFont.getSize2D() + delta));
        codeArea.setFont(currentFont.deriveFont(newSize));
    }

    public void resetFontSize() {
        codeArea.setFont(codeArea.getFont().deriveFont(14f));
    }

    public void applyThemeIfNeeded(String themeName) {
        if (!themeName.equals(codeArea.getClientProperty("theme"))) {
            RTextFactory.applyRSyntaxTheme(codeArea);
            codeArea.putClientProperty("theme", themeName);
        }
    }

    private String getSyntaxStyleForLanguage(String language) {
        if (language == null) return SyntaxConstants.SYNTAX_STYLE_NONE;
        switch (language.toLowerCase()) {
            case "java": return SyntaxConstants.SYNTAX_STYLE_JAVA;
            case "python": return SyntaxConstants.SYNTAX_STYLE_PYTHON;
            case "c": return SyntaxConstants.SYNTAX_STYLE_C;
            default: return SyntaxConstants.SYNTAX_STYLE_NONE;
        }
    }

    public JPanel getPanel() { return panel; }
    public RSyntaxTextArea getCodeArea() { return codeArea; }
}
