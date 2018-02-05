package ru.ezhov.components.warning;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * класс, который показывает предупреждения
 * <p>
 *
 * @author ezhov_da
 */
public class WarningWindow extends JWindow {
    /**
     * компонент для отображения иконки
     */
    protected JLabel labelIcon;
    /**
     * компонент для отображения текста
     */
    protected JEditorPane textPane;
    /**
     * панель, в которую все оборачивается
     */
    protected JPanel panel;
    /**
     * расположение
     */
    protected Insets insets = new Insets(5, 5, 5, 5);
    private Timer timer;

    public WarningWindow() {
        init();
    }

    public WarningWindow(GraphicsConfiguration gc) {
        super(gc);
        init();
    }

    public WarningWindow(Frame owner) {
        super(owner);
        init();
    }

    public WarningWindow(Window owner) {
        super(owner);
        init();
    }

    public WarningWindow(Window owner, GraphicsConfiguration gc) {
        super(owner, gc);
        init();
    }

    /**
     * инициализируем компонент
     */
    protected void init() {
        labelIcon = new JLabel();
        textPane = new JTextPane();
        panel = new JPanel(new GridBagLayout());
        panel.add(labelIcon, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, insets, 0, 0));
        panel.add(new JScrollPane(textPane), new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.BOTH, insets, 0, 0));
        textPane.setContentType("text/html");
        add(panel);
    }

    public JLabel getLabelIcon() {
        return labelIcon;
    }

    public JEditorPane getTextPane() {
        return textPane;
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void setVisible(boolean b) {
        timer = new Timer(1000, new ActionListener() {
            int i = 3000;
            int counter = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                counter += 1000;
                if (i == counter) {
                    setVisible(false);
                    timer.stop();
                }
            }
        });
        timer.start();
        super.setVisible(b);
    }
}
