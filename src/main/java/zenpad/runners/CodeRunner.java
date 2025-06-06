package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import zenpad.misc.factory.DialogFactory;
import zenpad.runners.exec.*;
import zenpad.tab.TabManager;

/**
 * Manages code execution for supported languages.
 * <p>
 * This class serves as a coordinator that maps programming languages to their respective
 * {@link CodeExec} implementations and executes code from the currently selected tab
 * in the application.
 */
public class CodeRunner {
    private final Map<String, CodeExec> executors = new HashMap<>();

    public CodeRunner() {
        executors.put("Java", new JavaExec());
        executors.put("Python", new PythonExec());
        executors.put("C", new ClangExec());
    }

    /**
     * Executes the code in the currently selected tab using the appropriate executor.
     * @param tabManager the {@link TabManager} used to retrieve the selected code and file name
     */
    public void runSelectedTab(TabManager tabManager) {
        String code = tabManager.getSelectedCode();
        String fileName = tabManager.getSelectedFileName();
        String language = inferLanguageFromFileName(fileName);

        if (language == null) {
            DialogFactory.showError("Unsupported file type for execution.", "Error");
            return;
        }
        if (code == null || code.trim().isEmpty()) {
            DialogFactory.showError("No code to run.", "Error");
            return;
        }

        try {
            File tempDir = Files.createTempDirectory("zenpad_run").toFile();
            CodeExec executor = executors.get(language);
            if (executor != null) {
                executor.execute(code, fileName, tempDir);
            } else {
                DialogFactory.showError("No executor available for: " + language, "No executor available");
            }
        } catch (IOException e) {
            DialogFactory.showError("Failed to create temporary directory: " + e.getMessage(), "Error");
        }
    }
    
    /**
     * Infers the programming language from a given file name based on its extension.
     *
     * @param fileName the file name (e.g., {@code HelloWorld.java})
     * @return the inferred language ("Java", "Python", or "C"), or {@code null} if unsupported
     */
    public static String inferLanguageFromFileName(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            return switch (ext) {
                case "java" -> "Java";
                case "py" -> "Python";
                case "c" -> "C";
                default -> null;
            };
        }
        return null;
    }
}
