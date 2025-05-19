package zenpad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JOptionPane;

/**
 * CodeRunner is responsible for compiling and running Java code.
 * It creates a temporary Java file, compiles it, and executes it in the appropriate terminal.
 * The class detects the operating system to determine the correct terminal to use.
 * 
 * @see EditorTab
 * @see Toolbar
 */

public class CodeRunner {

    /**
     * Compiles and runs the provided Java code.
     * <p>
     * This method creates a temporary Java file, compiles it, and executes it in the appropriate terminal.
     * It detects the operating system to determine the correct terminal to use.
     * </p>
     *
     * @param code The Java code to be compiled and run.
     * @param fileName The name of the file to be created (without extension).
     */

    public void runCode(String code, String fileName) {
        try {
            // Extract the class name from the file name
            String className = fileName.substring(0, fileName.lastIndexOf('.'));

            // Create a temporary Java file
            // Write the code to the file
            File tempDir = Files.createTempDirectory("java_samples").toFile();
            File javaFile = new File(tempDir, className + ".java");

            Files.writeString(javaFile.toPath(), code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            Process compileProcess = new ProcessBuilder("javac", javaFile.getName())
                .directory(tempDir)
                .start();
            compileProcess.waitFor();

            // Check if the compilation was successful
            if (compileProcess.exitValue() != 0) {
                JOptionPane.showMessageDialog(null, "Compilation failed. Please check your code.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Detect the OS and run in appropriate terminal
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder runProcess;

            if (os.contains("win")) {
                // Windows: Open in Command Prompt
                runProcess = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                    "java", "-cp", tempDir.getAbsolutePath(), className);
            } else if (os.contains("mac")) {
                String command = "java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash";
                runProcess = new ProcessBuilder("osascript", "-e",
                    "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "\"");
            } else {
                // Linux: Try various terminals or fallback to common defaults
                String terminal = detectLinuxTerminal();
                String bashCommand = "bash -c 'java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash'";
                runProcess = new ProcessBuilder(terminal, "-e", bashCommand);
            }

            runProcess.directory(tempDir);
            // Debug print: show the command being run
            System.out.println("Running: " + String.join(" ", runProcess.command()));
            runProcess.start();
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while running the code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Detects the appropriate terminal for Linux systems.
     * <p>
     * This method checks for various common terminal applications and returns the first one found.
     * If none are found, it falls back to a default terminal.
     *
     * @return The name of the detected terminal.
     */

    private String detectLinuxTerminal() {
        String [] terminals = {
            "gnome-terminal",
            "gnome-console",
            "konsole",
            "guake",
            "quake",
            "mate-terminal",
            "xfce4-terminal",
            "lxterminal",
            "alacritty",
            "tilix",
            "ghostty",
            "wezterm",
            "terminator",
            "xterm"
        };

        for (String term : terminals) {
            if (isCommandAvailable(term)) {
                return term;
            }
        }

        // Fallback to the default terminal if not found yet
        return "x-terminal-emulator";
    }

    /**
     * Checks if a command is available in the system's PATH.
     * <p>
     * This method uses the 'which' command to check if the specified command is available.
     * </p>
     *
     * @param command The command to check for availability.
     * @return true if the command is available, false otherwise.
     * @see #detectLinuxTerminal()
     * @see #runCode(String)
     */

    private boolean isCommandAvailable(String command) {
        try {
            Process findCommand = new ProcessBuilder("which", command).start();
            return findCommand.waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            // Command not found
            return false;
        }
    }
}