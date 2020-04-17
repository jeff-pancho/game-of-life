package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import main.game.cell.Cell;

public class Herbivore extends Lifeform implements EdibleForCarnivore {
    private int lifespan;
    private int turnsLeft;
    private int neighbors;
    private int numOfFood;

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
        
        List<Point2D> availableCells = new ArrayList<>();
        neighbors = 0;
        numOfFood = 0;
        
        scan(cells, (curLife, curRow, curCol) -> {
            if (curLife instanceof Herbivore)
                neighbors++;
            else if (curLife == null || curLife instanceof EdibleForHerbivore) {
                if (curLife instanceof EdibleForHerbivore)
                    numOfFood++;
                availableCells.add(new Point2D(curCol, curRow));
            }
        });
        
        boolean canGiveBirth = availableCells.size() >= 2 && numOfFood >= 2 && neighbors >= 1;
        boolean canMove = availableCells.size() >= 1;
        
        chooseRandCell(availableCells, canGiveBirth, (newRow, newCol, randCell) -> {
            giveBirth(cells, newRow, newCol);
            availableCells.remove(randCell);
        });
        
        chooseRandCell(availableCells, canMove, (newRow, newCol, randCell) -> {
            if (cells[newRow][newCol].getLifeform() instanceof EdibleForHerbivore)
                turnsLeft = lifespan;
            setPos(cells, newRow, newCol);
        });
        
        if (turnsLeft-- <= 0)
            cells[row][col].setLifeform(null);
        
        updated = true;
        
    }
    
    protected void giveBirth(Cell[][] cells, int newRow, int newCol) {
        Lifeform newLifeform = new Herbivore(newRow, newCol);
        newLifeform.setUpdated(false);
        cells[newRow][newCol].setLifeform(newLifeform);
    }
    
}
