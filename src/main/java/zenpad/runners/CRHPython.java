package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JOptionPane;

/**
 * PythonCodeExecutor is responsible for running Python code.
 * It handles the creation of a temporary Python file and its execution
 * in the appropriate terminal based on the operating system.
 */
public class CRHPython {

    private CodeRunner codeRunnerInstance; // To access detectLinuxTerminal and isCommandAvailable

    /**
     * Constructor for PythonCodeExecutor.
     * @param codeRunnerInstance An instance of CodeRunner to access shared utility methods.
     */
    public CRHPython(CodeRunner codeRunnerInstance) {
        this.codeRunnerInstance = codeRunnerInstance;
    }

    /**
     * Executes the provided Python code.
     *
     * @param code The Python code to be executed.
     * @param fileName The name of the Python file (e.g., "my_script.py").
     * @param tempDir The temporary directory where the file will be created and run.
     * @return true if the code was successfully executed, false otherwise.
     */
    public boolean executePythonCode(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".py")) {
            JOptionPane.showMessageDialog(null, "The file is not a Python (.py) file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            File pythonFile = new File(tempDir, fileName);
            Files.writeString(pythonFile.toPath(), code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder runProcessBuilder;

            if (os.contains("win")) {
                runProcessBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                    "python", pythonFile.getName());
            } else if (os.contains("mac")) {
                String command = "python " + pythonFile.getName() + "; exec bash";
                runProcessBuilder = new ProcessBuilder("osascript", "-e",
                    "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "\"");
            } else { // Assuming Linux for any other OS
                String terminal = codeRunnerInstance.detectLinuxTerminal();
                String bashCommand = "bash -c 'python " + pythonFile.getName() + "; exec bash'";
                runProcessBuilder = new ProcessBuilder(terminal, "-e", bashCommand);
            }

            // Now, we are certain runProcessBuilder has been initialized
            runProcessBuilder.directory(tempDir);
            System.out.println("Running Python: " + String.join(" ", runProcessBuilder.command()));
            runProcessBuilder.start();
            return true;

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while running the Python code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}