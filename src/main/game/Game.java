package main.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Game extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Board board = new Board(Board.WIDTH, Board.HEIGHT);
        Group root = new Group(board);
        Scene scene = new Scene(root, Board.WIDTH, Board.HEIGHT);
        
        initStage(stage, scene);
        
        board.render();
        
        scene.setOnMouseClicked(this::nextTurn);
    }
    
    private void nextTurn(MouseEvent e) {
        System.out.println("Next turn!!!");
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
