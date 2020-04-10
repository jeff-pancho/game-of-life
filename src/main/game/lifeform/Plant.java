package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;
import main.game.RandomGenerator;

public class Plant extends Lifeform implements EdibleForHerbivore {
    
    public Plant(int x, int y) {
        super(x, y);
        this.color = Color.rgb(58, 170, 52);
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        List<Point2D> availableCells = new ArrayList<>();
        int adjacentPlants = 0;
        
        for (Point2D curPt : cells[row][col].getNeighbors()) {
            int curRow = (int) curPt.getY();
            int curCol = (int) curPt.getX();
            
            Cell curCell = cells[curRow][curCol];
            Lifeform curLife = curCell.getLifeform();
            
            if (curLife == null)
                availableCells.add(new Point2D(curCol, curRow));
            else if (curLife instanceof Plant)
                adjacentPlants++;
        }
        
        if (adjacentPlants >= 2 && availableCells.size() >= 3) {
            int randInd = RandomGenerator.nextNumber(availableCells.size());
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            cells[newRow][newCol].setLifeform(new Plant(newRow, newCol));
            cells[newRow][newCol].getLifeform().setUpdated(false);
        }
        
        updated = true;
        
    }

}
