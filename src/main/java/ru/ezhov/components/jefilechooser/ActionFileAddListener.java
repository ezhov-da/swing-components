package ru.ezhov.components.jefilechooser;

import javax.swing.*;
import java.io.File;

/**
 * интерфейс, который используется для уведомления о внесении файлов в компонент
 * <p>
 *
 * @author ezhov_da
 */
public interface ActionFileAddListener {
    /**
     * метод срабатывает при добавлении файлов к элементу
     * <p>
     *
     * @param files        - список добавляемых файлов
     * @param list         - список для отображения файлов
     * @param eFileChooser - основной элемент
     */
    void addFile(File[] files, JList list, JEFileChooser eFileChooser);
}
