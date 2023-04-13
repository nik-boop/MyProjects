package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    private Double ArrowAngle = 0.;

    public void paintCircle(GraphicsContext g, int x, int y, int r, boolean Centre, boolean Fill, int width) {
        if (Centre) {
            x = Double.valueOf(x - ((double) r / 2)).intValue();
            y = Double.valueOf(y - ((double) r / 2)).intValue();
        }
        if (Fill) {
            //g.setFill(Color.BLACK);
            g.fillOval(x, y, r, r);
        } else {
            //g.setStroke(Color.BLACK);
            g.setLineWidth(width);
            g.strokeOval(x, y, r, r);
        }
    }

    public void paintDivisions(GraphicsContext g, int x, int y, int r, Double lenLine, float width, Double startAngle, Double endAngle, int steps) {
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
            //g.setStroke(Color.BLACK);
            g.setLineWidth(width);
            g.strokeLine(x1, y1, x2, y2);
        }
    }

    public void paintDivisionsText(GraphicsContext g, int x, int y, int r, int width, Double startAngle, Double endAngle, String[] texts) {
        double angleStep = (endAngle - startAngle) / (texts.length - 1);
        Double x1, y1;
        for (int step = 0; step < texts.length; step++) {
            double a = (startAngle + angleStep * step) / 180 * Math.PI;
            double cos = Math.cos(a);
            double sin = Math.sin(a);
            x1 = x + r * cos;
            y1 = y + r * sin;
            //g.setFill(Color.BLACK);
            g.setFont(new javafx.scene.text.Font("TimesRoman", width));
            g.fillText(texts[step], x1 - texts[step].length() / 2.0 * 8, y1 + 4);
        }
    }

    public void paintArrow(GraphicsContext g, int x, int y, int r, Double Angle, Color color, int thickness) {
        double a = (Angle) / 180 * Math.PI - Math.PI/2;
        double cos = Math.cos(a);
        double sin = Math.sin(a);

        double x2 = x;
        double y2 = y;

        double[] xpoints = {1, -1, -4, 4};
        double[] ypoints = {r, r, 0, 0};
        int npoints = 4;

        double angle = Math.atan2(y2 - y, x2 - x);

        g.setStroke(color);
        g.setFill(color);
        g.setLineWidth(thickness);
        g.strokeLine(x, y, x2 - 10 * Math.cos(angle), y2 - 10 * Math.sin(angle));

        g.save();
        g.translate(x2, y2);
        g.rotate(Angle);
        g.fillPolygon(xpoints, ypoints, npoints);
        g.restore();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(400, 400);
        root.setCenter(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Draw(gc);

        Slider slider = new Slider(-225, 45, 0);
        slider.setPrefWidth(150);
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            ArrowAngle = newValue.doubleValue();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            Draw(gc);
        });

        root.setBottom(slider);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Манометр");
        primaryStage.show();
    }

    public void Draw(GraphicsContext gc) {
        int x = 200;
        int y = 200;
        int r = 150;
        String[] text1 = {"", "10"};
        String[] text2 = {"20", "30", "40", "45", "50", "55", "60", "65"};
        String[] text3 = {"20", "40", "60"};
        String[] text4 = {"80", "90", "100"};
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(4);
        paintCircle(gc, x, y, (r+17)*2, true, false, 4);
        gc.setStroke(Color.BLACK);
        paintDivisions(gc, x, y, r+2, 8., 2, -210., 45., 8*5+3);

        gc.setStroke(Color.BLACK);
        paintDivisions(gc, x, y, r, 12., 4, -225., -220., 2);
        paintDivisionsText(gc, x, y, r-20, 24, -225., -220., text1);

        gc.setStroke(Color.BLACK);
        paintDivisions(gc, x, y, r, 12., 4, -210., 45., 8);
        paintDivisionsText(gc, x, y, r-20, 24, -210., 45., text2);

        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.ORANGE);
        paintDivisions(gc, x, y, r/2, 6., 4, -220., -135., 3);
        paintDivisionsText(gc, x, y, r/2-20, 16, -220., -135., text3);

        gc.setStroke(Color.ORANGE);
        gc.setFill(Color.ORANGE);
        paintDivisions(gc, x, y, r/2, 6., 4, -60., 30., 3);
        paintDivisionsText(gc, x, y, r/2-20, 16, -60., 30., text4);

        paintArrow(gc, x, y, r+10, ArrowAngle-90, Color.RED, 2);

        gc.setFill(Color.BLACK);
        gc.fillOval(x - 25, y - 25, 50, 50);
    }
    public static void main(String[] args) {
        launch();
    }
};