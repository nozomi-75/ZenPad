package zenpad.runners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TerminalLauncher {
    public static void launchCommand(File workingDir, String command) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder pb;

        if (os.contains("win")) {
            pb = new ProcessBuilder("cmd", "/c", "start", "cmd", "/k", command);
        } else if (os.contains("mac")) {
            String macCommand = (
                "cd '" + workingDir.getAbsolutePath() +
                "' && " + command + "; exec bash"
            );

            pb = new ProcessBuilder(
                "osascript", "-e",
                "tell application \"Terminal\" to do script \"" +
                macCommand.replace("\"", "\\\"") + "\""
            );
        } else {
            String terminal = detectLinuxTerminal();
            pb = new ProcessBuilder(terminal, "-e", "bash -c '" + command + "; exec bash'");
        }

        pb.directory(workingDir);
        pb.start();
    }

    private static String detectLinuxTerminal() {
        List<String> terminals = Arrays.asList(
            "gnome-terminal", "gnome-console", "xfce4-terminal", "konsole", "foot", "lxterminal", "mate-terminal", "alacritty", "xterm"
        );
        for (String term : terminals) {
            try {
                if (new ProcessBuilder("which", term).start().waitFor() == 0) return term;
            } catch (IOException | InterruptedException ignored) {}
        }
        return "x-terminal-emulator";
    }
}
