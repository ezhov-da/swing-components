package ru.ezhov.components.showcomment;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

/**
 * элемент отображения комментария для компонента
 * <p>
 *
 * @author ezhov_da
 */
public class JECommentWindow extends JWindow {
    protected JPanel commentPanel = new JPanel(null);
    protected JLabel label = new JLabel();
    private float indent;

    public JECommentWindow() {
        init();
    }

    protected void init() {
        commentPanel.add(label, BorderLayout.CENTER);
        add(commentPanel);
    }

    public JLabel getLabel() {
        return label;
    }


    @Override
    public void setVisible(boolean b) {
        AWTUtilities.setWindowOpaque(this, false);
        AWTUtilities.setWindowOpacity(this, 0.85f);
        com.sun.awt.AWTUtilities.setWindowShape(this, createShape());
        setLocationComponentNotify();
        //отображаем текст предупреждения
        super.setVisible(b); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * отображаем компонент с предупреждение
     */
    private void setLocationComponentNotify() {
        Dimension dimensionFrame = JECommentWindow.this.getSize(); //получаем размер компонента
        Dimension dimensionComponent = label.getSize(); //получаем размер уведомления
        int yLoc = dimensionFrame.height / 2 - dimensionComponent.height / 2;
        label.setLocation((int) indent, yLoc);
    }

    /**
     * создаем фигуру для фоормы
     * <p>
     *
     * @return фигура
     */
    private Shape createShape() {
        GeneralPath polyline = new GeneralPath();
        Dimension dimension = JECommentWindow.this.getSize(); //получаем размер панели
        float min = 5;    //маленькиий отступ
        float max = 5 * dimension.height / 100;    //большой отступ = константа (процент от размера * на высоту компонента / 100)
        indent = max + min + 10;   //это отступ для расположения элемента
        float width = (float) dimension.width;  //получаем ширину
        float height = (float) dimension.height;    //получаем высоту
        //всего в построении фигуры используется 14 точек
        //фигура будет строится относительно размеров формы + константы отступов
        float[] x =
                {
                        min,
                        min + max,
                        min + max,
                        min + max,
                        min + max * 2,
                        width - min - max,
                        width - min,
                        width - min,
                        width - min,
                        width - min,
                        width - min - max,
                        min + max * 2,
                        min + max,
                        min + max,
                        min + max

                };
        float[] y =
                {
                        height / 2,
                        height / 2 - max,
                        min + max,
                        min,
                        min,
                        min,
                        min,
                        min + max,
                        height - min - max,
                        height - min,
                        height - min,
                        height - min,
                        height - min,
                        height - min - max,
                        height / 2 + max
                };

        //рисуем линию
        polyline.moveTo(x[0], y[0]);
        polyline.lineTo(x[1], y[1]);
        polyline.lineTo(x[2], y[2]);
        polyline.curveTo(x[2], y[2], x[3], y[3], x[4], y[4]);
        polyline.lineTo(x[5], y[5]);
        polyline.curveTo(x[5], y[5], x[6], y[6], x[7], y[7]);
        polyline.lineTo(x[8], y[8]);
        polyline.curveTo(x[8], y[8], x[9], y[9], x[10], y[10]);
        polyline.lineTo(x[11], y[11]);
        polyline.curveTo(x[11], y[11], x[12], y[12], x[13], y[13]);
        polyline.lineTo(x[14], y[14]);
        polyline.closePath();
        return polyline;
    }

    private Color top = new Color(250, 250, 250);
    private Color bottom = new Color(238, 238, 238);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gd = (Graphics2D) g;
        gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gd.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        gd.setPaint(new GradientPaint(0, 0, top, 0, 700, bottom));
        gd.draw(createShape());
    }


}


//тестовый пример-----------------------------------------------------------------------------------------------------------------
//            polyline.moveTo(0, 50);
//            polyline.lineTo(20, 30);
//            polyline.lineTo(20, 10);
//            polyline.curveTo(20, 10, 30, 8, 40, 5);
//            polyline.lineTo(80, 5);
//            polyline.curveTo(80, 5, 90, 8, 100, 10);
//            polyline.lineTo(100, 70);
//            polyline.curveTo(100, 70, 90, 75, 80, 80);
//            polyline.lineTo(40, 80);
//            polyline.curveTo(40, 80, 30, 75, 20, 70);
//            polyline.lineTo(20, 50);
//            polyline.closePath();
//-------------------------------------------------------------------------------------------------------------------------------------------
