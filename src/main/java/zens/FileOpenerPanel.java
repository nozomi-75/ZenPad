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
import java.util.Arrays;
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
    private JPanel panel;
    
    public FileOpenerPanel(TabManager tabManager) {
        panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(createSampleTree(tabManager));
        scrollPane.setPreferredSize(new java.awt.Dimension(200, 400)); // Adjust width as needed
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

        List<Branch> branches = getBranches();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        for (Branch branch : branches) {

            // Abruptly exit if branch array pairs are not the same length
            if (branch.displayNames.length != branch.fileNames.length) {
                System.err.println("Error: The arrays for branch '" + branch.parentName + "' do not match in length.");
                System.exit(1);
            }

            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(branch.parentName);
            for (int i = 0; i < branch.displayNames.length; i++) {
                parentNode.add(new DefaultMutableTreeNode(
                    new SampleFile(
                        branch.displayNames[i],
                        "samples/" + branch.fileNames[i]
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
     * Returns a list of branches, each containing display names and file names.
     * This method is used to populate the JTree with sample files.
     * 
     * @return List of Branch objects representing the sample files.
     */
    private List<Branch> getBranches() {
        return Arrays.asList(
            new Branch(
                "Java Basics",
                new String[] { "Hello world", "Data types", "Type casting", "True or false" },
                new String[] { "HelloWorld.java", "PrintData.java", "TypeCast.java", "Boolean.java" }
            ),

            new Branch(
                "Java Operators",
                new String[] { "Arithmetic operators" },
                new String[] { "ArithmeticOperators.java" }
            ),

            new Branch(
                "Java Conditions",
                new String[] { "Ternary operator", "Traditional switch", "Rule switch" },
                new String[] { "Ternary.java", "Switch.java", "RuleSwitch.java" }
            ),

            new Branch(
                "Java Loops",
                new String[] { "For loop", "While loop", "Do-while loop", "Nested loops" },
                new String[] { "ForLoop.java", "While.java", "DoWhile.java", "NestedLoop.java" }
            ),

            new Branch(
                "Complex Loops",
                new String[] { "Floyd's triangle", "Multiplication table", "Right triangle", "Sum of a matrix", "Coordinates" },
                new String[] { "Floyd.java", "MultiplicationTable.java", "RightTriangle.java", "MatrixSum.java", "PlotCoordinates.java" }
            )
            // Add more branches here as needed
            // new Branch("Another Branch", new String[] {...}, new String[] {...})
        );
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

        SampleFile(String displayName, String filePath) {
            this.displayName = displayName;
            this.filePath = filePath;
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