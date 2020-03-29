package main.game.lifeform;

import javafx.scene.paint.Color;

public abstract class Lifeform {
    protected int row;
    protected int col;
    protected Color color;
    
    public Lifeform(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    abstract void move();
    
    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public Color getColor() {
        return this.color;
    }
}
