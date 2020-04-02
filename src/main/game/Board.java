package main.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import main.game.lifeform.Lifeform;

public class Board extends Canvas {
    public static final int TILE_SIZE = 35;
    public static final int SIZE = 25;
    public static final int WIDTH = TILE_SIZE * SIZE;
    public static final int HEIGHT = TILE_SIZE * SIZE;
    public static final Color gridColor = Color.rgb(77, 57, 81);
    
    private GraphicsContext gc;
    
    public Board(double width, double height) {
        super(width, height);
        this.gc = getGraphicsContext2D();
    }
    
    public void render(Lifeform[][] lifeforms) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        
        Lifeform curLife;
        
        for (int row = 0; row < lifeforms.length; row++)
            for (int col = 0; col < lifeforms[row].length; col++)
                if ((curLife = lifeforms[row][col]) != null)
                    renderLifeform(curLife);
                else {
                    Color rectColor = (row + col) % 2 == 0 ? Color.rgb(242, 245, 255) : Color.rgb(218, 218, 242);
                    renderRect(row, col, rectColor);
                }
        
        // render grid
        gc.setLineWidth(2);
        gc.setStroke(gridColor);
        
        for (int i = 0; i < SIZE; i++) {
            gc.strokeLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT);
            gc.strokeLine(0, i * TILE_SIZE, HEIGHT, i * TILE_SIZE);
        }
    }
    
    private void renderRect(int row, int col, Color color) {
        gc.setFill(color);
        gc.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }
    
    private void renderLifeform(Lifeform curLife) {
        renderRect(curLife.getRow(), curLife.getCol(), curLife.getColor());
    }

}
