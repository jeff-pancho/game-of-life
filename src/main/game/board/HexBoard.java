package main.game.board;

import javafx.scene.paint.Color;
import main.game.cell.Cell;

public class HexBoard extends Board {
    public static final double APOTHEM = (double) WIDTH / (SIZE * 2 + 1);
    public static final double RADIUS = APOTHEM / Math.cos(Math.PI / 6);
    public static final double Y_INCREMENT = APOTHEM * Math.sqrt(3);
    public static final double Y_OFFSET = (HEIGHT - (24 * APOTHEM * Math.sqrt(3) + RADIUS * 2)) / 2;

    public HexBoard(double width, double height) {
        super(width, height);
    }

    @Override
    public void render(Cell[][] cells) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        
        // render grid
        for (int i = 0; i < 25; i++)
            for (int j = 0; j < 25; j++) {
                final double xOffset = i % 2 == 0 ? APOTHEM : APOTHEM * 2;
                renderHexagon(xOffset + (APOTHEM * 2) * j, Y_OFFSET + Y_INCREMENT * i, RADIUS, Color.BLACK, false);
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
