package life;

import calab.*;

import javax.swing.*;

public class LifePanel extends GridPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;

    public LifePanel (GridFactory factory){
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
    }

    public static void main(String[] args){
        GridFactory factory = new LifeFactory();
        GridPanel panel = new LifePanel(factory);
        panel.display();
    }
}
