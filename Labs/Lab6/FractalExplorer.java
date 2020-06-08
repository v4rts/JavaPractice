import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer {

    private int wh; /* Высота-ширина окна */
    private JImageDisplay display; /* Ссылка JImageDisplay, для обновления отображения в разных методах в процессе вычисления фрактала */
    private FractalGenerator fractalGenerator;
    private JComboBox comboBox; /* Список фракталов */
    private Rectangle2D.Double range; /* Объект Rectangle2D.Double, указывающий диапазон комплексной плоскости, которая выводится на экран.*/
    private int RowsRemaining; /* Переменная для хранения оставшихся рядов для прорисовки */
    private JButton resetBtn; /* Кнопка reset */
    private JButton saveBtn; /* Кнопка save */

    private FractalExplorer (int wh) {
        /* Конструктор */
        this.wh = wh;
        this.fractalGenerator = new Mandelbrot();
        this.range = new Rectangle2D.Double(0,0,0,0);
    }


    public void createAndShowGUI() {
        /* Инициализация графического интерфейса Swing */
        JFrame frame = new JFrame("Fractal Explorer");
        display = new JImageDisplay(wh, wh);
        resetBtn = new JButton("Reset");
        saveBtn = new JButton("Save");
        resetBtn.setActionCommand("reset");
        saveBtn.setActionCommand("save");
        JLabel label = new JLabel("Selected fractal: ");
        comboBox = new JComboBox();
        /* Добавление фракталов в меню */
        comboBox.addItem(new Mandelbrot());
        comboBox.addItem(new Tricorn());
        comboBox.addItem(new BurningShip());
        JPanel panel = new JPanel();
        JPanel panelBtn = new JPanel();
        panel.add(label);
        panel.add(comboBox);
        panelBtn.add(resetBtn);
        panelBtn.add(saveBtn);
        ActionHandler aHandler = new ActionHandler();
        MouseHandler mHandler = new MouseHandler();
        resetBtn.addActionListener(aHandler);
        saveBtn.addActionListener(aHandler);
        display.addMouseListener(mHandler);

        /* Добавление интерфейса на JFrame */
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(display, java.awt.BorderLayout.CENTER);
        frame.add(panelBtn, java.awt.BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }

    private void drawFractal() {
        /* Метод для вывода фрактала на экран */
        enableUI(false); /* Отключение всех кнопок и списка во время прорисовки */
        this.RowsRemaining = wh; /* Оставшиеся ряды для прорисовки */
        for (int i = 0; i < wh; i++) {
            FractalWorker obj = new FractalWorker(i);
            obj.execute(); /* Запуск в фоновом режиме */
        }
    }

    public class ActionHandler implements ActionListener {
        /* Обработчик событий от кнопок сброса и сохранения*/
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand() == "reset") {
                fractalGenerator = (FractalGenerator) comboBox.getSelectedItem();
                fractalGenerator.getInitialRange(range);
                drawFractal();
            }
            else if (e.getActionCommand() == "save") {
                /* Средство выбора файлов */
                JFileChooser chooser = new javax.swing.JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                int res = chooser.showSaveDialog(display);

                if (res == JFileChooser.APPROVE_OPTION) {
                    try {
                        /* Обработчик возможной ошибки при сохранении */
                        javax.imageio.ImageIO.write(display.getImage(),"png", chooser.getSelectedFile());
                    } catch (Exception e1) {
                        javax.swing.JOptionPane.showMessageDialog(display, e1.getMessage(), "Save error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else {
                    return;
                }
            }
        }
    }
    public class MouseHandler extends MouseAdapter {
        /* Обрабочик событий от мыши */
        public void mouseClicked(MouseEvent e) {
            if (RowsRemaining != 0) { /* Возможность реагировать на клик мыши только тогда, когда не осталось рядов для закрашивания */
                return;
            }
            super.mouseClicked(e);
            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, wh, e.getX());
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, wh, e.getY());
            fractalGenerator.recenterAndZoomRange(range, xCoord, yCoord, .5);
            drawFractal();
        }
    }
    public static void main(String[] args) {
        /* Cтатический метод main() для вызовов методов рисования фрактала */
        FractalExplorer fractalExplorer = new FractalExplorer(500);
        fractalExplorer.createAndShowGUI();
        fractalExplorer.drawFractal();
    }

    private void enableUI(boolean val) {
        /* Включение-отключение кнопок и выпадающего списка */
        saveBtn.setEnabled(val);
        resetBtn.setEnabled(val);
        comboBox.setEnabled(val);
    }

    private class FractalWorker extends SwingWorker<Object, Object> {
        /* Класс FractalWorker отвечает за вычисление значений цвета для одной строки фрактала */
        private int yCoord;
        private int[] color; /* Список для RGB значений */

        FractalWorker(int yCoord) {
            /* Конструктор */
            this.yCoord = yCoord;
        }
        protected Object doInBackground()
        /* Метод, выполняющий зарисовки в фоновом режиме */
        {
            this.color = new int[wh];
            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.width, wh, this.yCoord);
            for (int j = 0; j < wh; j++) {
                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, wh, j);
                double numIters = fractalGenerator.numIterations(xCoord, yCoord);
                if (numIters == -1) {
                    color[j] = 0;
                } else {
                    float hue = 0.7f + (float) numIters / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    color[j] = rgbColor;
                }
            }
            return null;
        }
        public void done() {
            /* Перерисовывание области */
            for (int i = 0; i < wh; i++) {
                display.drawPixel(i, yCoord, color[i]);
            }
            display.repaint(0, 0, yCoord, wh, 1);
            RowsRemaining -= 1; /* Уменьшение кол-ва оставшихся для закрашивания рядов */
            if (RowsRemaining == 0) {
                enableUI(true); /* Разблокировка кнопок и выпадающего списка */
            }
        }
    }
}