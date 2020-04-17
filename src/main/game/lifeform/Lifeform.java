package main.game.lifeform;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import main.game.RandomGenerator;
import main.game.cell.Cell;

public abstract class Lifeform {
    protected int row;
    protected int col;
    protected Color color;
    protected boolean updated;
    
    public Lifeform(int row, int col) {
        this.row = row;
        this.col = col;
        this.updated = false;
    }
    
    public abstract void update(Cell[][] cells);
    
    protected abstract void giveBirth(Cell[][] cells, int newRow, int newCol);
    
    public static boolean inBounds(Cell[][] cells, int row, int col) {
        return row >= 0 && row < cells.length && col >= 0 && col < cells[row].length;
    }
    
    protected void scan(Cell[][] cells, TriConsumer<Lifeform, Integer, Integer> c) {
        for (Point2D curPt : cells[row][col].getNeighbors()) {
            int curRow = (int) curPt.getY();
            int curCol = (int) curPt.getX();
            
            Cell curCell = cells[curRow][curCol];
            Lifeform curLife = curCell.getLifeform();
            
            c.accept(curLife, curRow, curCol);
        }
    }
    
    protected void chooseRandCell(List<Point2D> availableCells, boolean canSearch, TriConsumer<Integer, Integer, Integer> c) {
        if (canSearch) {
            int randInd = RandomGenerator.nextNumber(availableCells.size());
            Point2D randCell = availableCells.get(randInd);
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            
            c.accept(newRow, newCol, randInd);
        }
    }
    
    public void setPos(Cell[][] cells, int newRow, int newCol) {
        cells[newRow][newCol].setLifeform(this);
        cells[row][col].setLifeform(null);
        
        this.row = newRow;
        this.col = newCol;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}
