package life;

import calab.*;

public class LifeGrid extends Grid {

    public LifeGrid(){
        super();
        populate();
    }
    @Override
    public Cell makeCell(boolean uniform) {
        return new LifeCell();
    }
}
