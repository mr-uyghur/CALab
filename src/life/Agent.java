package life;

import calab.*;
import java.awt.*;

import static java.awt.Color.*;
import static mvc.Utilities.*;

public class Agent extends Cell {
    private int status; // 0 is dead, 1 is alive
    private int ambience; // number of neighbors alive

    public Agent(Society society) {
        super(society);
        status = 0;
        ambience = 0;
    }

    @Override
    public void observe() {
        int alive = 0;
        for (Cell n : neighbors) {
            var a = (Agent) n;
            if (a.status == 1) {
                alive++;
            }
        }

        ambience = alive;
        notifySubscribers();
    }

    @Override
    public void interact() {
        // no op function
    }

    // checks ambience to determine status
    @Override
    public void update() {
        if (Society.rebirth.contains(ambience)) {
            status = 1;
        } else if (Society.death.contains(ambience)) {
            status = 0;
        }
        notifySubscribers();
    }

    @Override
    public int getStatus() {
        // TODO the assignment seems to indicate that getStatus() should return `status` (alive or dead)?
        //      but the screenshots show that it's the ambience!
        return ambience;
    }

    @Override
    public Color getColor() {
        return switch (status) {
            case 0 -> red;
            case 1 -> green;
            default -> throw new IllegalStateException("Invalid state " + status);
        };
    }

    @Override
    public void nextState() {
        status = (status + 1) % 2;
        notifySubscribers();
    }

    @Override
    public void reset(boolean randomly) {
        if (randomly) { // set status to either dead or alive
            // [1,100]
            int rand = rng.nextInt(100) + 1;
            if (rand <= Society.percentAlive)
                status = 1;
            else
                status = 0;
        } else { // set status to dead
            status = 0;
        }
        notifySubscribers();
    }
}

