package calab;

import mvc.*;

public class GridView extends View {
    private Grid model;
    // Logically 2D array where a cell at (x,y) is indexed by `y*width+x`
    private CellView[] cellViews;

    public GridView(Grid model) {
        this.model = model;
        int dim = model.getDim();
        this.cellViews = new CellView[dim * dim];
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cellViews[y * dim + x] = new CellView(model.getCell(y, x));
            }
        }
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
    }
}
