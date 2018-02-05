package ru.ezhov.components.jefilterTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * компонент расширенное дерево
 * <p>
 *
 * @author ezhov_da
 */
public class JEFilterTree extends JPanel {
    //КОМПОНЕНТЫ------------------------------------------------------------------------------------------------------------------
    /**
     * список для вывода данных
     */
    protected JList listForDataShow;
    /**
     * пролистывание дерева
     */
    protected JScrollPane scrollPaneListForData;
    /**
     * кнопка для открытия дерева
     */
    protected JButton buttonOpenTree;
    /**
     * меню с деревом
     */
    protected JPopupMenu popupMenu;
    /**
     * дерево
     */
    protected JTree tree;
    /**
     * кнопка применить
     */
    protected JButton buttonCommit;
    /**
     * кнопка отменить
     */
    protected JButton buttonCancel;
    /**
     * кнопка отменить
     */
    protected JButton buttonResetSelectedList;
    /**
     * список добавленных
     */
    protected JList listAddData;
    /**
     * пролистывание списка
     */
    protected JScrollPane scrollPaneList;
    /**
     * пролистывание дерева
     */
    protected JScrollPane scrollPaneTree;
    /**
     * разделительная панель для компонентов
     */
    protected JSplitPane splitPane;
    protected int widthPopupMenuDefault = 200;
    protected JPanel panelForPopupMenu;
    /**
     * ширина выпадающего меню
     * если 0, тогда по умолчанию ширина компонента + widthPopupMenuDefault
     */
    private int widthPopupMenu;
    protected int heightPopupMenuDefault = 200;
    private List<ActionTreeListListener> actionTreeListListeners;
    /**
     * высота выпадающего меню
     * если 0, тогда по умолчанию heightPopupMenuDefault
     */
    private int heightPopupMenu;
    /**
     * насколько вниз отображать меню от текстового поля
     */
    protected int heightDown = 5;
    /**
     * слушатель на выбор в компонентах дерева и списка
     */
    private final SelectionListenerTreeList selectionListenerTreeList =
            new SelectionListenerTreeList();
    protected JPanel panelBottom;
    protected JPanel panelNorth;

    public JEFilterTree() {
        init();
    }

    /**
     * инициализируем класс
     */
    private void init() {
        initComponent(); //инициализируем компоненты
        initLayout(); //распологаем их как нам необходимо
        setListeners(); //ставим слушателей на элементы
    }

    protected void initComponent() {
        listForDataShow = new JList();
        listForDataShow.setLayoutOrientation(JList.VERTICAL_WRAP);
        listForDataShow.setVisibleRowCount(0);
        scrollPaneListForData = new JScrollPane();
        buttonOpenTree = new JButton();
        popupMenu = new JPopupMenu();
        tree = new JTree();
        buttonCommit = new JButton();
        buttonCancel = new JButton();
        listAddData = new JList();
        scrollPaneList = new JScrollPane();
        scrollPaneTree = new JScrollPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        buttonResetSelectedList = new JButton();
    }

    /**
     * располагаем элементы
     */
    protected void initLayout() {
        //инициализируем основную панель текстовым полем и кнопкой
        initBasicPanel();
        //инициализируем меню
        initMenuPanel();
    }

    protected void initBasicPanel() {
        setLayout(new BorderLayout());
        scrollPaneListForData.setViewportView(listForDataShow);
        add(scrollPaneListForData, BorderLayout.CENTER);
        add(buttonOpenTree, BorderLayout.EAST);
    }

    protected void initMenuPanel() {
        panelForPopupMenu = new JPanel(new BorderLayout()); //создаем основное меню для фильтра
        panelBottom = new JPanel(); //создаем меню для кнопок работы
        scrollPaneTree.setViewportView(tree);
        splitPane.setLeftComponent(scrollPaneTree);
        splitPane.setRightComponent(getPanelSelectedList());
        panelForPopupMenu.add(splitPane, BorderLayout.CENTER);
        //пока убрал кнопки применить и отменить, так как они не нужны
        panelForPopupMenu.add(panelBottom, BorderLayout.SOUTH);
        getPanelNorth();
        panelForPopupMenu.add(panelNorth, BorderLayout.NORTH);
        popupMenu.add(panelForPopupMenu);
    }

    protected void getPanelNorth() {
        panelNorth = new JPanel();
    }

