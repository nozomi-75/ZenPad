package zenpad;

import java.awt.BorderLayout;

import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TextPanel {
    private JPanel textPanel;
    private JTextArea textArea;

    public TextPanel() {
        textPanel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setEditable(true);
        textPanel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void setText(String text) {
        textArea.setText(text);
        textArea.setCaretPosition(0);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
    }

    public JPanel getTextPanel() {
        return textPanel;
    }
}
