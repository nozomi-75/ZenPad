package zenpad.fileopener;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import zenpad.note.NotePanel;
import zenpad.runners.CodeRunner;
import zenpad.ui.TabManager;
import zenpad.utils.RTextHelper;

public class FileOpenerController {

    public FileOpenerController(JTree tree, TabManager tabManager, NotePanel textPanel) {
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;

                Object obj = selectedNode.getUserObject();
                if (obj instanceof FileOpenerModel.SampleFile) {
                    FileOpenerModel.SampleFile file = (FileOpenerModel.SampleFile) obj;
                    String language = CodeRunner.inferLanguageFromFileName(file.filePath);
                    tabManager.openNewTab(file.filePath, file.displayName, file.noteFile, language);
                    textPanel.loadTextFromResource(file.noteFile);
                    RTextHelper.applyRSyntaxTheme(textPanel.getTextArea());
                }
            }
        });
    }
}
