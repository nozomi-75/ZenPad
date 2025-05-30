package zenpad.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class LogUtils {
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
