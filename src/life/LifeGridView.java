package life;

import calab.*;
import mvc.Model;

public class LifeGridView extends GridView{
    private LifeCellView[] lifeCellViews;

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
        for (LifeCellView cv : lifeCellViews) {
            cv.update(msg, oldState, newState);
        }
    }
}
