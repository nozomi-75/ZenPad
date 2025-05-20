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
     * Compiles and runs the provided code in the selected language.
     *
     * @param code The code to be compiled and run.
     * @param fileName The name of the file to be created (with extension).
     * @param language The programming language selected.
     */
    public void runCode(String code, String fileName, String language) {
        try {
            File tempDir = Files.createTempDirectory("samples").toFile();
            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder runProcess = null;

            switch (language) {
                case "Java": {
                    String className = fileName.substring(0, fileName.lastIndexOf('.'));
                    File javaFile = new File(tempDir, className + ".java");
                    Files.writeString(javaFile.toPath(), code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

                    Process compileProcess = new ProcessBuilder("javac", javaFile.getName())
                        .directory(tempDir)
                        .start();
                    compileProcess.waitFor();

                    if (compileProcess.exitValue() != 0) {
                        JOptionPane.showMessageDialog(null, "Compilation failed. Please check your code.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (os.contains("win")) {
                        runProcess = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                            "java", "-cp", tempDir.getAbsolutePath(), className);
                    } else if (os.contains("mac")) {
                        String command = "java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash";
                        runProcess = new ProcessBuilder("osascript", "-e",
                            "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "\"");
                    } else {
                        String terminal = detectLinuxTerminal();
                        String bashCommand = "bash -c 'java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash'";
                        runProcess = new ProcessBuilder(terminal, "-e", bashCommand);
                    }
                    break;
                }
                case "Python": {
                    JOptionPane.showMessageDialog(null, "Logic not yet added: " + language, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                case "C": {
                    JOptionPane.showMessageDialog(null, "Logic not yet added: " + language, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Unsupported language: " + language, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            if (runProcess != null) {
                runProcess.directory(tempDir);
                System.out.println("Running: " + String.join(" ", runProcess.command()));
                runProcess.start();
            }
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