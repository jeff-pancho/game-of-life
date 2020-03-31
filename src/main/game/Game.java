package main.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.game.lifeform.Herbivore;
import main.game.lifeform.Lifeform;
import main.game.lifeform.Plant;

public class Game extends Application {
    Lifeform[][] lifeforms;
    Board board;
    Group root;
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        board = new Board(Board.WIDTH, Board.HEIGHT);
        root = new Group(board);
        scene = new Scene(root, Board.WIDTH, Board.HEIGHT);
        initStage(stage, scene);
        
        lifeforms = new Lifeform[Board.SIZE][Board.SIZE];
        
        RandomGenerator.reset();
        
        populateBoard();
        board.render(lifeforms);
        
        scene.setOnMouseClicked(this::nextTurn);
    }
    
    private void nextTurn(MouseEvent e) {
        System.out.println("Next turn!!");
        board.render(lifeforms);
        
        for (Lifeform[] row : lifeforms)
            for (Lifeform curLife : row)
                if (curLife != null)
                    curLife.update(lifeforms);
        
        for (Lifeform[] row : lifeforms)
            for (Lifeform curLife : row)
                if (curLife != null)
                    curLife.setUpdated(false);
    }
    
    private void populateBoard() {
        for (int row = 0; row < lifeforms.length; row++) {
            for (int col = 0; col < lifeforms[row].length; col++) {
                final int randInt = RandomGenerator.nextNumber(100);
                if (randInt >= 85)
                    lifeforms[row][col] = new Herbivore(row, col);
                else if (randInt >= 65 && randInt < 85)
                    lifeforms[row][col] = new Plant(row, col);
            }
        }
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
