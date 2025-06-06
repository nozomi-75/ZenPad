package zenpad.note;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import zenpad.utils.RTextHelper;

public class NoteView {
    private JPanel notePanel;
    private RSyntaxTextArea textArea;
    private RTextScrollPane scrollPane;

    public NoteView() {
        notePanel = new JPanel(new BorderLayout());

        textArea = new RSyntaxTextArea();
        RTextHelper.configureDefaults(textArea, false);
        textArea.setSyntaxEditingStyle(org.fife.ui.rsyntaxtextarea.SyntaxConstants.SYNTAX_STYLE_MARKDOWN);

        scrollPane = new RTextScrollPane(textArea);
        scrollPane.getGutter().setLineNumbersEnabled(false);
        scrollPane.getGutter().setFoldIndicatorEnabled(false);
        notePanel.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return notePanel;
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    public void setText(String text) {
        if (!text.equals(textArea.getText())) {
            textArea.setText(text);
            textArea.setCaretPosition(0);
        }
    }

    public String getText() {
        return textArea.getText();
    }

    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
    }

    public void updateTheme() {
        SwingUtilities.invokeLater(() -> {
            SwingUtilities.updateComponentTreeUI(notePanel);
            SwingUtilities.updateComponentTreeUI(scrollPane);
            SwingUtilities.updateComponentTreeUI(textArea);
        });
    }
}
