package main.game.board;

import javafx.scene.paint.Color;
import main.game.cell.Cell;

public class HexBoard extends Board {

    public HexBoard(double width, double height) {
        super(width, height);
    }

    @Override
    public void render(Cell[][] cells) {
        
        
    }
    
    private void renderHexagon(double centerX, double centerY, double radius, Color color, boolean fill) {
        final double sixthDir = Math.PI / 3;
        double[] xPt = new double[6];
        double[] yPt = new double[6];
        
        for (int i = 0; i < 6; i++) {
            xPt[i] = centerX + radius * Math.cos(sixthDir);
            yPt[i] = centerY + radius * Math.sin(sixthDir);
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
