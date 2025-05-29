package zenpad.views;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TabHeaderView {
    private JPanel headerPanel;
    private JLabel titleLabel;
    private JButton closeButton;

    public TabHeaderView(String title) {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setOpaque(false);

        titleLabel = new JLabel(title);
        closeButton = new JButton("×");

        closeButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        closeButton.setBorder(new EmptyBorder(2, 12, 2, 2));
        closeButton.setFocusable(false);
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(closeButton);
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }
}
