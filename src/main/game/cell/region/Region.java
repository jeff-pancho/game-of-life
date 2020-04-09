package main.game.cell.region;

import javafx.scene.paint.Color;

public abstract class Region {
    protected Color[] colors;
    
    public Color getColor(int choice) {
        return this.colors[choice];
    }
}
