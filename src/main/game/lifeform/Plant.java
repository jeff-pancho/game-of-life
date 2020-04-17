package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;

public class Plant extends Lifeform implements EdibleForHerbivore {
    private int neighbors;
    
    public Plant(int x, int y) {
        super(x, y);
        this.color = Color.rgb(58, 170, 52);
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        List<Point2D> availableCells = new ArrayList<>();
        neighbors = 0;
        
        scan(cells, (curLife, curRow, curCol) -> {
           if (curLife == null)
               availableCells.add(new Point2D(curCol, curRow));
           else if (curLife instanceof Plant)
               neighbors++;
        });
        
        boolean canGiveBirth = availableCells.size() >= 3 && neighbors >= 2;
        
        chooseRandCell(availableCells, canGiveBirth, (newRow, newCol, randCell) -> {
            giveBirth(cells, newRow, newCol);
        });
        
        updated = true;
        
    }
    
    protected void giveBirth(Cell[][] cells, int newRow, int newCol) {
        Lifeform newLifeform = new Plant(newRow, newCol);
        newLifeform.setUpdated(false);
        cells[newRow][newCol].setLifeform(newLifeform);
    }

}
