package zenpad.runners.exec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import zenpad.misc.DialogFactory;
import zenpad.misc.LogUtils;
import zenpad.runners.TerminalLauncher;

public class JavaExec implements CodeExec {
    @Override
    public boolean execute(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".java")) {
            DialogFactory.showError("The file is not a Java (.java) file.", "Error");
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
                DialogFactory.showError("Compilation failed. See compiler-error.log.", "Compilation error");
                return false;
            }

            TerminalLauncher.launchCommand(tempDir, "java -cp . " + className);
            return true;

        } catch (IOException | InterruptedException e) {
            DialogFactory.showError("Error running Java: " + e.getMessage(), "Error");
            return false;
        }
    }
}
