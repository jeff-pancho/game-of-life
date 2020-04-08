package main.game.board;

import javafx.scene.paint.Color;
import main.game.cell.Cell;
import main.game.lifeform.Lifeform;

public class HexBoard extends Board {
    public static final double APOTHEM = (double) WIDTH / (SIZE * 2 + 1);
    public static final double RADIUS = APOTHEM / Math.cos(Math.PI / 6);
    public static final double Y_INCREMENT = APOTHEM * Math.sqrt(3);
    public static final double Y_OFFSET = (HEIGHT - ((SIZE - 2) * APOTHEM * Math.sqrt(3) + RADIUS * 2)) / 2;

    public HexBoard(double width, double height) {
        super(width, height);
    }

    @Override
    public void render(Cell[][] cells) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        
        for (int row = 0; row < cells.length; row++)
            for (int col = 0; col < cells[row].length; col++) {
                final double xOffset = row % 2 == 0 ? APOTHEM : APOTHEM * 2;
                final double centerX = xOffset + (APOTHEM * 2) * col;
                final double centerY = Y_OFFSET + Y_INCREMENT * row;
                
                Cell curCell = cells[row][col];
                Lifeform curLife = curCell.getLifeform();
                Color color;
                if (curLife != null) {
                    color = curLife.getColor();
                } else {
                    int offset = row % 2 == 0 ? 0 : 2;
                    color = curCell.getColor((col + offset) % 3);
                }
                
                renderHexagon(centerX, centerY, RADIUS, color, true);
                renderHexagon(centerX, centerY, RADIUS, gridColor, false);
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
