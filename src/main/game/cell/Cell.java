package main.game.cell;

import java.util.ArrayList;

import javafx.geometry.Point2D;
import main.game.cell.region.Region;
import main.game.lifeform.Lifeform;

public abstract class Cell {
    protected final int row;
    protected final int col;
    protected Lifeform lifeform;
    protected Region region;
    protected ArrayList<Point2D> neighbors;
    
    public Cell(int row, int col, Region region) {
        this.row = row;
        this.col = col;
        this.lifeform = null;
        this.region = region;
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
    
    public Region getRegion() {
        return this.region;
    }

}
