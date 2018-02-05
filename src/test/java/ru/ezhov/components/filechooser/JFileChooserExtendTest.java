package ru.ezhov.components.filechooser;

import javax.swing.SwingUtilities;

/**
 *
 * @author ezhov_da
 */
public class JFileChooserExtendTest
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {

            @Override
            public void run()
            {
                JFileChooserExtend fileChooserExtend = new JFileChooserExtend();
                fileChooserExtend.changeGoUp("тест");
                fileChooserExtend.showOpenDialog(null);
            }
        });
    }
}
