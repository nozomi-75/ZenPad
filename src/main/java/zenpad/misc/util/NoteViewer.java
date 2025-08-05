package zenpad.misc.util;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class NoteViewer extends JDialog {

    private final JEditorPane editorPane;

    /**
     * Creates a modal dialog to preview and print a note.
     * @param owner The parent frame.
     * @param noteTitle The title of the note for the window.
     * @param markdownContent The full markdown content of the note.
     */
    public NoteViewer(Frame owner, String noteTitle, String markdownContent) {
        super(owner, "Print Preview - " + noteTitle, true);
        setSize(800, 600);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner);

        // Create the editor pane to display the note
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html"); // We'll render Markdown to HTML
        // Add it to a scroll pane for scrolling long notes
        JScrollPane scrollPane = new JScrollPane(editorPane);

        // Create a print button
        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> printNote());

        // Create a panel for the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);

        // Add components to the frame
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Load the note content passed to the constructor
        loadNoteContent(markdownContent);
    }

    private void loadNoteContent(String markdownContent) {
        // Convert Markdown to HTML using flexmark-java
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(markdownContent);
        String htmlBody = renderer.render(document);

        // Add CSS for improved, modern styling for printing.
        String htmlContent = "<html><head><style>"
            + "body { font-family: sans-serif; background-color: #fff; color: #333; margin: 0; padding: 0; }"
            + ".printable-area { padding: 1in; }"
            + "code, pre { font-family: 'JetBrains Mono', 'Noto Mono', 'Hack', 'Ubuntu Mono', 'Consolas', 'source-code-pro', monospace; }"
            + "pre { background-color: #f6f8fa; padding: 16px; overflow: auto; font-size: 85%; line-height: 1.45; border-radius: 6px; }"
            + "pre code { background-color: transparent; padding: 0; }"
            + "h1, h2, h3, h4, h5, h6 { margin-top: 24px; margin-bottom: 16px; font-weight: 600; line-height: 1.25; border-bottom: 1px solid #eaecef; padding-bottom: .3em; }"
            + "h1 { font-size: 2em; } h2 { font-size: 1.5em; } h3 { font-size: 1.25em; }"
            + "p, blockquote, ul, ol, dl, table { margin-top: 16px; margin-bottom: 16px; }"
            + "ul, ol { padding-left: 2em; }"
            + "</style></head><body>"
            + "<div class='printable-area'>"
            + htmlBody
            + "</div>"
            + "</body></html>";

        // Set the HTML content to the editor pane
        editorPane.setText(htmlContent);
        editorPane.setCaretPosition(0); // Scroll to top
    }
    
    private void printNote() {
        try {
            // This simpler print method respects user settings from the dialog
            // more naturally. The margin is now handled by CSS padding on a wrapper div.
            boolean didPrint = editorPane.print();

            if (didPrint) {
                JOptionPane.showMessageDialog(this, "Printing has been sent to the printer.", "Printing", JOptionPane.INFORMATION_MESSAGE);
                // Close the preview window after sending to printer
                this.dispose();
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error while trying to print: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
