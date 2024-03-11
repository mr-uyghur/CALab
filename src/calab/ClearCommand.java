package calab;

import mvc.*;

public class ClearCommand extends Command {
    public ClearCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Grid grid)) {
            throw new Exception("Model must instantiate Grid");
        }
        grid.repopulate(false);
    }
}
