package zenpad.runners.exec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import zenpad.misc.DialogFactory;
import zenpad.runners.TerminalLauncher;

public class PythonExec implements CodeExec {
    @Override
    public boolean execute(String code, String fileName, File tempDir) {
        if (!fileName.endsWith(".py")) {
            DialogFactory.showError("The file is not a Python (.py) file.", "Error");
            return false;
        }

        try {
            File script = new File(tempDir, fileName);
            Files.writeString(script.toPath(), code, StandardOpenOption.CREATE);

            TerminalLauncher.launchCommand(tempDir, "python3 " + fileName);
            return true;

        } catch (IOException e) {
            DialogFactory.showError("Error running Python: " + e.getMessage(), "Error");
            return false;
        }
    }
}
