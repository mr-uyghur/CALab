package calab;

import javax.swing.*;
import mvc.*;

public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public GridPanel(AppFactory factory) {
        super(factory);
        run1 = new JButton("Run1");
        run50 = new JButton("Run50");
        populate = new JButton("Populate");
        clear = new JButton("Clear");

        run1.addActionListener(this);
        run50.addActionListener(this);
        populate.addActionListener(this);
        clear.addActionListener(this);

        controlPanel.add(run1);
        controlPanel.add(run50);
        controlPanel.add(populate);
        controlPanel.add(clear);

        controlPanel.setBackground(Color.LIGHT_GRAY);
    }
}
