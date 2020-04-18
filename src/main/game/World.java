package main.game;

import main.game.board.Board;
import main.game.board.BoardType;
import main.game.board.HexBoard;
import main.game.board.SquareBoard;
import main.game.cell.Cell;
import main.game.cell.HexCell;
import main.game.cell.SquareCell;
import main.game.cell.region.Land;
import main.game.lifeform.Carnivore;
import main.game.lifeform.Herbivore;
import main.game.lifeform.Lifeform;
import main.game.lifeform.Omnivore;
import main.game.lifeform.Plant;

public class World {
    private Board board;
    private Cell[][] cells;
    private BoardType type;
    
    public World(BoardType type) {
        this.type = type;
        
        switch(this.type) {
        case HEX:
            this.board = new HexBoard(Board.WIDTH, Board.HEIGHT);
            break;
        case SQUARE:
        default:
            this.board = new SquareBoard(Board.WIDTH, Board.HEIGHT);
        }
        
        this.cells = new Cell[Board.SIZE][Board.SIZE];
    }
    
    public void init() {
        RandomGenerator.reset();
        populateWorld();
        board.render(cells);
    }
    
    public void update() {
        Lifeform curLife;
        
        for (Cell[] row : cells)
            for (Cell curCell : row)
                if ((curLife = curCell.getLifeform()) != null) {
                    curLife.update(cells);
                }
        
        for (Cell[] row : cells)
            for (Cell curCell : row)
                if ((curLife = curCell.getLifeform()) != null)
                    curLife.setUpdated(false);
        
        board.render(cells);
    }
    
    private void populateWorld() {
        for (int row = 0; row < cells.length; row++) {
            for (int col = 0; col < cells[row].length; col++) {
                Cell curCell;
                
                switch(type) {
                case HEX:
                    curCell = new HexCell(row, col, new Land(), cells);
                    break;
                case SQUARE:
                default:
                    curCell = new SquareCell(row, col, new Land(), cells);
                }
                
                cells[row][col] = curCell;
                
                final int randInt = RandomGenerator.nextNumber(100);
                if (randInt >= 80)
                    curCell.setLifeform(new Herbivore(row, col));
                else if (randInt >= 60)
                    curCell.setLifeform(new Plant(row, col));
                else if (randInt >= 50)
                    curCell.setLifeform(new Carnivore(row, col));
                else if (randInt >= 45)
                    curCell.setLifeform(new Omnivore(row, col));
            }
        }
    }
    
    public Board getBoard() {
        return this.board;
    }
    
}
