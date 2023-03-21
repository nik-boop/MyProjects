package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main extends JPanel {
    private Graphics G;
    private Double ArrowAngle = 0.;

    public void paintCircle(Graphics g, int x, int y, int r, boolean Centre, boolean Fill) {
        if (Centre) {
            x = Double.valueOf(x - (r / 2)).intValue();
            y = Double.valueOf(y - (r / 2)).intValue();
        }
        if (Fill) {
            g.fillOval(x, y, r, r);
        } else {
            g.drawOval(x, y, r, r);
        }
    }

    public void paintDivisions(Graphics g, int x, int y, int r, Double lenLine, float width, Double startAngle, Double endAngle, int steps) {
        Graphics2D g2 = (Graphics2D) g;
        double angleStep = (endAngle - startAngle) / (steps - 1);
        Double x1, y1, x2, y2;
        for (int step = 0; step < steps; step++) {
            double a = (startAngle + angleStep * step) / 180 * Math.PI;
            double cos = Math.cos(a);
            double sin = Math.sin(a);
            x1 = x + r * cos;
            y1 = y + r * sin;
            x2 = x + (r + lenLine) * cos;
            y2 = y + (r + lenLine) * sin;
            g2.setStroke(new BasicStroke(width));
            g2.drawLine(x1.intValue(), y1.intValue(), x2.intValue(), y2.intValue());
        }
    }

    public void paintDivisionsText(Graphics g, int x, int y, int r, float width, Double startAngle, Double endAngle, String[] texts) {
        Graphics2D g2 = (Graphics2D) g;
        double angleStep = (endAngle - startAngle) / (texts.length - 1);
        Double x1, y1;
        for (int step = 0; step < texts.length; step++) {
            double a = (startAngle + angleStep * step) / 180 * Math.PI;
            double cos = Math.cos(a);
            double sin = Math.sin(a);
            x1 = x + r * cos;
            y1 = y + r * sin;
            g2.drawString(texts[step], x1.intValue() - texts[step].length() / 2 * 8, y1.intValue() + 4);
        }
    }

    public void paintArrow(Graphics g, int x, int y, int r, Double Angle, Color color, int thickness) {
        Graphics2D g2 = (Graphics2D) g;

        double a = (Angle) / 180 * Math.PI;
        double cos = Math.cos(a);
        double sin = Math.sin(a);

        Double x2 = x + r * cos;
        Double y2 = y + r * sin;

        Polygon ARROW_HEAD = new Polygon();

        ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-5, -10);
        ARROW_HEAD.addPoint(5, -10);

        double angle = Math.atan2(y2 - y, x2 - x);

        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(x, y, (int) (x2 - 10 * Math.cos(angle)), (int) (y2 - 10 * Math.sin(angle)));

        AffineTransform tx1 = g2.getTransform();
        AffineTransform tx2 = (AffineTransform) tx1.clone();

        tx2.translate(x2, y2);
        tx2.rotate(angle - Math.PI / 2);

        g2.setTransform(tx2);

        g2.fill(ARROW_HEAD);

        g2.setTransform(tx1);
    }

    public void clear(Graphics g) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.G = g;
        Graphics2D g2 = (Graphics2D) g;
        String[] text = {"0", "22.5", "45", "67.5", "90"};
        paintDivisions(g, 100, 100, 50, 15.0, 2, -180., 0., 9);
        paintDivisionsText(g, 100, 100, 40, 1, -180., 0., text);
        paintArrow(g, 100, 100, 30, ArrowAngle, Color.RED, 2);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Opel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        Main canvas = new Main();
        frame.add(canvas, BorderLayout.CENTER);

        JSlider slider = new JSlider(-270, 270, 0);
        slider.setPreferredSize(new Dimension(150, 30));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                int value = slider.getValue();
                canvas.ArrowAngle = (double) value;
                canvas.repaint();
            }
        });
        frame.add(slider, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}