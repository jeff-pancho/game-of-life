package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;
import main.game.RandomGenerator;

public class Plant extends Lifeform implements PlantEdible {
    
    public Plant(int x, int y) {
        super(x, y);
        this.color = Color.rgb(58, 170, 52);
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        final double eightDir = Math.PI / 4;
        List<Point2D> availableCells = new ArrayList<>();
        int adjacentPlants = 0;
        
        for (int i = 0; i < 8; i++) {
            double dir = i * eightDir;
            int curRow = (int) Math.round(row + Math.sin(dir));
            int curCol = (int) Math.round(col + Math.cos(dir));
            
            if (!inBounds(cells, curRow, curCol))
                continue;
            
            Cell curCell = cells[curRow][curCol];
            Lifeform curLife = curCell.getLifeform();
            
            if (curLife == null)
                availableCells.add(new Point2D(curCol, curRow));
            else if (curLife instanceof Plant)
                adjacentPlants++;
        }
        
        if (adjacentPlants == 4 && availableCells.size() >= 3) {
            int randInd = RandomGenerator.nextNumber(availableCells.size());
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            cells[newRow][newCol].setLifeform(new Plant(newRow, newCol));
        }
        
        updated = true;
        
    }

}
