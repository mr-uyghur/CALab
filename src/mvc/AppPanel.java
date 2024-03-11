package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppPanel extends JPanel implements ActionListener {
    protected AppFactory factory;
    protected Model model;

    protected ControlPanel controlPanel;
    protected View viewPanel;

    public AppPanel(AppFactory factory) {
        this.factory = factory;
        this.model = factory.makeModel();

        controlPanel = new ControlPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        viewPanel = factory.makeView(model);

        setLayout((new GridLayout(1, 2)));
        add(controlPanel);
        add(viewPanel);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void display() {
        var frame = new SafeJFrame();

        var mb = new JMenuBar();

        var files = new JMenu("Files");
        files.add(createMenuItem("New"));
        files.add(createMenuItem("Save"));
        files.add(createMenuItem("Open"));
        files.add(createMenuItem("Quit"));
        mb.add(files);

        var edit = new JMenu("Edit");
        for (String name : factory.getEditCommands()) {
            var editCmd = factory.makeEditCommand(model, name, this);
            var item = new JMenuItem(name);
            item.addActionListener(e -> {
                try {
                    editCmd.execute();
                } catch (Exception ex) {
                    Utilities.error(ex);
                }
            });
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

        frame.setJMenuBar(mb);
        frame.add(this);
        frame.setTitle(factory.getTitle());
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    private JMenuItem createMenuItem(String name) {
        var res = new JMenuItem(name);
        res.addActionListener(this);
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New" -> setModel(factory.makeModel());
            // If no filename is associated, a save-as prompt is opened automatically
            case "Save" -> Utilities.save(model, false);
            case "Open" -> setModel(Utilities.open(model));
            case "Quit" -> System.exit(0);
        }
    }
}
