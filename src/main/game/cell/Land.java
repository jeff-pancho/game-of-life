package main.game.cell;

import javafx.scene.paint.Color;

public class Land extends Cell {

    public Land(int row, int col) {
        super(row, col);
        this.color = Color.rgb(242, 245, 255);
        this.altColor = Color.rgb(218, 218, 242);
    }
}
