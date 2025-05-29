package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JOptionPane;

import zenpad.core.Toolbar;
import zenpad.ui.EditorTab;
import zenpad.ui.TabManager;

/**
 * CodeRunner is responsible for compiling and running Java code.
 * It creates a temporary Java file, compiles it, and executes it in the appropriate terminal.
 * The class detects the operating system to determine the correct terminal to use.
 * 
 * @see EditorTab
 * @see Toolbar
 */

public class CodeRunner {
    public static String inferLanguageFromFileName(String fileName) {
        if (fileName != null) {
            int dot = fileName.lastIndexOf('.');
            String ext = (dot != -1) ? fileName.substring(dot + 1).toLowerCase() : "";
            switch (ext) {
                case "java": return "Java";
                case "py": return "Python";
                case "c": return "C";
                default: return null;
            }
        }
        return null;
    }

    public void runSelectedTab(TabManager tabManager) {
        String code = tabManager.getSelectedCode();
        String fileName = tabManager.getSelectedFileName();
        String language = inferLanguageFromFileName(fileName);

        if (language == null) {
            JOptionPane.showMessageDialog(null, "Unsupported file type for execution.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (code == null || code.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No code to run.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        runCode(code, fileName, language);
    }

    public void runCode(String code, String fileName, String language) {
        try {
            File tempDir = Files.createTempDirectory("samples").toFile();

            switch (language) {
                case "Java": {
                    CRHJava javaExec = new CRHJava(this);
                    javaExec.executeJavaCode(code, fileName, tempDir);
                    break;
                }
                case "Python": {
                    CRHPython pyExec = new CRHPython(this);
                    pyExec.executePythonCode(code, fileName, tempDir);
                    break;
                }
                case "C": {
                    CRHClang cExec = new CRHClang(this);
                    cExec.executeCCode(code, fileName, tempDir);
                    break;
                }
                default:
                    JOptionPane.showMessageDialog(null, "Unsupported language: " + language, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
        } catch (IOException e) {
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
    public String detectLinuxTerminal() {
        String [] terminals = {
            "gnome-terminal", "gnome-console", "konsole", "guake", "quake", "mate-terminal", "xfce4-terminal",
            "lxterminal", "alacritty", "tilix", "ghostty", "wezterm", "terminator", "xterm", "foot"
        };

        for (String term : terminals) {
            if (isCommandAvailable(term)) {
                return term;
            }
        }

        return "x-terminal-emulator";
    }

    /**
     * Checks if a command is available in the system's PATH.
     * @param command The command to check for availability.
     * @return true if the command is available, false otherwise.
     */
    public boolean isCommandAvailable(String command) {
        try {
            Process findCommand = new ProcessBuilder("which", command).start();
            return findCommand.waitFor() == 0;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}