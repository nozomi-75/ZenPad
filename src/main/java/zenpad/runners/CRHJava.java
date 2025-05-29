package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import javax.swing.JOptionPane;

/**
 * JavaCodeExecutor is responsible for compiling and running Java code.
 * It handles the creation of a temporary Java file, compilation, and execution
 * in the appropriate terminal based on the operating system.
 */
public class CRHJava {
    private CodeRunner codeRunnerInstance;

    /**
     * Constructor for JavaCodeExecutor.
     * @param codeRunnerInstance An instance of CodeRunner to access shared utility methods.
     */
    public CRHJava(CodeRunner codeRunnerInstance) {
        this.codeRunnerInstance = codeRunnerInstance;
    }

    /**
     * Compiles and runs the provided Java code.
     *
     * @param code The Java code to be compiled and run.
     * @param fileName The name of the Java file (e.g., "MyProgram.java").
     * @param tempDir The temporary directory where the file will be created and run.
     * @return true if the code was successfully compiled and run, false otherwise.
     */
    public boolean executeJavaCode(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".java")) {
            JOptionPane.showMessageDialog(null, "The file is not a Java (.java) file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            String className = fileName.substring(0, fileName.lastIndexOf('.'));
            File javaFile = new File(tempDir, className + ".java");
            Files.writeString(javaFile.toPath(), code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            Process compileProcess = new ProcessBuilder("javac", javaFile.getName())
                .directory(tempDir)
                .start();
            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                JOptionPane.showMessageDialog(null, "Compilation failed. Please check your code.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String os = System.getProperty("os.name").toLowerCase();
            ProcessBuilder runProcessBuilder;

            if (os.contains("win")) {
                runProcessBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k",
                    "java", "-cp", tempDir.getAbsolutePath(), className);
            } else if (os.contains("mac")) {
                String command = "java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash";
                runProcessBuilder = new ProcessBuilder("osascript", "-e",
                    "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "\"");
            } else { // Assuming Linux for any other OS
                String terminal = codeRunnerInstance.detectLinuxTerminal();
                String bashCommand = "bash -c 'java -cp " + tempDir.getAbsolutePath() + " " + className + "; exec bash'";
                runProcessBuilder = new ProcessBuilder(terminal, "-e", bashCommand);
            }

            // Now, we are certain runProcessBuilder has been initialized
            runProcessBuilder.directory(tempDir);
            System.out.println("Running: " + String.join(" ", runProcessBuilder.command()));
            runProcessBuilder.start();
            return true;

        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while running the code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}