package main.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board extends Canvas {
    public static final int TILE_SIZE = 50;
    public static final int SIZE = 16;
    public static final int WIDTH = TILE_SIZE * SIZE;
    public static final int HEIGHT = TILE_SIZE * SIZE;
    
    private GraphicsContext gc;
    
    public Board(double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
    }
    
    public void render() {
        // render grid
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        
        for (int i = 0; i < SIZE; i++) {
            gc.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT);
            gc.strokeLine(0, i * TILE_SIZE, HEIGHT, i * TILE_SIZE);
        }
    }

}
