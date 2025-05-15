package zens;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import java.awt.GridLayout;

/**
 * FileOpenerPanel is responsible for creating a panel with buttons to open files.
 * It takes a TabManager object as a parameter to handle the logic for opening files in new tabs.
 * The panel contains a JTree that displays sample files.
 * 
 * @see TabManager
 * @param tabManager The TabManager instance to handle file opening logic.
 */

public class FileOpenerPanel {
    private JPanel panel;
    
    public FileOpenerPanel(TabManager tabManager) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10)); // Arrange buttons in a single row
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(createSampleTree(tabManager));
        scrollPane.setPreferredSize(new java.awt.Dimension(150, 400)); // Adjust width as needed
        panel.add(scrollPane);
    }

    public JPanel getPanel() {
        return panel;
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

        /* The nodes take their names from the brnOneNames array, which is iterated by a loop.
         * The filenames are listed in the brnOneFiles array, which is also iterated by a loop.
         * It is possible to merge two arrays into one (i.e., node name is similar to the file name).
         * Their length should be the same. Otherwise, the program will throw an ArrayIndexOutOfBoundsException.
         * You may add more branches to the tree by adding more array pairs.
         * Adding more branches would require current branch be dissociated from the root node.
         * You would have to add parent nodes to the root node and the branches to the parent nodes.
         */

        String[] brnOneNames = {
            "Sample 1", "Sample 2", "Sample 3", "Sample 4", "Sample 5"
        };

        String[] brnOneFiles = {
            "sample1.txt", "sample2.txt", "sample3.txt", "sample4.txt", "sample5.txt"
        };

        if (brnOneNames.length != brnOneFiles.length) {
            System.err.println("Error: The arrays must have the same length.");
            System.exit(1);
        }

        // Base nodes section, add parent nodes here if necessary
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode parentNodeOne = new DefaultMutableTreeNode("Java Basics");

        // Handle iteration for the first branch
        for (int i = 0; i < brnOneFiles.length; i++) {
            parentNodeOne.add(new DefaultMutableTreeNode(
                new SampleFile(brnOneNames[i], "ent/" + brnOneFiles[i])
            ));
        }

        // Add branches to the root node
        root.add(parentNodeOne);
        
        // Set root node configuration
        JTree tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        /*
         * Adds a TreeSelectionListener to the JTree.
         * When the user selects a node, this listener is triggered.
         * It checks if a valid node is selected and whether its user object is a SampleFile.
         * If so, it retrieves the SampleFile and opens a new tab with the associated file path and display name.
         */
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode == null) return;
                Object userObj = selectedNode.getUserObject();
                if (userObj instanceof SampleFile) {
                    SampleFile sample = (SampleFile) userObj;
                    tabManager.openNewTab(sample.filePath, sample.displayName);
                }
            }
        });

        return tree;
    }

    /**
     * SampleFile is a simple class that holds the display name and file path of a sample file.
     * It is used to represent the files in the JTree.
     * The display name is what will be shown in the tree, while the file path is used to open the file.
     * 
     * @see createSampleTree
     */
    private static class SampleFile {
        String displayName;
        String filePath;
        SampleFile(String displayName, String filePath) {
            this.displayName = displayName;
            this.filePath = filePath;
        }
        @Override
        public String toString() {
            return displayName;
        }
    }
}