package main.game.lifeform;

import javafx.scene.paint.Color;
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
    
    public boolean inBounds(Cell[][] cells, int row, int col) {
        return row >= 0 && row < cells.length && col >= 0 && col < cells[row].length;
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
