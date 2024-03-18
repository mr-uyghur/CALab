package life;

import calab.*;

public class Society extends Grid {

    public Society(int dim) {
        super(dim);
        populate();
    }

    public Society() {
        super();
        populate();
    }

    @Override
    public Cell makeCell(boolean uniform) {
        return new Agent(this);
    }
}

