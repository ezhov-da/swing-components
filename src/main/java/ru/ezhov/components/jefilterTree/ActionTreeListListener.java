package ru.ezhov.components.jefilterTree;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * интерфейс, который уведомляет пользователя о выборе дерева или списка
 * <p>
 *
 * @author ezhov_da
 */
public interface ActionTreeListListener {
    public enum WhatSelected {TREE, LIST}

    ;

    /**
     * Получаем выбранный компонент
     * <p>
     *
     * @param tree
     * @param list
     * @param whatSelected 0 - дерево, 1 - список
     * @param e
     */
    void selected(JTree tree, JList list, WhatSelected whatSelected, MouseEvent e);
}
