package main.game.cell;

import javafx.scene.paint.Color;
import main.game.lifeform.Lifeform;

public abstract class Cell {
    protected final int row;
    protected final int col;
    protected Color color;
    protected Color altColor;
    protected Lifeform lifeform;
    
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.lifeform = null;
    }
    
    public Lifeform getLifeform() {
        return this.lifeform;
    }
    
    public void setLifeform(Lifeform lifeform) {
        this.lifeform = lifeform;
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

    public Color getAltColor() {
        return this.altColor;
    }
}
