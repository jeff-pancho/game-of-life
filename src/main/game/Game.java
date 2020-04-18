package main.game;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.game.board.Board;
import main.game.board.BoardType;

public class Game extends Application {
    World world;
    Group root;
    Scene scene;
    BoardType type;
    
    @Override
    public void start(Stage stage) throws Exception {
        List<String> args = getParameters().getRaw();
        String arg = args.size() > 0 ? args.get(0) : "HEX";
        switch(arg.toUpperCase()) {
        case "HEX":
            type = BoardType.HEX;
            break;
        case "SQUARE":
        default:
            type = BoardType.SQUARE;
        }
        
        world = new World(type);
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
    
    public void run(String[] args) {
        launch(args);
    }

}
