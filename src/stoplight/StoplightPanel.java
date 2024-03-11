package stoplight;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import mvc.*;

public class StoplightPanel extends AppPanel {
    private JButton change;

    public StoplightPanel(AppFactory factory) {
        super(factory);
        change = new JButton("Change");
        change.addActionListener(this);
        controlPanel.add(change);
    }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }

    @Override
    public void setModel(Model model) {
        if (!(model instanceof Stoplight stoplight))
            throw new RuntimeException("Model must instantiate Stoplight");
        super.setModel(model);

        if (viewPanel != null) {
            ((StoplightView) viewPanel).setLight(stoplight);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource() == change) {
            ((Stoplight) model).change();
        }
    }
}


