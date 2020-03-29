package main.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import main.game.lifeform.Lifeform;

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
    
    public void render(Lifeform[][] lifeforms) {
        for (Lifeform[] row : lifeforms)
            for (Lifeform curLife : row)
                if (curLife != null)
                    renderLifeform(curLife);
        
        // render grid
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        
        for (int i = 0; i < SIZE; i++) {
            gc.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT);
            gc.strokeLine(0, i * TILE_SIZE, HEIGHT, i * TILE_SIZE);
        }
    }
    
    private void renderLifeform(Lifeform curLife) {
        gc.setFill(curLife.getColor());
        gc.fillRect(curLife.getCol() * TILE_SIZE, curLife.getRow() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
