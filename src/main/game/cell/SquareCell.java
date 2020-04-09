package main.game.cell;

import javafx.geometry.Point2D;

import main.game.cell.region.Region;
import main.game.lifeform.Lifeform;

public class SquareCell extends Cell {
    
    public static final Point2D[] NEIGHBORS_POS = {
            new Point2D(1, 0),
            new Point2D(1, -1),
            new Point2D(0, -1),
            new Point2D(-1, -1),
            new Point2D(-1, 0),
            new Point2D(-1, 1),
            new Point2D(0, 1),
            new Point2D(1, 1)
    };

    public SquareCell(int row, int col, Region region, Cell[][] cells) {
        super(row, col, region);
        
        for (Point2D pos : NEIGHBORS_POS) {
            int curRow = row + (int) pos.getY();
            int curCol = col + (int) pos.getX();
            if (Lifeform.inBounds(cells, curRow, curCol)) {
                Point2D newPos = new Point2D(curCol, curRow);
                neighbors.add(newPos);
            }
        }
        
    }

}
