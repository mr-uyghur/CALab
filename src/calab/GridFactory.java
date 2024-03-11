package calab;

import mvc.*;

public abstract class GridFactory implements AppFactory {
    @Override
    public View makeView(Model m) {
        return new GridView((Grid) m);
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Clear", "Populate", "Run1", "Run50"};
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        return switch (name) {
            case "Clear" -> new ClearCommand(model);
            case "Populate" -> new PopulateCommand(model);
            case "Run1" -> new RunCommand(model, 1);
            case "Run50" -> new RunCommand(model, 50);
            default -> throw new IllegalArgumentException("Unexpected value: " + name);
        };
    }
}
