package ru.ezhov.components;

import ru.ezhov.components.warning.WarningWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author ezhov_da
 */
public class TestWarningWindow {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("_________");
                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JButton buttonTest = new JButton("test show WarningWindow");
                final WarningWindow warningWindow = new WarningWindow(frame);
                JLabel label = warningWindow.getLabelIcon();
                label.setIcon(new ImageIcon(TestWarningWindow.class.getResource("/exclamation.png")));
                JEditorPane textPane = warningWindow.getTextPane();
                JPanel panel = warningWindow.getPanel();
                textPane.setText("тестируем отображаения окна сообщения");
                textPane.setEditable(false);
                textPane.setBorder(null);
                frame.add(buttonTest);
                warningWindow.setSize(300, 100);
                warningWindow.setLocationRelativeTo(buttonTest);
                panel.setBorder(BorderFactory.createLineBorder(Color.black));
                buttonTest.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        Dimension dimension = button.getSize();
                        Point point = button.getLocationOnScreen();
                        warningWindow.setLocation(point.x + dimension.width, point.y);
                        warningWindow.setVisible(true);
                    }
                });
                frame.setVisible(true);
            }
        });
    }
}
