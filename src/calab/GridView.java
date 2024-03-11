package calab;

import java.awt.*;
import mvc.*;

public class GridView extends View {
    // Logically 2D array where a cell at (x,y) is indexed by `y*width+x`
    private CellView[] cellViews;

    public GridView(Grid model) {
        super(model);
        updateComponentsForGrid(model);
    }

    @Override
    public void setModel(Model newModel) {
        var grid = (Grid) newModel;
        super.setModel(newModel);
        // NOTE(rtk0c): this will leave dangling event subscriptions from CellView on the old model's Cells
        //   we just assume that whenever the model is changed out, everything from it is going to be disposed of
        updateComponentsForGrid(grid);
    }

    private void updateComponentsForGrid(Grid grid) {
        int dim = grid.getDim();
        cellViews = new CellView[dim * dim];
        removeAll();
        setLayout(new GridLayout(dim, dim));
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cellViews[y * dim + x] = new CellView(grid.getCell(y, x));
            }
        }
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
        for (CellView cv : cellViews) {
            cv.update(msg, oldState, newState);
        }
    }
}
