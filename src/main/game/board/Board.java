package main.game.board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.game.cell.Cell;

public abstract class Board extends Canvas {
    public static final int TILE_SIZE = 35;
    public static final int SIZE = 25;
    public static final int WIDTH = TILE_SIZE * SIZE;
    public static final int HEIGHT = TILE_SIZE * SIZE;
    public static final Color gridColor = Color.rgb(77, 57, 81);
    
    protected GraphicsContext gc;
    
    public Board(double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
    }
    
    public abstract void render(Cell[][] cells);
    
    protected void renderRect(int row, int col, Color color) {
        gc.setFill(color);
        gc.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
