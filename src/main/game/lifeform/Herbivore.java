package main.game.lifeform;

import javafx.scene.paint.Color;

public class Herbivore extends Lifeform {

    public Herbivore(int row, int col) {
        super(row, col);
        this.color = Color.YELLOW;
    }

    @Override
    public void update(Lifeform[][] lifeforms) {
        
    }
    
}
