package zenpad;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import zenpad.ui.TabManager;

import java.awt.Font;

public class TabHeader {
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JButton closeButton;
    private TabManager tabManager;

    public TabHeader(String button, JTabbedPane tabbedPane, JPanel editorPanel, TabManager tabManager) {
        this.tabManager = tabManager;
        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setOpaque(false);

        titleLabel = new JLabel(button);
        closeButton = createCloseButton(tabbedPane, editorPanel);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(closeButton);
    }

    /**
     * Creates a close button for the tab header.
     * The button is styled to look like a close icon and has an action listener to remove the tab when clicked.
     *
     * @param tabbedPane The JTabbedPane instance to manage the tabs.
     * @param editorPanel The JPanel representing the editor panel associated with this tab.
     * @return A JButton configured as a close button.
     * 
     * @see TabManager
     */
    private JButton createCloseButton(JTabbedPane tabbedPane, JPanel editorPanel) {
        JButton closeButton = new JButton("×");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        closeButton.setBorder(new EmptyBorder(2, 12, 2, 2));
        closeButton.setFocusable(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);

        closeButton.addActionListener(e -> {
            int index = tabbedPane.indexOfComponent(editorPanel);
            if (index != -1) {
                tabManager.closeTab(index);
            }
        });

        return closeButton;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }
}