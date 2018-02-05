package ru.ezhov.components.jefilechooser;

import ru.ezhov.components.filechooser.JFileChooserOpenFiles;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ezhov_da
 */
public class JEFileChooser extends JButton {

    /**
     * ширина меню по умолчанию
     */
    private static final int widthDefaultPopupMenu = 200;
    /**
     * высота меню по умолчанию
     */
    private static final int heightDefaultPopupMenu = 200;
    /**
     * отступ от нижней границы компонента кнопкии
     */
    private static final int downPopupMenu = 5;
    //КОМПОНЕНТЫ------------------------------------------------------------------------------------------------------------------
    protected JLabel labelInfo;
    protected JList list;
    protected JScrollPane scrollPaneList;
    protected JPopupMenu popupMenu;
    protected JFileChooserOpenFiles fileChooser;
    protected JPanel panelBasic;
    //---------------------------------------------------------------------------------------------------------------------------------------
    protected String STR_ADD_NAME_FILE_PATTERN = "%s (%s)";
    private String nameButton;
    /**
     * установленная ширина меню
     */
    private int widthPopupMenu;
    /**
     * установленная высота меню
     */
    private int heightPopupMenu;
    /**
     * коллекция, которая хранит слушателей на добавление файлов
     */
    private List<ActionFileAddListener> actionFileAddLcisteners;

    public JEFileChooser() {
        init();
    }

    public JEFileChooser(Icon icon) {
        super(icon);
        init();
    }

    public JEFileChooser(String text) {
        super(text);
        init();
    }

    public JEFileChooser(Action a) {
        super(a);
        init();
    }

    public JEFileChooser(String text, Icon icon) {
        super(text, icon);
        init();
    }

    /**
     * ставим высоту меню
     * <p>
     *
     * @param heightPopupMenu
     */
    public void setHeightPopupMenu(int heightPopupMenu) {
        this.heightPopupMenu = heightPopupMenu;
    }

    /**
     * получаем установленную ширину меню
     * <p>
     *
     * @return
     */
    public int getWidthPopupMenu() {
        return widthPopupMenu == 0 ? widthDefaultPopupMenu : widthPopupMenu;
    }

    /**
     * ставим ширину меню
     * <p>
     *
     * @param widthPopupMenu
     */
    public void setWidthPopupMenu(int widthPopupMenu) {
        this.widthPopupMenu = widthPopupMenu;
    }

    /**
     * получаем установленную высоту меню
     * <p>
     *
     * @return
     */
    public int getHeightDefaultPopupMenu() {
        return heightPopupMenu == 0 ? heightDefaultPopupMenu : heightPopupMenu;
    }

    public JList getList() {
        return list;
    }

    public JScrollPane getScrollPaneList() {
        return scrollPaneList;
    }

    public JPopupMenu getPopupMenu() {
        return popupMenu;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    private void init() {
        initComponents(); //инициализируем компоненты
        initLayout(); //размещаем компоненты
        setListeners(); //ставим слушателей
        initDragAndDrope();
    }

    /**
     * метод устанавливает перетаскивание на кнопку
     */
    private void initDragAndDrope() {
        list.setTransferHandler(new BottomFileTrasferHandler());
        setTransferHandler(new BottomFileTrasferHandler());
    }

    /**
     * инициализируем компоненты
     */
    protected void initComponents() {
        labelInfo = new JLabel();
        list = new JList();
        scrollPaneList = new JScrollPane(list);
        scrollPaneList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        popupMenu = new JPopupMenu();
        fileChooser = new JFileChooserOpenFiles();
        fileChooser.changeGoUp("На один уровень вверх");
        fileChooser.addOpenFiles();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(true);
        UIManager.put(TOP, ui);
    }

    /**
     * размещаем их на менюшке
     */
    protected void initLayout() {
        scrollPaneList.setViewportView(list);
        JPanel panelList = new JPanel(new BorderLayout());
        panelList.add(labelInfo, BorderLayout.NORTH);
        panelList.add(scrollPaneList, BorderLayout.CENTER);
        panelBasic = new JPanel(new BorderLayout());
        panelBasic.add(panelList, BorderLayout.CENTER);
        popupMenu.add(panelBasic);
    }

    public JPanel getPanelBasic() {
        return panelBasic;
    }

    protected void setListeners() {
        addActionListener(new ActiomListenerButton());
        list.addMouseListener(new ActionListenerAddButton());
    }

    public String getNameButton() {
        return nameButton;
    }

    public void setNameButton(String nameButton) {
        this.nameButton = nameButton;
    }

    public void setFullText() {
        setText(String.format(STR_ADD_NAME_FILE_PATTERN, nameButton, list.getModel().getSize()));
    }

    /**
     * добавляем слушателя на добавление файлов
     * <p>
     *
     * @param actionFileAddListener
     */
    public void addActionFileAddListener(ActionFileAddListener actionFileAddListener) {
        if (actionFileAddLcisteners == null) {
            actionFileAddLcisteners = new ArrayList<ActionFileAddListener>();
        }
        actionFileAddLcisteners.add(actionFileAddListener);
    }

    /**
     * удаляем слушателя на добавление файлов
     * <p>
     *
     * @param actionFileAddListener
     */
    public void removeActionFileAddListener(ActionFileAddListener actionFileAddListener) {
        if (actionFileAddLcisteners != null) {
            actionFileAddLcisteners.remove(actionFileAddListener);
        }
    }

    public JLabel getLabelInfo() {
        return labelInfo;
    }

    private class ActiomListenerButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int width = widthPopupMenu == 0 ? widthDefaultPopupMenu : widthPopupMenu;
            int height = heightPopupMenu == 0 ? heightDefaultPopupMenu : heightPopupMenu;
            int y = JEFileChooser.this.getSize().height + downPopupMenu;
            popupMenu.setPopupSize(width, height);
            popupMenu.show(JEFileChooser.this, 0, y);
        }
    }

    /**
     * слушатель на добавление файлов через диалоговое окно
     */
    private class ActionListenerAddButton extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            int indexList = list.locationToIndex(e.getPoint());
            Rectangle rectangle = list.getCellBounds(indexList, indexList);
            if (e.getButton() == MouseEvent.BUTTON1 && (indexList == -1 || !rectangle.contains(e.getPoint()))) {
                int i = fileChooser.showOpenDialog(null);
                if (i == JFileChooser.OPEN_DIALOG) {
                    File[] files = fileChooser.getSelectedFiles();
                    for (ActionFileAddListener actionFileAddListener : actionFileAddLcisteners) {
                        actionFileAddListener.addFile(files, list, JEFileChooser.this);
                    }
                }
            }
        }
    }

    /**
     * класс, отвечающий за перетаскивание файлов на кнопку
     */
    private class BottomFileTrasferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY_OR_MOVE;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                try {
                    List<File> listFile;
                    listFile = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    File[] files = (File[]) listFile.toArray();
                    for (ActionFileAddListener actionFileAddListener : actionFileAddLcisteners) {
                        actionFileAddListener.addFile(files, list, JEFileChooser.this);
                    }
                    return true;
                } catch (Exception ex) {
                    Logger.getLogger(JEFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            }
            return false;
        }
    }
}
