package ru.ezhov.components.filechooser;

import javax.swing.SwingUtilities;

/**
 *
 * @author ezhov_da
 */
public class JFileChooserOpenFilesTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFileChooserOpenFiles openFiles = new JFileChooserOpenFiles();
                openFiles.addOpenFiles();
                openFiles.showOpenDialog(null);
            }
        });
    }

}
