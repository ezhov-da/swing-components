package ru.ezhov.components;

import ru.ezhov.components.showcomment.JECommentWindow;

import javax.swing.*;

/**
 * класс для тестирования компонента комментария
 * <p>
 *
 * @author ezhov_da
 */
public class TestJECommentWindow {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JECommentWindow frame = new JECommentWindow();
                frame.getLabel().setText("ура, у нас получилось");
                frame.getLabel().setSize(140, 50);
                frame.setSize(200, 100);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }

}
