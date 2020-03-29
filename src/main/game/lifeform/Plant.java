package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Plant extends Lifeform {
    
    public Plant(int x, int y) {
        super(x, y);
        this.color = Color.GREEN;
    }

    @Override
    public void update(Lifeform[][] lifeforms) {
        final double eightDir = Math.PI / 4;
        List<Point2D> emptyCells = new ArrayList<>();
        int adjacentPlants = 0;
        
        for (int i = 0; i < 8; i++) {
            // Big brain trig calculations to look at adjacent squares
            double dir = i * eightDir;
            int curRow = (int) Math.round(row + Math.sin(dir));
            int curCol = (int) Math.round(col + Math.cos(dir));
            
            if (!inBounds(lifeforms, curRow, curCol))
                continue;
            
            Lifeform curLife = lifeforms[curRow][curCol];
            
            if (curLife == null)
                emptyCells.add(new Point2D(curCol, curRow));
            else if (curLife instanceof Plant)
                adjacentPlants++;
            
        }
        
        // The magic of reproduction
        if (adjacentPlants >= 4 && emptyCells.size() >= 3) {
            Random rand = new Random();
            Point2D randCell = emptyCells.get(rand.nextInt(emptyCells.size()));
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            lifeforms[newRow][newCol] = new Plant(newRow, newCol);
        }
        
    }

}
