package calab;

import mvc.*;

public abstract class GridFactory implements AppFactory {
    @Override
    public View makeView(Model m) {
        return new GridView((Grid) m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"RUN1", "RUN50","POPULATE","CLEAR"};
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        return switch (name) {
            case "CLEAR" -> new ClearCommand(model);
            case "POPULATE" -> new PopulateCommand(model);
            case "RUN1" -> new RunCommand(model, 1);
            case "RUN50" -> new RunCommand(model, 50);
            default -> throw new IllegalArgumentException("Unexpected value: " + name);
        };
    }
}
