package main.game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.game.board.Board;

public class Game extends Application {
    World world;
    Group root;
    Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        world = new World();
        world.init();
        
        root = new Group(world.getBoard());
        scene = new Scene(root, Board.WIDTH, Board.HEIGHT);
        initStage(stage, scene);
        
        scene.setOnMouseClicked(this::nextTurn);
    }
    
    private void nextTurn(MouseEvent e) {
        System.out.println("Next turn!!");
        world.update();
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
