package org.example;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Main extends JPanel {
    private Graphics G;
    private Double ArrowAngle = 0.;

    public void paintCircle(Graphics g, int x, int y, int r, boolean Centre, boolean Fill, int width) {
        Graphics2D g2 = (Graphics2D) g;
        if (Centre) {
            x = Double.valueOf(x - ((double) r / 2)).intValue();
            y = Double.valueOf(y - ((double) r / 2)).intValue();
        }
        if (Fill) {
            g.fillOval(x, y, r, r);
        } else {
            g2.setStroke(new BasicStroke(width));
            g2.drawOval(x, y, r, r);
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

    public void paintDivisionsText(Graphics g, int x, int y, int r, int width, Double startAngle, Double endAngle, String[] texts) {
        Graphics2D g2 = (Graphics2D) g;
        double angleStep = (endAngle - startAngle) / (texts.length - 1);
        Double x1, y1;
        for (int step = 0; step < texts.length; step++) {
            double a = (startAngle + angleStep * step) / 180 * Math.PI;
            double cos = Math.cos(a);
            double sin = Math.sin(a);
            x1 = x + r * cos;
            y1 = y + r * sin;
            g.setFont(new Font("TimesRoman", Font.BOLD, width));
            g2.drawString(texts[step], x1.intValue() - texts[step].length() / 2 * 8, y1.intValue() + 4);
        }
    }

    public void paintArrow(Graphics g, int x, int y, int r, Double Angle, Color color, int thickness) {
        Graphics2D g2 = (Graphics2D) g;

        double a = (Angle) / 180 * Math.PI - Math.PI/2;
        double cos = Math.cos(a);
        double sin = Math.sin(a);

        double x2 = x;
        double y2 = y;

        Polygon ARROW_HEAD = new Polygon();

        ARROW_HEAD.addPoint(1, r);
        ARROW_HEAD.addPoint(-1, r);
        ARROW_HEAD.addPoint(-4, 0);
        ARROW_HEAD.addPoint(4, 0);

        double angle = Math.atan2(y2 - y, x2 - x);

        g2.setColor(color);
        g2.setStroke(new BasicStroke(thickness));

        g2.drawLine(x, y, (int) (x2 - 10 * Math.cos(angle)), (int) (y2 - 10 * Math.sin(angle)));

        AffineTransform tx1 = g2.getTransform();
        AffineTransform tx2 = (AffineTransform) tx1.clone();

        tx2.translate(x2, y2);
        tx2.rotate(a);

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
        int x = 200;
        int y = 200;
        int r = 150;
        Graphics2D g2 = (Graphics2D) g;
        String[] text1 = {"", "10"};
        String[] text2 = {"20", "30", "40", "45", "50", "55", "60", "65"};
        String[] text3 = {"20", "40", "60"};
        String[] text4 = {"80", "90", "100"};
        g.setColor(Color.GRAY);
        paintCircle(g, x, y, (r+17)*2, true, false, 4);
        g.setColor(Color.BLACK);

        paintDivisions(g, x, y, r+2, 8., 2, -210., 45., 8*5+3);

        paintDivisions(g, x, y, r, 12., 4, -225., -220., 2);
        paintDivisionsText(g, x, y, r-20, 24, -225., -220., text1);

        paintDivisions(g, x, y, r, 12., 4, -210., 45., 8);
        paintDivisionsText(g, x, y, r-20, 24, -210., 45., text2);

        g.setColor(Color.ORANGE);
        paintDivisions(g, x, y, r/2, 6., 4, -220., -135., 3);
        paintDivisionsText(g, x, y, r/2-20, 16, -220., -135., text3);


        paintDivisions(g, x, y, r/2, 6., 4, -60., 30., 3);
        paintDivisionsText(g, x, y, r/2-20, 16, -60., 30., text4);

        paintArrow(g, x, y, r+10, ArrowAngle, Color.RED, 2);

        g.setColor(Color.BLACK);
        paintCircle(g, x, y, (50), true, true, 1);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Opel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        Main canvas = new Main();
        frame.add(canvas, BorderLayout.CENTER);

        JSlider slider = new JSlider(-225, 45, 0);
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