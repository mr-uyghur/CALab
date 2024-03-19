package life;

import calab.*;
import mvc.*;

public class LifeFactory extends GridFactory {

    @Override
    public Model makeModel() {
        return new Society();
    }

    @Override
    public View makeView(Model m) {
        if (!(m instanceof Society grid))
            throw new RuntimeException("Model must instantiate Society");
        return new GridView(grid);
    }

    @Override
    public String getTitle() {
        return "Cellular Automata Lab";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "Run1 - runs the program once\n"+
                        "Run50 - runs the program 50 times\n" +
                        "Populate - randomly populates the grid with cells\n" +
                        "Clear - clears the grid and turns all the cells red\n"
        };
    }

    @Override
    public String getAbout() {
        return "Game of Life v.1.0. Copyright 2024 by mvc group 2 (Tianyi, Sandy, Ali)";
    }
}
