import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

/**
 * FileOpenerPanel is responsible for creating a panel with buttons to open files.
 * It takes a TabManager object as a parameter to handle the logic for opening files in new tabs.
 * The panel contains buttons that, when clicked, will open the corresponding file in a new tab.
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

        /* The buttons take their names from the buttonName array, which is iterated by a loop.
         * The filenames are listed in the fileName array, which is also iterated by a loop.
         * It is possible to merge two arrays into one (i.e., button name is similar to the file name).
         * Their length should be the same. Otherwise, the program will throw an ArrayIndexOutOfBoundsException.
         */

        String[] buttonName = {
            "Sample 1", "Sample 2", "Sample 3", "Sample 4", "Sample 5",
            "Sample 6", "Sample 7", "Sample 8", "Sample 9", "Sample 10"
        };

        String[] fileName = {
            "sample1.txt", "sample2.txt", "sample3.txt", "sample4.txt", "sample5.txt",
            "sample6.txt", "sample7.txt", "sample8.txt", "sample9.txt", "sample10.txt"
        };

        /* Check if the lengths of buttonName and fileName arrays are equal
         * Should abruptly terminate the program if otherwise
         */

        if (buttonName.length != fileName.length) {
            System.err.println("Error: buttonName and fileName arrays must have the same length.");
            System.exit(1);
        }

        /* Loop through above arrays while creating buttons
         * The button name is taken from the buttonName array
         * The file name is taken from the fileName array
         * The file name is passed to the TabManager to open the file
         * The button name is passed to the TabManager to set the tab title
         * The file name is prefixed with "ent/" to resolve full directory path
         */

        for (int i = 0; i < fileName.length; i++) {
            String fileToCall = fileName[i];
            String button = buttonName[i];

            // Create buttons with the name from the array
            JButton fileButton = new JButton(button);
            fileButton.setFocusPainted(false);

            // Add action listener to the button
            fileButton.addActionListener(e -> {
                tabManager.openNewTab("ent/" + fileToCall, button);
            });

            panel.add(fileButton);
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}