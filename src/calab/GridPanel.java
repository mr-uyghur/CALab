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
        run1 = new JButton("Run1");
        run50 = new JButton("Run50");
        populate = new JButton("Populate");
        clear = new JButton("Clear");

        run1.addActionListener(this);
        run50.addActionListener(this);
        populate.addActionListener(this);
        clear.addActionListener(this);

        // formatting the button layout
        bp.setLayout(new GridLayout(2,2,25,100));
        bp.setBackground(Color.LIGHT_GRAY);
        bp.add(run1);
        bp.add(run50);
        bp.add(populate);
        bp.add(clear);
        bp.setPreferredSize(new Dimension(215,150));

        controlPanel.add(bp);
        controlPanel.setBackground(Color.LIGHT_GRAY);
    }
}
