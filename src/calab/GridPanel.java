package calab;

import javax.swing.*;
import mvc.*;

import java.awt.*;

public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public GridPanel(AppFactory factory) {
        super(factory);
        JPanel bp = new JPanel();
        run1 = new JButton("RUN1");
        run50 = new JButton("RUN50");
        populate = new JButton("POPULATE");
        clear = new JButton("CLEAR");

        run1.addActionListener(this);
        run50.addActionListener(this);
        populate.addActionListener(this);
        clear.addActionListener(this);

        // formatting the button layout
        bp.setLayout(new GridLayout(2,2,75,150));
        bp.setBackground(Color.LIGHT_GRAY);
        bp.add(run1);
        bp.add(run50);
        bp.add(populate);
        bp.add(clear);
        bp.setPreferredSize(new Dimension(275,200));

        controlPanel.add(bp);
        controlPanel.setBackground(Color.LIGHT_GRAY);
        viewPanel.setBackground(Color.GRAY);
    }
}
