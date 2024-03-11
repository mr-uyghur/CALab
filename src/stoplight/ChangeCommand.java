package stoplight;

import mvc.*;

public class ChangeCommand extends Command {
    public ChangeCommand(Model model) { super(model); }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof Stoplight stoplight)) {
            throw new Exception("Model must instantiate Stoplight");
        }
        stoplight.change();
    }
}
