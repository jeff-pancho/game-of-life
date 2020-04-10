package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;
import main.game.RandomGenerator;

public class Carnivore extends Lifeform {
    private int lifespan;
    private int turnsLeft;

    public Carnivore(int row, int col) {
        super(row, col);
        this.color = Color.rgb(227, 68, 99);
        this.lifespan = 5;
        this.turnsLeft = lifespan;
    }

    @Override
    public void update(Cell[][] cells) {
        if (updated)
            return;
        
        List<Point2D> availableCells = new ArrayList<>();
        int numOfFood = 0;
        int carnivoreNeighbors = 0;
        
        for (Point2D curPt : cells[row][col].getNeighbors()) {
            int curRow = (int) curPt.getY();
            int curCol = (int) curPt.getX();
            
            Cell curCell = cells[curRow][curCol];
            Lifeform curLife = curCell.getLifeform();
            
            if (curLife instanceof Carnivore)
                carnivoreNeighbors++;
            else if (curLife == null || curLife instanceof EdibleForCarnivore) {
                if (curLife instanceof EdibleForCarnivore)
                    numOfFood++;
                availableCells.add(new Point2D(curCol, curRow));
            }
            
        }
        
        // giving birth
        if (availableCells.size() >= 3 && numOfFood >= 2 && carnivoreNeighbors >= 1) {
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
        Lifeform newLifeform = new Carnivore(newRow, newCol);
        newLifeform.setUpdated(false);
        cells[newRow][newCol].setLifeform(newLifeform);
    }

}