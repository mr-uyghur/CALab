package calab;

import java.awt.*;
import java.io.*;
import java.util.*;
import mvc.*;

public abstract class Cell extends Publisher implements Serializable {
    protected int row = 0, col = 0;
    // FIXME(rtk0c): the UML diagram given asks for Cell[8], but the starter code given uses a set????
    protected Set<Cell> neighbors = new HashSet<>();
    protected Grid myGrid;
    protected Cell partner = null;

    public Cell(Grid grid) {
        this.myGrid = grid;
    }

    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
            var arr = neighbors.toArray(new Cell[0]);
            int startingPos = new Random().nextInt(arr.length);
            // Iterating i in the modulo set of `arr.length`
            // More concretely, loop i in `startingPos..(arr.length)` and `0..startingPos` sequentially (note `..` denotes an close-open interval)
            for (int i = startingPos; i != startingPos - 1; i = (i + 1) % arr.length) {
                var neighbor = arr[i];
                if (neighbor.partner == null) {
                    neighbor.partner = this;
                    this.partner = neighbor;
                    break;
                }
            }

            // Failed to find a partner
        }
    }

    public void unpartner() {
        if (partner != null) {
            partner.partner = null;
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();

    // interact with a random neighbor
    public abstract void interact();

    // update my state
    public abstract void update();

    public abstract int getStatus();

    // NOTE(rtk0c):
    //   The UML diagram marks this as a concrete method, which does not make any sense since there might be an arbitrary number of distinct states
    //   The description said "Where nextState, getColor, and getStatus are abstract methods in the Cell class", so that's what I'm going for
    public abstract Color getColor();

    // set status to status + 1 mod whatever
    public abstract void nextState();

    // set status to a random or initial value
    public abstract void reset(boolean randomly);
}
