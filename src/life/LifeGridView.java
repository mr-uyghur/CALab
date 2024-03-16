package life;

import calab.*;
import mvc.Model;

public class LifeGridView extends GridView{
    private CellView[] cellViews;

    public LifeGridView (LifeGrid grid){
        super(grid);
    }
    @Override
    public void setModel(Model newModel) {
        var lifeGrid = (LifeGrid) newModel;
        super.setModel(lifeGrid);
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
        for (CellView cv : cellViews) {
            cv.update(msg, oldState, newState);
        }
    }
}
