package stoplight;

import mvc.*;

public class StoplightFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Stoplight();
    }

    @Override
    public View makeView(Model m) {
        if (!(m instanceof Stoplight stoplight))
            throw new RuntimeException("Model must instantiate Stoplight");
        return new StoplightView(stoplight);
    }

    @Override
    public String getTitle() {
        return "Stop Light Simulator";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"click Change to cycle through colors"};
    }

    @Override
    public String getAbout() {
        return "Stoplight Simulator version 1.0. Copyright 2020 by Cyberdellic Designs";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Change"};
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object source) {
        //noinspection SwitchStatementWithTooFewBranches
        return switch (name) {
            case "Change" -> new ChangeCommand(model);
            default -> null;
        };
    }
}
