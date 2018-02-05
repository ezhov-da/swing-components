package ru.ezhov.components;

import ru.ezhov.components.jefilechooser.ActionFileAddListener;
import ru.ezhov.components.jefilechooser.JEFileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * тестируем кнопку выбора файлов
 * <p>
 *
 * @author ezhov_da
 */
public class TestJEFileChooser {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("_________");
                frame.setSize(200, 80);
                JEFileChooser fileChooser = new JEFileChooser("Добавить файлы");
                fileChooser.getLabelInfo().setText("<html>" + ""
                        + "<p align=\"center\"><font size=\"2\"><font color=\"#808080\">Поддерживается добавление файлов</font> <u><font color=\"#B22222\">размером до 10 Мб</font></u> <font color=\"#696969\">и запакованных в архив с расширением:</font> .<u><font color=\"#B22222\">7z, .rar, .zip</font></u></font></p>"
                );
                fileChooser.getList().setModel(new DefaultListModel());
                fileChooser.addActionFileAddListener(new OwnActionFileAddListener());
                fileChooser.setNameButton("Добавить файлы");
                frame.add(fileChooser);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class OwnActionFileAddListener implements ActionFileAddListener {
    @Override
    public void addFile(final File[] files, final JList list, final JEFileChooser eFileChooser) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DefaultListModel listModel = (DefaultListModel) list.getModel();
                for (File file : files) {
                    if (!file.isDirectory()) {
                        System.out.println("0");
                        if (!listModel.contains(file)) {
                            System.out.println("1");
//                            if (!file.exists())
//                            {
                            try {
                                System.out.println("3");
                                Scanner scanner = new Scanner(file);
                                StringBuilder stringBuilder = new StringBuilder();
                                while (scanner.hasNextLine()) {
                                    stringBuilder.append(scanner.nextLine());
                                }
//                                System.out.println("fuck");
//                                String path = file.getAbsolutePath();
//                                String ext = path.substring(path.lastIndexOf('.') + 1);
//                                File f = new File(file.getParent(), file.getName() + new Random().nextInt() + "." + ext);
//                                FileWriter fileWriter = new FileWriter(f);
//                                fileWriter.append(stringBuilder.toString());
//                                fileWriter.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(OwnActionFileAddListener.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(OwnActionFileAddListener.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //}
                            listModel.addElement(file);
                        }
                    }
                }
                eFileChooser.setFullText();
            }
        });
    }
}
