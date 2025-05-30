package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import zenpad.runners.exec.*;
import zenpad.ui.TabManager;
import zenpad.utils.ErrDialog;

public class CodeRunner {
    private final Map<String, CodeExec> executors = new HashMap<>();

    public CodeRunner() {
        executors.put("Java", new JavaExec());
        executors.put("Python", new PythonExec());
        executors.put("C", new ClangExec());
    }

    public void runSelectedTab(TabManager tabManager) {
        String code = tabManager.getSelectedCode();
        String fileName = tabManager.getSelectedFileName();
        String language = inferLanguageFromFileName(fileName);

        if (language == null) {
            ErrDialog.showError("Unsupported file type for execution.");
            return;
        }
        if (code == null || code.trim().isEmpty()) {
            ErrDialog.showError("No code to run.");
            return;
        }

        try {
            File tempDir = Files.createTempDirectory("zenpad_run").toFile();
            CodeExec executor = executors.get(language);
            if (executor != null) {
                executor.execute(code, fileName, tempDir);
            } else {
                ErrDialog.showError("No executor available for: " + language);
            }
        } catch (IOException e) {
            ErrDialog.showError("Failed to create temporary directory: " + e.getMessage());
        }
    }

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
