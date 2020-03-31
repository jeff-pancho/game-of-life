package main.game.lifeform;

import javafx.scene.paint.Color;
import main.game.Board;

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
    
    public abstract void update(Lifeform[][] lifeforms);
    
    public boolean inBounds(Lifeform[][] lifeforms, int row, int col) {
        return row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE;
    }
    
    public void setPos(Lifeform[][] lifeforms, int newRow, int newCol) {
        lifeforms[newRow][newCol] = this;
        lifeforms[row][col] = null;
        
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
