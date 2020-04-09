package main.game.cell;

import javafx.geometry.Point2D;
import main.game.cell.region.Region;

public class SquareCell extends Cell {
    public static final Point2D[] NEIGHBORS_POS = {
            new Point2D(-1, -1),
            new Point2D(0, -1),
            new Point2D(-1, 1),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(1, 0),
            new Point2D(1, -1),
            new Point2D(-1, 0)
    };

    public SquareCell(int row, int col, Region region, Cell[][] cells) {
        super(row, col, region);
        
        for (Point2D pos : NEIGHBORS_POS) {
            
        }
        
    }

}
