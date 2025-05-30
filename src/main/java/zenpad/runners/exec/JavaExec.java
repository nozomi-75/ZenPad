package zenpad.runners.exec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import zenpad.runners.TerminalLauncher;
import zenpad.utils.ErrDialog;
import zenpad.utils.LogUtils;

public class JavaExec implements CodeExec {
    @Override
    public boolean execute(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".java")) {
            ErrDialog.showError("The file is not a Java (.java) file.");
            return false;
        }

        try {
            String className = fileName.replace(".java", "");
            File javaFile = new File(tempDir, fileName);
            Files.writeString(javaFile.toPath(), code, StandardOpenOption.CREATE);

            Process compile = new ProcessBuilder("javac", javaFile.getName())
                .directory(tempDir).start();
            compile.waitFor();

            if (compile.exitValue() != 0) {
                LogUtils.logError(compile.getErrorStream());
                ErrDialog.showError("Compilation failed. See compiler-error.log.");
                return false;
            }

            TerminalLauncher.launchCommand(tempDir, "java -cp . " + className);
            return true;

        } catch (IOException | InterruptedException e) {
            ErrDialog.showError("Error running Java: " + e.getMessage());
            return false;
        }
    }
}
