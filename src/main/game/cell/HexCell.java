package main.game.cell;

import javafx.geometry.Point2D;

import main.game.cell.region.Region;
import main.game.lifeform.Lifeform;

public class HexCell extends Cell {
    public static final Point2D[] NEIGHBORS_EVEN_POS = {
            new Point2D(-1, -1),
            new Point2D(-1, 0),
            new Point2D(-1 , 1),
            new Point2D(0, 1),
            new Point2D(1, 0),
            new Point2D(1, -1)
    };
    
    public static final Point2D[] NEIGHBORS_ODD_POS = {
            new Point2D(0, -1),
            new Point2D(-1, 0),
            new Point2D(0, 1),
            new Point2D(1, 1),
            new Point2D(1, 0),
            new Point2D(1, -1)
    };

    public HexCell(int row, int col, Region region, Cell[][] cells) {
        super(row, col, region);
        
        if (row % 2 == 0)
            for (Point2D pos : NEIGHBORS_EVEN_POS) {
                int curRow = row + (int) pos.getY();
                int curCol = col + (int) pos.getX();
                if (Lifeform.inBounds(cells, curRow, curCol))
                    neighbors.add(new Point2D(curRow, curCol));
            }
        else
            for (Point2D pos : NEIGHBORS_ODD_POS) {
                int curRow = row + (int) pos.getY();
                int curCol = col + (int) pos.getX();
                if (Lifeform.inBounds(cells, curRow, curCol))
                    neighbors.add(new Point2D(curRow, curCol));
            }
    }

}
