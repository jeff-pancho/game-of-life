package main.game.lifeform;

import javafx.scene.paint.Color;

public class Plant extends Lifeform {
    
    public Plant(int x, int y) {
        super(x, y);
        this.color = Color.GREEN;
    }

    @Override
    void move() {
        
    }

}
