package zens;

/**
 * Branch class represents a branch in the file tree structure.
 * It contains the parent name, display names, and file names associated with the branch.
 * 
 * @see FileOpenerPanel
 * @param parentName The name of the parent branch.
 * @param displayNames An array of display names for the files in the branch.
 * @param fileNames An array of file names for the files in the branch.
 */
public class Branch {
        String parentName;
        String[] displayNames;
        String[] fileNames;

        Branch(String parentName, String[] displayNames, String[] fileNames) {
            this.parentName = parentName;
            this.displayNames = displayNames;
            this.fileNames = fileNames;
    }
}
