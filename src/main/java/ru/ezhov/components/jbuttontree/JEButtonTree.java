package ru.ezhov.components.jbuttontree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

/**
 * @author ezhov_da
 */
public class JEButtonTree extends JButton {
    private static final Logger LOG = Logger.getLogger(JEButtonTree.class.getName());
    protected JPopupMenu popupMenu = new JPopupMenu();
    protected JTree tree = new JTree();
    protected ActionListener actionListenerShowMenu = new ActionListenerShowMenu();
    protected JRadioButton radioButtonOneEmployee = new JRadioButton("Выбор сотрудника");
    protected JRadioButton radioButtonManyEmployee = new JRadioButton("Выбор подразделения");
    protected JButton clearSelect = new JButton("Сбросить выделения");
    protected JButton clearOk = new JButton();
    private final JPanel panelBasic = new JPanel(new BorderLayout());
    private JPanel panelCenter;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    public JEButtonTree() {
        addActionListener(actionListenerShowMenu);
    }

    public void addTreeToPanel() {
        popupMenu.setLayout(new BorderLayout());
        panelCenter = new JPanel(new BorderLayout());
        panelCenter.add(new JScrollPane(tree), BorderLayout.CENTER);
        //
        JPanel panel = new JPanel();
        panel.add(radioButtonOneEmployee);
        panel.add(radioButtonManyEmployee);
        buttonGroup.add(radioButtonOneEmployee);
        buttonGroup.add(radioButtonManyEmployee);
        radioButtonOneEmployee.setSelected(true);
        panelCenter.add(panel, BorderLayout.NORTH);
        //
        JPanel panelClear = new JPanel();
        panelClear.add(clearOk);
        panelClear.add(clearSelect);
        panelCenter.add(panelClear, BorderLayout.SOUTH);
        //
        panelBasic.add(panelCenter, BorderLayout.CENTER);
        popupMenu.add(panelBasic, BorderLayout.CENTER);
    }


    private class ActionListenerShowMenu implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            popupMenu.show(JEButtonTree.this, 0, JEButtonTree.this.getHeight());
        }

    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public void setPopupMenu(JPopupMenu popupMenu) {
        this.popupMenu = popupMenu;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }

    public ActionListener getActionListenerShowMenu() {
        return actionListenerShowMenu;
    }

    public void setActionListenerShowMenu(ActionListener actionListenerShowMenu) {
        this.actionListenerShowMenu = actionListenerShowMenu;
    }

    public JPanel getPanelBasic() {
        return panelBasic;
    }

    public JButton getClearSelect() {
        return clearSelect;
    }

    public JButton getClearOk() {
        return clearOk;
    }
}
