package CALab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mvc.*;


public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        myCell = c;
        if (c != null) { c.subscribe(this); }
        this.addActionListener(this);
    }

    public CellView() { this(null); }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        // call update needed?
    }

    // called by notifySubscribers and GridView.update

    @Override
    public void update(String msg, Object oldState, Object newState) {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black)); // needed?
        setText("" + myCell.getStatus());
    }
}

/*
Some other files needed:

   GridFactory.java
   GridPanel.java
   ClearCommand.java
   RunCommand.java   // for Run1 and Run50 buttons
   PopulateCommand.java

*/
