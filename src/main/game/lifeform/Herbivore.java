package main.game.lifeform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Herbivore extends Lifeform {
    private int lifespan;
    private int turnsLeft;

    public Herbivore(int row, int col) {
        super(row, col);
        this.color = Color.rgb(242, 197, 104);
        this.lifespan = 5;
        this.turnsLeft = 5;
    }

    @Override
    public void update(Lifeform[][] lifeforms) {
        if (updated)
            return;
        
        final double eightDir = Math.PI / 4;
        List<Point2D> availableCells = new ArrayList<>();
        
        for (int i = 0; i < 8; i++) {
            double dir = i * eightDir;
            int curRow = (int) Math.round(row + Math.sin(dir));
            int curCol = (int) Math.round(col + Math.cos(dir));
            
            if (!inBounds(lifeforms, curRow, curCol))
                continue;
            
            Lifeform curLife = lifeforms[curRow][curCol];
            
            if (curLife == null || curLife instanceof Plant)
                availableCells.add(new Point2D(curCol, curRow));
        }
        
        if (availableCells.size() >= 1) {
            Random rand = new Random();
            Point2D randCell = availableCells.get(rand.nextInt(availableCells.size()));
            int newRow = (int) randCell.getY();
            int newCol = (int) randCell.getX();
            
            if (lifeforms[newRow][newCol] instanceof Plant)
                turnsLeft = lifespan;
            
            setPos(lifeforms, newRow, newCol);
        }
        
        if (turnsLeft-- <= 0)
            lifeforms[row][col] = null;
        
        updated = true;
        
    }
    
}
