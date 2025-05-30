package zenpad.runners.exec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import zenpad.runners.TerminalLauncher;
import zenpad.utils.ErrDialog;

public class PythonExec implements CodeExec {
    @Override
    public boolean execute(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".py")) {
            ErrDialog.showError("The file is not a Python (.py) file.");
            return false;
        }

        try {
            File script = new File(tempDir, fileName);
            Files.writeString(script.toPath(), code, StandardOpenOption.CREATE);

            TerminalLauncher.launchCommand(tempDir, "python " + fileName);
            return true;

        } catch (IOException e) {
            ErrDialog.showError("Error running Python: " + e.getMessage());
            return false;
        }
    }
}
