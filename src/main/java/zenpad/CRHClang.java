package zenpad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javax.swing.JOptionPane;

public class CRHClang {
    private CodeRunner codeRunnerInstance;

    public CRHClang(CodeRunner codeRunnerInstance) {
        this.codeRunnerInstance = codeRunnerInstance;
    }

    public boolean executeCCode(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".c")) {
            JOptionPane.showMessageDialog(null, "The file is not a C (.c) file.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            File cFile = new File(tempDir, fileName);
            Files.writeString(cFile.toPath(), code, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // Compile the C file
            String exeName = fileName.substring(0, fileName.lastIndexOf('.'));
            String os = System.getProperty("os.name").toLowerCase();
            String outputName = os.contains("win") ? exeName + ".exe" : exeName;

            Process compileProcess = new ProcessBuilder("gcc", cFile.getName(), "-o", outputName)
                .directory(tempDir)
                .start();
            compileProcess.waitFor();

            if (compileProcess.exitValue() != 0) {
                String err = new String(compileProcess.getErrorStream().readAllBytes());
                JOptionPane.showMessageDialog(null, "Compilation failed:\n" + err, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            ProcessBuilder runProcessBuilder;
            if (os.contains("win")) {
                runProcessBuilder = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", outputName);
            } else if (os.contains("mac")) {
                String command = "./" + outputName + "; exec bash";
                runProcessBuilder = new ProcessBuilder("osascript", "-e",
                    "tell application \"Terminal\" to do script \"" + command.replace("\"", "\\\"") + "\"");
            } else { // Linux
                String terminal = codeRunnerInstance.detectLinuxTerminal();
                String bashCommand = "bash -c './" + outputName + "; exec bash'";
                runProcessBuilder = new ProcessBuilder(terminal, "-e", bashCommand);
            }

            runProcessBuilder.directory(tempDir);
            System.out.println("Running C: " + String.join(" ", runProcessBuilder.command()));
            runProcessBuilder.start();
            return true;

        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while running the C code: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
