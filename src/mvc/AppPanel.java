package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppPanel extends JPanel implements Subscriber, ActionListener {
    protected AppFactory factory;
    protected Model model;

    protected JPanel controlPanel;
    protected View viewPanel;
    protected SafeJFrame frame;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.model = factory.makeModel();
        this.model.subscribe(this);

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.PINK);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        viewPanel = factory.makeView(model);

        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(viewPanel);

        frame = new SafeJFrame();

        frame.setJMenuBar(makeMenuBar());
        frame.add(this);
        frame.setTitle(factory.getTitle());
        frame.setSize(500, 300);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model newModel) {
        model.unsubscribe(this);
        model = newModel;
        model.subscribe(this);
        viewPanel.setModel(model);
    }

    public void display() {
        frame.setVisible(true);
    }

    private JMenuItem createMenuItem(String name) {
        var res = new JMenuItem(name);
        res.addActionListener(this);
        return res;
    }

    @Override
    public void update() {
        // No-op
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        switch (name) {
            case "New" -> setModel(factory.makeModel());
            // If no filename is associated, a save-as prompt is opened automatically
            case "Save" -> Utilities.save(model, false);
            case "Open" -> setModel(Utilities.open(model));
            case "Quit" -> System.exit(0);
        }
        Command cmd = factory.makeEditCommand(model, name, e.getSource());
        if (cmd != null) {
            try {
                cmd.execute();
            } catch (Exception ex) {
                Utilities.error(ex);
            }
        }
    }

    protected JMenuBar makeMenuBar() {
        var mb = new JMenuBar();

        var files = new JMenu("Files");
        files.add(createMenuItem("New"));
        files.add(createMenuItem("Save"));
        files.add(createMenuItem("Open"));
        files.add(createMenuItem("Quit"));
        mb.add(files);

        var edit = new JMenu("Edit");
        for (String name : factory.getEditCommands()) {
            var item = new JMenuItem(name);
            item.setActionCommand(name); // technically not necessary, left here for clarity: we DO want the command name to be used as the key
            item.addActionListener(this);
            edit.add(item);
        }
        mb.add(edit);

        var help = new JMenu("Help");
        {
            var helpItem = new JMenuItem("Help...");
            helpItem.addActionListener(e -> Utilities.inform(factory.getHelp()));
            help.add(helpItem);
        }
        {
            var aboutItem = new JMenuItem("About...");
            aboutItem.addActionListener(e -> Utilities.inform(factory.getAbout()));
            help.add(aboutItem);
        }
        mb.add(help);

        return mb;
    }
}
