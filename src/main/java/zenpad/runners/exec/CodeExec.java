package zenpad.runners.exec;

import java.io.File;

public interface CodeExec {
    boolean execute(String code, String fileName, File tempDir);
}
