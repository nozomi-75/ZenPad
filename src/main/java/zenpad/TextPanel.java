package zenpad;

import java.awt.BorderLayout;
import java.io.InputStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.SwingWorker;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * TextPanel provides a panel that displays Markdown-rendered content only.
 * It supports asynchronous loading of text resources and renders Markdown as HTML.
 */
public class TextPanel {
    private JPanel textPanel;
    private JEditorPane editorPane;
    private JScrollPane scrollPane;

    /**
     * Constructs a TextPanel with Markdown rendering support.
     */
    public TextPanel() {
        textPanel = new JPanel(new BorderLayout());

        // JEditorPane for rendering HTML (used for Markdown)
        editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setContentType("text/html");
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        java.awt.Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        scrollPane = new JScrollPane(editorPane);
        textPanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Sets the Markdown content of the panel and renders it as HTML.
     * @param markdown The Markdown content to render.
     */
    public void setText(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String htmlBody = renderer.render(document);

        // Basic HTML template for better font and padding
        String html = "<html><head>"
            + "<style>"
            + "body { font-family: 'Segoe UI', 'Arial', sans-serif; font-size: 10px; padding: 5px; }"
            + "pre { background: #7F8C8D; padding: 8px; border-radius: 4px; }"
            + "code { font-family: 'monospace'; }"
            + "h1, h2, h3, h4 { margin-top: 1.2em; }"
            + "</style>"
            + "</head><body>" + htmlBody + "</body></html>";

        editorPane.setText(html);
        editorPane.setCaretPosition(0);
    }

    /**
     * Loads text from a resource file asynchronously and sets it as Markdown.
     * @param filePath the resource path (e.g., "desc/HelloWorld.md")
     */
    public void loadTextFromResource(String filePath) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
                    if (inputStream == null) {
                        return "Error: Resource not found: " + filePath;
                    }
                    return new String(inputStream.readAllBytes());
                }
            }
            @Override
            protected void done() {
                try {
                    String text = get();
                    setText(text);
                } catch (Exception e) {
                    setText("Error loading " + filePath + ": " + e.getMessage());
                }
            }
        }.execute();
    }

    /**
     * Gets the current HTML content.
     * @return The current HTML as a String.
     */
    public String getText() {
        return editorPane.getText();
    }

    /**
     * Returns the main panel containing the Markdown view.
     * @return The JPanel for this TextPanel.
     */
    public JPanel getTextPanel() {
        return textPanel;
    }
}
