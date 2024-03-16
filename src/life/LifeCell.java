package life;

import calab.*;

import java.awt.*;
import java.util.*;

import static java.awt.Color.red;
import static java.awt.Color.green;
import static mvc.Utilities.rng;

public class LifeCell extends Cell{
    private int status; // 0 is dead, 1 is alive
    private int ambience; // number of neighbors alive
    private Color color;
    private Set<LifeCell> neighbors = new HashSet<>();

    public LifeCell(){
        super.row = 20;
        super.col = 20;
        super.myGrid = new LifeGrid();
        status = 0;
        color = red;
        ambience = 0;
    }
    @Override
    public void observe() {
        int alive = 0;
        for(LifeCell n: neighbors){
            if(n.getStatus()==1){
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
        if(ambience!=3&&ambience!=2){
            status = 0;
            color = red;
        }
        else{
            status = 1;
            color = green;
        }
    }

    @Override
    public void reset(boolean randomly) {
        if(randomly){ // set status to either dead or alive
            if(rng.nextInt(2)==1)
                status = 1;
            else
                status = 0;
        }
        else{ // set status to dead
            status = 0;
        }
    }
}
