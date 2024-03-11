package calab;

import mvc.*;

public class PopulateCommand extends Command {
    public PopulateCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Grid grid)) {
            throw new Exception("Model must instantiate Grid");
        }
        grid.repopulate(true);
    }
}
