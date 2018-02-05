package ru.ezhov.components;

import ru.ezhov.components.jefilterTree.ActionTreeListListener;
import ru.ezhov.components.jefilterTree.JEFilterTree;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;

/**
 * @author ezhov_da
 */
public class TestJEFilterTree {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("_________");
                frame.setSize(300, 80);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JEFilterTree filterTree = new JEFilterTree();
                filterTree.getButtonCommit().setText("применить");
                filterTree.getButtonCancel().setText("отменить");
                filterTree.getSplitPane().setResizeWeight(0.6);
                DefaultListModel defaultListModel = new DefaultListModel();
                filterTree.getListForDataShow().setModel(defaultListModel);
                filterTree.getListAddData().setModel(defaultListModel);
                filterTree.addActionTreeListListener(new ListenerSelected());
                frame.add(filterTree);
                frame.setVisible(true);
            }
        });
    }
}

class ListenerSelected implements ActionTreeListListener {
    @Override
    public void selected(JTree tree, JList list, WhatSelected whatSelected, MouseEvent e) {
        DefaultListModel defaultListModel;
        switch (whatSelected) {
            case TREE:
                TreePath treePath = tree.getClosestPathForLocation(e.getX(), e.getY());
                if (treePath == null) {
                    return;
                }
                Object objectTreePath = treePath.getLastPathComponent();
                defaultListModel = (DefaultListModel) list.getModel();
                if (defaultListModel.contains(objectTreePath)) {
                    defaultListModel.removeElement(objectTreePath);
                } else {
                    defaultListModel.addElement(objectTreePath);
                }
                break;
            case LIST:
                defaultListModel = (DefaultListModel) list.getModel();
                int i = list.locationToIndex(e.getPoint());
                defaultListModel.remove(i);
                break;
        }
    }
}
