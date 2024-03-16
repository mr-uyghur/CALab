package life;

import calab.*;
import mvc.*;


public class LifeFactory extends GridFactory {

    @Override
    public Model makeModel() {
        return new LifeGrid();
    }

    @Override
    public View makeView(Model m) {
        if (!(m instanceof LifeGrid grid))
            throw new RuntimeException("Model must instantiate LifeGrid");
        return new LifeView(grid);
    }

    @Override
    public String getTitle() {
        return "Cellular Automata Lab";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "Run1 - runs the program once/n"+
                "Run50 - runs the program 50 times/n" +
                "Populate - randomly populates the grid with cells/n" +
                "Clear - clears the grid and turns all the cells red/n"

        };
    }

    @Override
    public String getAbout() {
        return "Game of Life v.1.0. Copyright 2024 by mvc group 2 (Tianyi, Sandy, Ali)";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Run1", "Run50", "Populate", "Clear"};
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
