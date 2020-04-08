package main.game.board;

import javafx.scene.paint.Color;
import main.game.cell.Cell;
import main.game.lifeform.Lifeform;

public class SquareBoard extends Board {
    public SquareBoard(double width, double height) {
        super(width, height);
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
                    Color color = curCell.getColor((row + col) % 2);
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
}
