package zenpad.misc.factory;

import java.awt.Insets;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import zenpad.toolbar.Toolbar;

/**
 * ButtonFactory provides static factory methods for creating
 * and configuring buttons used in the {@link Toolbar}
 */
public class ButtonFactory {
    public static JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.addActionListener(e -> action.run());
        button.setMargin(new Insets(2, 5, 2, 5));
        return button;
    }

    public static void createToggleButton(String text, Runnable action, Consumer<JToggleButton> assignField, javax.swing.JToolBar toolbar) {
        JToggleButton btn = new JToggleButton(text);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> action.run());
        btn.setMargin(new Insets(2, 5, 2, 5));
        toolbar.add(btn);
        assignField.accept(btn);
    }
}