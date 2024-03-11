package calab;

import java.util.*;
import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim;
    // TODO(rtk0c): make this a continuous array?
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);

    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        // 1. use makeCell to fill in cells
        // 2. use getNeighbors to set the neighbors field of each cell
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                // TODO(rtk0c: what the hell is uniform supposed to be
                var cell = makeCell(true);
                cells[y][x] = cell;
                cell.row = y;
                cell.col = x;
            }
        }
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                var cell = cells[y][x];
                cell.neighbors = getNeighbors(cell, 1);
            }
        }
    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cells[y][x].reset(randomly);
            }
        }
        // FIXME(rtk0c): what states to pass here?
        notifySubscribers("repopulate", null, null);
    }


    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        var result = new HashSet<Cell>();
        int x0 = asker.col - radius;
        int x1 = asker.col + radius;
        int y0 = asker.row - radius;
        int y1 = asker.row + radius;
        for (int y = y0; y <= y1; ++y) {
            for (int x = x0; x <= x1; ++x) {
                if (x == asker.col && y == asker.row)
                    continue;
                // N.B. to compute `i (mod n)` universally, we need `(i % n + n) % n`, but since we know i is at most -1, there is no need for the first reminder operation to handle cases of `i < -n`
                // Not optimized. If we want, we could split the loop in half at `asker.x|y`, but it's too much work for a homework assignment!
                int wrappedX = (x + dim) % dim;
                int wrappedY = (y + dim) % dim;
                result.add(cells[wrappedY][wrappedX]);
            }
        }
        return result;
    }

    // cell phases:

    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cells[y][x].observe();
            }
        }
    }

    public void interact() {
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cells[y][x].interact();
            }
        }
    }

    public void update() {
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cells[y][x].update();
            }
        }
    }

    public void updateLoop(int cycles) {
        observe();
        for (int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}
