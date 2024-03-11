package calab;

import java.awt.*;
import mvc.*;

public class GridView extends View {
    // Logically 2D array where a cell at (x,y) is indexed by `y*width+x`
    private CellView[] cellViews;

    public GridView(Grid model) {
        super(model);
        int dim = model.getDim();
        cellViews = new CellView[dim * dim];
        setLayout(new GridLayout(dim, dim));
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cellViews[y * dim + x] = new CellView(model.getCell(y, x));
            }
        }
    }

    @Override
    public void setModel(Model newModel) {
        var grid = (Grid) newModel;
        super.setModel(newModel);

        // TODO handle if newModel changed dimensions
        // TODO I infer that the assignment wants the CellView to be recreated every time the model changes, hence why the template didn't come with a setCell() implementation
        int dim = grid.getDim();
        for (int y = 0; y < dim; ++y) {
            for (int x = 0; x < dim; ++x) {
                cellViews[y * dim + x].setCell(grid.getCell(y, x));
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
