import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer {

    private int wh; /* Высота-ширина окна */
    private JImageDisplay display; /* Ссылка JImageDisplay, для обновления отображения в разных методах в процессе вычисления фрактала */
    private FractalGenerator fractalGenerator;
    private Rectangle2D.Double range; /* Объект Rectangle2D.Double, указывающий диапазон комплексной плоскости, которая выводится на экран.*/

    private FractalExplorer (int wh) {
        /* Конструктор */
        this.wh = wh;
        this.fractalGenerator = new Mandelbrot();
        this.range = new Rectangle2D.Double(0,0,0,0);
    }


    public void createAndShowGUI() {
        /* Инициализация графического интерфейса Swing */
        JFrame frame = new JFrame("Fractal");
        display = new JImageDisplay(wh, wh);
        JButton button = new JButton("Reset");

        ActionHandler Handler = new ActionHandler();
        MouseListener Listener = new MouseListener();
        button.addActionListener(Handler);
        display.addMouseListener(Listener);

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(display, java.awt.BorderLayout.CENTER);
        frame.add(button, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal() {
        /* Метод для вывода фрактала на экран */
        for (int x = 0; x < wh; x++) {
            for (int y = 0; y < wh; y++) {
                int counter = fractalGenerator.numIterations(FractalGenerator.getCoord(range.x, range.x + range.width, wh, x),fractalGenerator.getCoord(range.y, range.y + range.width, wh, y));
                if (counter == -1) {
                    display.drawPixel(x, y, 0);
                }
                else {
                    float hue = 0.7f + (float) counter / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    display.drawPixel(x, y, rgbColor);
                }
            }
        }
        display.repaint();
    }

    public class ActionHandler implements ActionListener {
        /* Обработчик событий от кнопки сброса */
        public void actionPerformed(ActionEvent e) {
            fractalGenerator.getInitialRange(range);
            drawFractal();
        }
    }

    public class MouseListener extends MouseAdapter {
        /* Обрабочик событий от мыши */
        public void mouseClicked(MouseEvent e) {
            double x = FractalGenerator.getCoord(range.x, range.x + range.width, wh, e.getX());
            double y = FractalGenerator.getCoord(range.y, range.y + range.width, wh, e.getY());
            fractalGenerator.recenterAndZoomRange(range, x, y, 0.5);
            drawFractal();
        }
    }
    public static void main(String[] args) {
        /* Cтатический метод main() для вызовов методов рисования фрактала */
        FractalExplorer fractalExplorer = new FractalExplorer(500);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }
}