package zenpad.runners.exec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import zenpad.runners.TerminalLauncher;
import zenpad.utils.DialogUtils;
import zenpad.utils.LogUtils;

public class ClangExec implements CodeExec {
    @Override
    public boolean execute(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".c")) {
            DialogUtils.showError("The file is not a C (.c) file.", "Error");
            return false;
        }

        try {
            File source = new File(tempDir, fileName);
            Files.writeString(source.toPath(), code, StandardOpenOption.CREATE);

            String exeName = fileName.replace(".c", "");
            String outputName = System.getProperty("os.name").toLowerCase().contains("win")
                ? exeName + ".exe" : exeName;

            Process compile = new ProcessBuilder("gcc", fileName, "-o", outputName)
                .directory(tempDir).start();
            compile.waitFor();

            if (compile.exitValue() != 0) {
                LogUtils.logError(compile.getErrorStream());
                DialogUtils.showError("C compilation failed. See compiler-error.log.", "Compilation error");
                return false;
            }

            TerminalLauncher.launchCommand(tempDir, "./" + outputName);
            return true;

        } catch (IOException | InterruptedException e) {
            DialogUtils.showError("Error running C code: " + e.getMessage(), "Error");
            return false;
        }
    }
}
