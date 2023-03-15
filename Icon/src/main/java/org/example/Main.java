package org.example;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class Main extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(201, 201, 201));
        int y = 40;
        int x = -50;
        int[] xPoints = {80+x, 240+x, 210+x, 330+x, 320+x, 145+x, 170+x, 70+x};
        int[] yPoints = {95+y, 95+y, 110+y, 110+y, 125+y, 125+y, 110+y, 110+y};
        x = 50; y = 50; int r = 200;
        g.fillOval(x, y, r, r);
        g.setColor(Color.white);
        x = 65; y = 65; r = 178;
        g.fillOval(x, y, r-5, r);
        g.setColor(new Color(190, 190, 190));
        g.fillPolygon(xPoints, yPoints, 8);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Opel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(new Main());
        frame.setVisible(true);
    }
}