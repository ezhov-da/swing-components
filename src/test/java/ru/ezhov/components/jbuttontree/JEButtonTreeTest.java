package ru.ezhov.components.jbuttontree;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


/**
 *
 * @author ezhov_da
 */
public class JEButtonTreeTest
{

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Throwable ex)
                {
                    //
                }
                JFrame frame = new JFrame("_________");
                JEButtonTree buttonTree = new JEButtonTree();
                buttonTree.getPopupMenu().setPopupSize(300, 300);
                buttonTree.setText("test");
                buttonTree.addTreeToPanel();
                frame.add(buttonTree);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

}
