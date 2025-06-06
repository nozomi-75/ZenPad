package zenpad.code;

import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import zenpad.tab.TabManager;
import zenpad.tabheader.TabHeader;;

public class CodeController {
    private CodeModel model;
    private CodeView view;
    private TabHeader tabHeader;

    public CodeController(CodeModel model, CodeView view, JTabbedPane tabbedPane, String node, TabManager tabManager) {
        this.model = model;
        this.view = view;
        this.tabHeader = new TabHeader(node, tabbedPane, view.getPanel(), tabManager);
        loadFileContentAsync();
    }

    /**
     * Loads file content asynchronously using a {@link SwingWorker} to prevent thread-blocking.
     * <ul>
     *   <li><b>doInBackground:</b> Reads the content from the model and publishes it to the UI thread.</li>
     *   <li><b>process:</b> Receives content or error message and updates the view accordingly.</li>
     * </ul>
     * 
     * @see CodeModel#loadFileContent()
     */
    private void loadFileContentAsync() {
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String content = model.loadFileContent();
                    publish(content);
                } catch (Exception e) {
                    publish("Error: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                if (!chunks.isEmpty() && chunks.get(0).startsWith("Error:")) {
                    view.setContent(chunks.get(0));
                } else {
                    for (String chunk : chunks) {
                        view.appendContent(chunk);
                    }
                }
            }
        }.execute();
    }

    // Public accessors
    public CodeModel getModel() { return model; }
    public CodeView getView() { return view; }
    public TabHeader getTabHeader() { return tabHeader; }
}
