package main.game.cell;

import javafx.scene.paint.Color;

public class Land extends Cell {

    public Land(int row, int col) {
        super(row, col);
        this.colors = new Color[] {
                Color.rgb(245, 245, 255),
                Color.rgb(218, 218, 242),
                Color.rgb(184, 184, 226)
        };
    }
}
