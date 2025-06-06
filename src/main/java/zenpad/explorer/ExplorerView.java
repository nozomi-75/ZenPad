package zenpad.explorer;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class ExplorerView {
    private JPanel panel;
    private JTree tree;

    public ExplorerView(DefaultMutableTreeNode treeRoot) {
        panel = new JPanel(new GridLayout(0, 1, 10, 10));
        panel.setBorder(new EmptyBorder(5, 5, 5, 2));

        tree = new JTree(treeRoot);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        panel.add(new JScrollPane(tree));
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTree getTree() {
        return tree;
    }
}
