package life;

import calab.*;
import java.awt.*;
import java.util.*;
import mvc.*;

public class LifeCell extends Cell {
    private int status; // 0 is dead, 1 is alive
    private int ambience; // number of neighbors alive
    private Color color;
    private Set<Cell> neighbors;

    public LifeCell() {
        super.row = 20;
        super.col = 20;
        super.myGrid = new LifeGrid();
        neighbors = myGrid.getNeighbors(this, 1);
        status = 0;
        color = Color.red;
        ambience = 0;
    }

    @Override
    public void observe() {
        int alive = 0;
        for (Cell n : neighbors) {
            if (n.getStatus() == 1) {
                alive++;
            }
        }

        ambience = alive;
    }

    @Override
    public void interact() {
        // no op function
    }

    @Override
    public void update() {
        nextState();
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public Color getColor() {
        return color;
    }

    // checks ambience to determine status
    @Override
    public void nextState() {
        if (ambience != 3 && ambience != 2) {
            status = 0;
            color = Color.red;
        } else {
            status = 1;
            color = Color.green;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) { // set status to either dead or alive
            if (Utilities.rng.nextInt(2) == 1) status = 1;
            else status = 0;
        } else { // set status to dead
            status = 0;
        }
    }
}
