package calab;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mvc.*;

public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        myCell = c;
        // NOTE(rtk0c): I have zero clue why there is ever a need to not bind a Cell to CellView,
        //   but it's a part of the template code, so I guess it gets to stay
        if (c != null) {
            c.subscribe(this);
        }
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black));
        setText("" + myCell.getStatus());
        // call to update() not needed, because Cell#nextState() notifies its subscribers which includes this
    }

    @Override
    public void update() {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black)); // needed?
        setText(Integer.toString(myCell.getStatus()));
    }
}

