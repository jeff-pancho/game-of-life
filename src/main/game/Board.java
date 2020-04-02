package main.game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.game.cell.Cell;
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
    
    public void render(Cell[][] cells) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        
        for (int row = 0; row < cells.length; row++)
            for (int col = 0; col < cells[row].length; col++) {
                Cell curCell = cells[row][col];
                Lifeform curLife = curCell.getLifeform();
                if (curLife != null)
                    renderRect(row, col, curLife.getColor());
                else {
                    Color color = (row + col) % 2 == 0 ? curCell.getColor() : curCell.getAltColor();
                    renderRect(row, col, color);
                }
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

}
