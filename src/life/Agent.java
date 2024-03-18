package life;

import calab.*;
import java.awt.*;

import static java.awt.Color.*;
import static mvc.Utilities.*;

public class Agent extends Cell {
    private int state; // 0 is dead, 1 is alive
    private int ambience; // number of neighbors alive

    public Agent(Society society) {
        super(society);
        super.row = 20;
        super.col = 20;
        state = 0;
        ambience = 0;
    }

    @Override
    public void observe() {
        int alive = 0;
        for (Cell n : neighbors) {
            if (n.getState() == 1) {
                alive++;
            }
        }

        ambience = alive;
    }

    @Override
    public void interact() {
        // no op function
    }

    // checks ambience to determine status
    @Override
    public void update() {
        if (ambience != 3 && ambience != 2) {
            state = 0;
        } else {
            state = 1;
        }
        notifySubscribers();
    }

    @Override
    public int getStatus() {
        return ambience;
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public Color getColor() {
        return switch (state) {
            case 0 -> red;
            case 1 -> green;
            default -> throw new IllegalStateException("Invalid state " + state);
        };
    }

    @Override
    public void nextState() {
        state = (state + 1) % 2;
        notifySubscribers();
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) { // set status to either dead or alive
            if (rng.nextInt(2) == 1)
                state = 1;
            else
                state = 0;
        } else { // set status to dead
            state = 0;
        }
        notifySubscribers();
    }
}

