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
