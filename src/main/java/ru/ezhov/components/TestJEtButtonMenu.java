package ru.ezhov.components;

import ru.ezhov.components.jebuttonmenu.JEButtonMenu;

import javax.swing.*;

/**
 * @author ezhov_da
 */
public class TestJEtButtonMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Throwable ex) {
                    //
                }
                JEButtonMenu buttonMenu = new JEButtonMenu(JEButtonMenu.ShowRespect.BASIC_BUTTON);
                buttonMenu.getBasicButton().setText("показать");
                buttonMenu.getShowButton().setIcon(new ImageIcon(TestJEtButtonMenu.class.getResource("/src/main/resources/arrow_state_blue_expanded.png")));
                buttonMenu.getPopupMenu().add(new JMenuItem("test"));
                JFrame frame = new JFrame("_________");
                frame.add(buttonMenu);
                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
