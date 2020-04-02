package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;
import main.game.RandomGenerator;

public class Herbivore extends Lifeform {
    private int lifespan;
    private int turnsLeft;

    public Herbivore(int row, int col) {
        super(row, col);
        this.color = Color.rgb(242, 197, 104);
        this.lifespan = 5;
        this.turnsLeft = this.lifespan;
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        final double eightDir = Math.PI / 4;
        List<Point2D> availableCells = new ArrayList<>();
        
        for (int i = 0; i < 8; i++) {
            double dir = i * eightDir;
            int curRow = (int) Math.round(row + Math.sin(dir));
            int curCol = (int) Math.round(col + Math.cos(dir));
            
            if (!inBounds(cells, curRow, curCol))
                continue;
            
            Cell curCell = cells[curRow][curCol];
            Lifeform curLife = curCell.getLifeform();
            
            if (curLife == null || curLife instanceof Plant)
                availableCells.add(new Point2D(curCol, curRow));
        }
        
        if (availableCells.size() >= 1) {
            final int randInd = RandomGenerator.nextNumber(10) % availableCells.size();
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            
            if (cells[newRow][newCol].getLifeform() instanceof Plant)
                turnsLeft = lifespan;
            
            setPos(cells, newRow, newCol);
        }
        
        if (turnsLeft-- <= 0)
            cells[row][col].setLifeform(null);
        
        updated = true;
        
    }
    
}
