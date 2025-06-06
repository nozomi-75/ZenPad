package zenpad.misc.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Utility class for logging compiler or runtime error messages to a log file.
 * <p>
 * This class is typically used to capture and persist error output (e.g., from {@code Process.getErrorStream()})
 * to a file named {@code compiler-error.log} in the current working directory.
 */
public class LogUtility {
    public static void logError(InputStream errorStream) {
        try {
            String message = new String(errorStream.readAllBytes());
            File log = new File("compiler-error.log");
            Files.writeString(log.toPath(), message, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error writing to log: " + e.getMessage());
        }
    }
}
