package zenpad.models;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

public class FileOpenerModel {

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
