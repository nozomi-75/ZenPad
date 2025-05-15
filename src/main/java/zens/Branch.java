package zens;

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
