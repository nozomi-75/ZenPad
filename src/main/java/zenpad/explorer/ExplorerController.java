package zenpad.explorer;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import zenpad.misc.factory.RTextFactory;
import zenpad.note.NotePanel;
import zenpad.runners.CodeRunner;
import zenpad.tab.TabManager;

public class ExplorerController {

    /**
     * Constructs an {@code ExplorerController} that listens for file selections in the explorer tree
     * and opens the corresponding code tab and note panel when a file is selected.
     * <p>
     * This controller reacts specifically to {@code SampleFile} nodes in the tree. When such a node is selected:
     * <ul>
     *   <li>The associated code file is opened in a new tab via {@code TabManager}.</li>
     *   <li>The corresponding note file is loaded into the {@code NotePanel}.</li>
     *   <li>The syntax theme is applied to the note area using {@code RTextFactory}.</li>
     * </ul>
     *
     * @param tree the {@code JTree} representing the file explorer
     * @param tabManager the manager responsible for creating and switching between editor tabs
     * @param textPanel the panel used to display note content related to the selected file
     */
    public ExplorerController(JTree tree, TabManager tabManager, NotePanel textPanel) {
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;

                Object obj = selectedNode.getUserObject();
                if (obj instanceof ExplorerModel.SampleFile) {
                    ExplorerModel.SampleFile file = (ExplorerModel.SampleFile) obj;
                    String language = CodeRunner.inferLanguageFromFileName(file.filePath);
                    tabManager.openNewTab(file.filePath, file.displayName, file.noteFile, language);
                    textPanel.loadTextFromResource(file.noteFile);
                    RTextFactory.applyRSyntaxTheme(textPanel.getTextArea());
                }
            }
        });
    }
}
