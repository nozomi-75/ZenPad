package zenpad.models;

import zenpad.ui.FileOpenerPanel;

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
    String baseDir;
    String[] displayNames;
    String[] fileNames;
    String[] noteFiles;

    Branch(String parentName, String baseDir, String[] displayNames, String[] fileNames, String[] noteFiles) {

        if (displayNames.length != fileNames.length) {
            throw new InvalidBranchException(
                "Branch '" + parentName + "' must have displayNames and fileNames arrays of the same length."
            );
        }
        
        if (displayNames.length == 0 || fileNames.length == 0) {
            throw new InvalidBranchException(
                "Branch '" + parentName + "' must have at least one displayName and fileName."
            );
        }
        
        if (parentName == null || parentName.isEmpty()) {
            throw new InvalidBranchException(
                "Branch name cannot be null or empty."
            );
        }

        this.parentName = parentName;
        this.baseDir = baseDir;
        this.displayNames = displayNames;
        this.fileNames = fileNames;
        this.noteFiles = noteFiles;
    }
    
    private static class InvalidBranchException extends RuntimeException {
        public InvalidBranchException(String message) {
            super(message);
        }
    }
}
