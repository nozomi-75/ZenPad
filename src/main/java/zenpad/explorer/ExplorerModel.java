package zenpad.explorer;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import zenpad.branch.Branch;
import zenpad.branch.SampleBranches;

public class ExplorerModel {

    /**
     * Constructs and returns the root node of the explorer tree, including all branches and sample files.
     * <p>
     * The tree structure is based on a list of {@code Branch} objects from {@code SampleBranches}, where each
     * branch corresponds to a category of sample files. Each branch becomes a parent node, and its associated
     * sample files (with optional note files) become child nodes.
     * <p>
     * Each child node contains a {@code SampleFile} object, which stores display name, file path, and note path.
     *
     * @return the root {@code DefaultMutableTreeNode} of the tree hierarchy used in the file explorer
     */
    public DefaultMutableTreeNode getTreeRoot() {
        List<Branch> branches = SampleBranches.getBranches();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        for (Branch branch : branches) {
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(branch.parentName);
            for (int i = 0; i < branch.displayNames.length; i++) {
                String filePath = "samples" + branch.baseDir + branch.fileNames[i];
                String notePath = (branch.noteFiles != null && branch.noteFiles.length > i &&
                                   branch.noteFiles[i] != null && !branch.noteFiles[i].isEmpty())
                                   ? "notes" + branch.baseDir + branch.noteFiles[i]
                                   : null;

                parentNode.add(new DefaultMutableTreeNode(new SampleFile(branch.displayNames[i], filePath, notePath)));
            }
            root.add(parentNode);
        }

        return root;
    }

    /**
     * Represents a sample code file and its associated metadata.
     * <p>
     * This class is used as the user object for {@code DefaultMutableTreeNode} in the explorer tree.
     * </p>
     */
    public static class SampleFile {
        public final String displayName;
        public final String filePath;
        public final String noteFile;

        public SampleFile(String displayName, String filePath, String noteFile) {
            this.displayName = displayName;
            this.filePath = filePath;
            this.noteFile = noteFile;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }
}
