package ru.ezhov.components.jebuttonmenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author ezhov_da
 */
public class JEButtonMenu extends JToolBar {

    protected JButton basicButton;
    protected JButton showButton;
    protected JPopupMenu popupMenu;
    protected ShowRespect showResprect;

    private List<JButton> buttonAddinButton;

    public enum ShowRespect {
        BASIC_BUTTON, SHOW_BUTTON
    }

    public JEButtonMenu(ShowRespect showRespect) {
        this.showResprect = showRespect;
        init();
    }

    /**
     * @param showRespect
     * @param buttonAddinButton - массив кнопок, которые добавляются в начало
     */
    public JEButtonMenu(ShowRespect showRespect, List<JButton> buttonAddinButton) {
        this.showResprect = showRespect;
        this.buttonAddinButton = buttonAddinButton;
        init();
    }

    protected void init() {
        basicButton = new JButton();
        basicButton.setLayout(new BorderLayout());
        showButton = new JButton();
        popupMenu = new JPopupMenu();
        showButton.addActionListener(new ListenerShowPopupMenu());

        if (buttonAddinButton != null && !buttonAddinButton.isEmpty()) {
            for (JButton button : buttonAddinButton) {
                add(button);
            }
        }

        add(basicButton);
        add(showButton);
        setFloatable(false);
    }

    public JButton getBasicButton() {
        return basicButton;
    }

    public JButton getShowButton() {
        return showButton;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    private class ListenerShowPopupMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (showResprect) {
                case BASIC_BUTTON:
                    popupMenu.show(basicButton, 0, basicButton.getHeight());
                    break;
                case SHOW_BUTTON:
                    popupMenu.show(showButton, 0, showButton.getHeight());
                    break;
            }
        }

    }

}