    protected JPanel getPanelSelectedList() {
        JPanel panel = new JPanel(new BorderLayout());
        scrollPaneList.setViewportView(listAddData);
        panel.add(scrollPaneList, BorderLayout.CENTER);
        panel.add(buttonResetSelectedList, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * ставим слушателей
     */
    protected void setListeners() {
        buttonOpenTree.addActionListener(new ActionListenerButtonOpenTree());
        tree.addMouseListener(selectionListenerTreeList);
        listAddData.addMouseListener(selectionListenerTreeList);
        buttonCancel.addActionListener(new ActionListenerCancelButton());
        buttonCommit.addActionListener(new ActionListenerCommitButton());
    }

    public JList getListForDataShow() {
        return listForDataShow;
    }

    public JButton getButtonOpenTree() {
        return buttonOpenTree;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public JTree getTree() {
        return tree;
    }

    public JButton getButtonCommit() {
        return buttonCommit;
    }

    public JButton getButtonCancel() {
        return buttonCancel;
    }

    public JList getListAddData() {
        return listAddData;
    }

    public JScrollPane getScrollPaneList() {
        return scrollPaneList;
    }

    public JScrollPane getScrollPaneTree() {
        return scrollPaneTree;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    /**
     * назначаем ширину меню
     * <p>
     *
     * @param width
     */
    public void setWidthPopupMenu(int width) {
        widthPopupMenu = width;
    }

    /**
     * назначаем высоту меню
     * <p>
     *
     * @param height
     */
    public void setHeightPopupMenu(int height) {
        heightPopupMenu = height;
    }

    /**
     * добавляем слушателя на компонент изменения
     * <p>
     *
     * @param actionTreeListListener
     */
    public void addActionTreeListListener(ActionTreeListListener actionTreeListListener) {
        if (actionTreeListListeners == null) {
            actionTreeListListeners = new ArrayList<ActionTreeListListener>();
        }
        actionTreeListListeners.add(actionTreeListListener);
    }

    /**
     * удаляем слушателя
     * <p>
     *
     * @param actionTreeListListener
     */
    public void removeActionTreeListListener(ActionTreeListListener actionTreeListListener) {
        if (actionTreeListListeners != null) {
            actionTreeListListeners.remove(actionTreeListListener);
        }
    }

    /**
     * класс реализовывает действие по оображению выпадающего меню
     */
    private class ActionListenerButtonOpenTree implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMenu();
        }
    }

    public void showMenu() {
        //получаем размер меню
        int width = (widthPopupMenu == 0) ? (JEFilterTree.this.getSize().width + widthPopupMenuDefault) : (widthPopupMenu);
        int height = (heightPopupMenu == 0) ? (heightPopupMenuDefault) : (heightPopupMenu);
        int heightText = scrollPaneListForData.getVisibleRect().getSize().height;
        popupMenu.setPopupSize(width, height);
        popupMenu.show(scrollPaneListForData, 0, heightText + heightDown);
    }

    private class SelectionListenerTreeList extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            Object object = e.getSource();
            if (object instanceof JTree) {
                fireTreeListListener(ActionTreeListListener.WhatSelected.TREE, e);
            } else if (object instanceof JList) {
                fireTreeListListener(ActionTreeListListener.WhatSelected.LIST, e);
            }
        }

        /**
         * уведомляем всех слушателей о выборе
         * <p>
         *
         * @param whatSelected
         */
        private void fireTreeListListener(ActionTreeListListener.WhatSelected whatSelected, MouseEvent e) {
            if (actionTreeListListeners != null) {
                for (ActionTreeListListener atll : actionTreeListListeners) {
                    atll.selected(tree, listAddData, whatSelected, e);
                }
            }
        }
    }

    /**
     * слушатель на кнопку отмены
     */
    private class ActionListenerCancelButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            popupMenu.setVisible(false);
        }
    }

    /**
     * применяем (по сути просто скрываем меню)
     */
    private class ActionListenerCommitButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            popupMenu.setVisible(false);
        }
    }

    public JButton getButtonResetSelectedList() {
        return buttonResetSelectedList;
    }

    public JPanel getPanelForPopupMenu() {
        return panelForPopupMenu;
    }

    public JScrollPane getScrollPaneListForData() {
        return scrollPaneListForData;
    }


}
