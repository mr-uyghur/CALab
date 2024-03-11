package stoplight;

import java.awt.*;
import java.awt.geom.*;

public class StoplightShape {
    private Stoplight light;
    private int height, width, xc, yc;
    private Ellipse2D.Double redLight, yellowLight, greenLight;

    public StoplightShape(Stoplight light, int xc, int yc, int height) {
        this.light = light;
        this.height = height;
        this.xc = xc;
        this.yc = yc;
        int delta = 5;
        double diam = (double) (height - 4 * delta) / 3;
        this.width = (int) (diam + 2 * delta);
        redLight = new Ellipse2D.Double(xc + delta, yc + delta, diam, diam);
        yellowLight = new Ellipse2D.Double(xc + delta, yc + diam + 2 * delta, diam, diam);
        greenLight = new Ellipse2D.Double(xc + delta, yc + 2 * diam + 3 * delta, diam, diam);
    }

    public StoplightShape(Stoplight light) {
        this(light, 50, 50, 100);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getXc() {
        return xc;
    }

    public int getYc() {
        return yc;
    }

    public void draw(Graphics2D gc) {
        Color oldColor = gc.getColor();
        gc.setColor(Color.WHITE);
        gc.fillRect(xc, yc, width, height);
        gc.setColor(Color.BLACK);
        gc.drawRect(xc, yc, width, height);
        gc.draw(greenLight);
        gc.draw(yellowLight);
        gc.draw(redLight);

        if (light.getColor().equals(Color.GREEN)) {
            gc.setColor(Color.GREEN);
            gc.fill(greenLight);
        } else if (light.getColor().equals(Color.YELLOW)) {
            gc.setColor(Color.YELLOW);
            gc.fill(yellowLight);
        } else if (light.getColor().equals(Color.RED)) {
            gc.setColor(Color.RED);
            gc.fill(redLight);
        } else {
            //throw new Exception("Invalid color: " + light.getColor().toString());
        }
        gc.setColor(oldColor);
    }
}
