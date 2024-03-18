package life;

import calab.*;

public class Society extends Grid {

    public Society(){
        super();
    }
    
    public Society(int dim){
        super(dim);
    }
    @Override
    public Cell makeCell(boolean uniform) {
        return new Agent();
    }
}
