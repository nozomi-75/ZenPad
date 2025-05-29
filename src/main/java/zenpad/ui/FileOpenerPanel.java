package zenpad.ui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import zenpad.Branch;
import zenpad.SampleBranches;
import zenpad.launcher.AppFrame;

import java.awt.GridLayout;
import java.util.List;

/**
 * FileOpenerPanel is responsible for creating a panel with nodes to open files.
 * It takes a TabManager object as a parameter to handle the logic for opening files in new tabs.
 * The panel contains a JTree that displays sample files.
 * 
 * @see TabManager
 * @param tabManager The TabManager instance to handle file opening logic.
 */

public class FileOpenerPanel {
    private NotePanel textPanel;
    private JPanel panel;
    
    public FileOpenerPanel(TabManager tabManager, NotePanel textPanel) {
        this.textPanel = textPanel;

        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(new EmptyBorder(5, 5, 5, 2));

        JScrollPane scrollPane = new JScrollPane(createSampleTree(tabManager));
        panel.add(scrollPane);
    }

    /**
     * Creates a JTree with sample files.
     * The tree is populated with nodes representing sample files.
     * When a node is selected, it opens the corresponding file in a new tab using the TabManager.
     * 
     * @param tabManager The TabManager instance to handle file opening logic.
     * @return A JTree populated with sample files.
     */

    private JTree createSampleTree(TabManager tabManager) {

        List<Branch> branches = SampleBranches.getBranches();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        for (Branch branch : branches) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(branch.parentName);
            for (int i = 0; i < branch.displayNames.length; i++) {
                String filePath = "samples" + branch.baseDir + branch.fileNames[i];
                String notePath = (
                    branch.noteFiles != null && branch.noteFiles.length > i &&
                    branch.noteFiles[i] != null && !branch.noteFiles[i].isEmpty())
                    ? "notes" + branch.baseDir + branch.noteFiles[i]
                    : null;
                parentNode.add(new DefaultMutableTreeNode(
                    new SampleFile(
                        branch.displayNames[i],
                        filePath,
                        notePath
                    )
                ));
            }

            root.add(parentNode);
        }

        // Set root node configuration
        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(createTreeSelectionListener(tabManager, tree));

        return tree;
    }

    private TreeSelectionListener createTreeSelectionListener(TabManager tabManager, JTree tree) {
        return new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;
                Object userObj = selectedNode.getUserObject();
                if (userObj instanceof SampleFile) {
                    SampleFile sample = (SampleFile) userObj;
                    String language = CodeRunner.inferLanguageFromFileName(sample.filePath);
                    tabManager.openNewTab(sample.filePath, sample.displayName, sample.noteFile, language);
                    textPanel.loadTextFromResource(sample.noteFile);
                    RTextHelper.applyRSyntaxTheme(textPanel.getTextArea());
                }
            }
        };
    }

    /**
     * SampleFile is a simple class that holds the display name and file path of a sample file.
     * It is used to represent the files in the JTree.
     * The display name is what will be shown in the tree, while the file path is used to open the file.
     * 
     * @see createSampleTree
     * @see Branch
     */
    private static class SampleFile {
        String displayName;
        String filePath;
        String noteFile;

        SampleFile(String displayName, String filePath, String noteFile) {
            this.displayName = displayName;
            this.filePath = filePath;
            this.noteFile = noteFile;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }

    /**
     * Returns the panel containing the file opener UI.
     * 
     * @return panel: The JPanel containing the file opener UI.
     * @see AppFrame
     */
    public JPanel getPanel() {
        return panel;
    }
}