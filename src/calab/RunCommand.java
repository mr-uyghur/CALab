package calab;

import mvc.*;

public class RunCommand extends Command {
    private int times;

    public RunCommand(Model model, int times) {
        super(model);
        this.times = times;
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Grid grid)) {
            throw new Exception("Model must instantiate Grid");
        }
        grid.updateLoop(times);
    }
}
