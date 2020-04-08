package main.game.board;

import javafx.scene.paint.Color;
import main.game.cell.Cell;

public class HexBoard extends Board {

    public HexBoard(double width, double height) {
        super(width, height);
    }

    @Override
    public void render(Cell[][] cells) {
        final double radius = 18;
        final double diameter = radius * 2;
        final double apothem = radius * Math.cos(Math.PI / 6);
        final double yOffset = apothem * Math.sqrt(3);
        
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        
        for (int i = 0; i < 24; i++)
            for (int j = 0; j < 24; j++) {
                final double xOffset = i % 2 == 0 ? radius : radius + apothem;
                renderHexagon(xOffset + (apothem * 2) * j, diameter + yOffset * i, radius, Color.BLACK, false);
            }
        
    }
    
    private void renderHexagon(double centerX, double centerY, double radius, Color color, boolean fill) {
        final double sixthDir = Math.PI / 3;
        double[] xPt = new double[6];
        double[] yPt = new double[6];
        
        for (int i = 0; i < 6; i++) {
            final double pointDir = Math.PI / 6 + sixthDir * i; 
            xPt[i] = centerX + radius * Math.cos(pointDir);
            yPt[i] = centerY + radius * Math.sin(pointDir);
        }
        
        if (fill) {
            gc.setFill(color);
            gc.fillPolygon(xPt, yPt, xPt.length);
        } else {
            gc.setLineWidth(2);
            gc.setStroke(color);
            gc.strokePolygon(xPt, yPt, xPt.length);
        }
        
    }

}
