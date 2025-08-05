package zenpad.misc.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NoteViewer extends JFrame {

    private final JEditorPane editorPane;

    public NoteViewer() {
        setTitle("ZenPad Note Viewer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

        // Load a sample note to display.
        // You can change this path to any of your markdown files.
        loadNote("/home/keiaa/Documents/Java programming/ZenPad/src/main/resources/notes/java/oop/Abstraction.md");
    }

    private void loadNote(String filePath) {
        try {
            // Read Markdown content from file
            Path path = Paths.get(filePath);
            String markdownContent = new String(Files.readAllBytes(path));

            // Convert Markdown to HTML using flexmark-java
            MutableDataSet options = new MutableDataSet();
            Parser parser = Parser.builder(options).build();
            HtmlRenderer renderer = HtmlRenderer.builder(options).build();
            Node document = parser.parse(markdownContent);
            String htmlContent = renderer.render(document);

            // Set the HTML content to the editor pane
            editorPane.setText(htmlContent);
            editorPane.setCaretPosition(0); // Scroll to top
        } catch (IOException e) {
            e.printStackTrace();
            editorPane.setText("<html><body><h1>Error</h1><p>Could not load note: " + e.getMessage() + "</p></body></html>");
        }
    }

    private void printNote() {
        try {
            // This is the core printing functionality. It opens the system's print dialog.
            boolean didPrint = editorPane.print();
            if (didPrint) {
                JOptionPane.showMessageDialog(this, "Printing has been sent to the printer.", "Printing", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "Error while trying to print: " + ex.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            NoteViewer viewer = new NoteViewer();
            viewer.setVisible(true);
        });
    }
}
