package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;
import main.game.RandomGenerator;

public class Omnivore extends Lifeform implements EdibleForCarnivore {
    private int lifespan;
    private int turnsLeft;
    private int neighbors;
    private int numOfFood;

    public Omnivore(int row, int col) {
        super(row, col);
        this.color = Color.rgb(68, 180, 228);
        this.lifespan = 5;
        this.turnsLeft = lifespan;
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        List<Point2D> availableCells = new ArrayList<>();
        neighbors = 0;
        numOfFood = 0;
        
        scan(cells, (curLife, curRow, curCol) -> {
            if (curLife instanceof Omnivore)
                neighbors++;
            else if (curLife == null || curLife instanceof EdibleForCarnivore || curLife instanceof EdibleForHerbivore) {
                if (curLife instanceof EdibleForCarnivore || curLife instanceof EdibleForHerbivore)
                    numOfFood++;
                availableCells.add(new Point2D(curCol, curRow));
            }
        });
        
        // giving birth
        if (availableCells.size() >= 3 && numOfFood >= 1 && neighbors >= 1) {
            int randInd = RandomGenerator.nextNumber(availableCells.size());
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            
            giveBirth(cells, newRow, newCol);
            availableCells.remove(randInd);
        }
        
        if (availableCells.size() >= 1) {
            int randInd = RandomGenerator.nextNumber(availableCells.size());
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            
            if (cells[newRow][newCol].getLifeform() instanceof EdibleForCarnivore)
                turnsLeft = lifespan;
            
            setPos(cells, newRow, newCol);
        }
        
        if (turnsLeft-- <= 0)
            cells[row][col].setLifeform(null);
        
        updated = true;
        
    }

    @Override
    protected void giveBirth(Cell[][] cells, int newRow, int newCol) {
        Lifeform newLifeform = new Omnivore(newRow, newCol);
        newLifeform.setUpdated(false);
        cells[newRow][newCol].setLifeform(newLifeform);
    }

}
