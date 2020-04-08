package main.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.game.board.Board;
import main.game.board.HexBoard;
import main.game.board.SquareBoard;
import main.game.cell.Cell;
import main.game.cell.Land;
import main.game.lifeform.Herbivore;
import main.game.lifeform.Lifeform;
import main.game.lifeform.Plant;

public class Game extends Application {
    Cell[][] cells;
    Board board;
    Group root;
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
//        board = new SquareBoard(Board.WIDTH, Board.HEIGHT);
        board = new HexBoard(Board.WIDTH, Board.HEIGHT);
        
        root = new Group(board);
        scene = new Scene(root, Board.WIDTH, Board.HEIGHT);
        initStage(stage, scene);
        
        cells = new Cell[Board.SIZE][Board.SIZE];
        
        RandomGenerator.reset();
        
        populateBoard();
        board.render(cells);
        
        scene.setOnMouseClicked(this::nextTurn);
    }
    
    private void nextTurn(MouseEvent e) {
        System.out.println("Next turn!!");
        board.render(cells);
        
        Lifeform curLife;
        
//        for (Cell[] row : cells)
//            for (Cell curCell : row)
//                if ((curLife = curCell.getLifeform()) != null) {
//                    curLife.update(cells);
//                }
//        
//        for (Cell[] row : cells)
//            for (Cell curCell : row)
//                if ((curLife = curCell.getLifeform()) != null)
//                    curLife.setUpdated(false);
        
    }
    
    private void populateBoard() {
//        for (int row = 0; row < cells.length; row++) {
//            for (int col = 0; col < cells[row].length; col++) {
//                Cell curCell = new Land(row, col);
//                cells[row][col] = curCell;
//                
//                final int randInt = RandomGenerator.nextNumber(100);
//                if (randInt >= 85)
//                    curCell.setLifeform(new Herbivore(row, col));
//                else if (randInt >= 65 && randInt < 85)
//                    curCell.setLifeform(new Plant(row, col));
//            }
//        }
    }
    
    private void initStage(Stage stage, Scene scene) {
        stage.setResizable(false);
        
        stage.setScene(scene);
        stage.setTitle("Game of Life");
        stage.show();
    }
    
    public void run() {
        launch();
    }

}
