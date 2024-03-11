package mvc;

public interface AppFactory {
    Model makeModel();

    View makeView(Model m);

    String getTitle();

    String[] getHelp();

    String getAbout();

    String[] getEditCommands();

    Command makeEditCommand(Model model, String name, Object source);
}
