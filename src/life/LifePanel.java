package life;

import calab.*;

import javax.swing.*;
import mvc.*;

public class LifePanel extends GridPanel {
    public LifePanel(AppFactory factory) {
        super(factory);
    }

    public static void main(String[] args){
        var factory = new LifeFactory();
        var p = new LifePanel(factory);
        p.display();
    }
}
