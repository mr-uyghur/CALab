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

    public Cell getCell() {
        return myCell;
    }

    public void setCell(Cell cell) {
        if (myCell != null) {
            myCell.unsubscribe(this);
        }
        this.myCell = cell;
        cell.subscribe(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        // call to update() not needed, because Cell#nextState() notifies its subscribers which includes this
    }

    // called by notifySubscribers and GridView.update
    @Override
    public void update(String msg, Object oldState, Object newState) {
        setBackground(myCell.getColor());
        setBorder(BorderFactory.createLineBorder(Color.black)); // needed?
        setText(Integer.toString(myCell.getStatus()));
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
