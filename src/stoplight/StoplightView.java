package stoplight;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import mvc.*;

public class StoplightView extends View {
    private Stoplight light;

    public StoplightView(Stoplight light) {
        setLight(light);
        setSize(500, 500);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.LIGHT_GRAY);
    }

    public void setLight(Stoplight newLight) {
        if (light != null) {
            light.unsubscribe(this);
        }
        light = newLight;
        light.subscribe(this);
        repaint();
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Color oldColor = gc.getColor();
        var lightComponent = new StoplightComponent(light);
        lightComponent.paintComponent(gc);
        gc.setColor(oldColor);
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        repaint();
    }
}
