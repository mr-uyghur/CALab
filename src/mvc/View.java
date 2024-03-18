package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model newModel) {
        model.unsubscribe(this);
        model = newModel;
        newModel.subscribe(this);
        // Pull data from newModel
        update();
    }

    @Override
    public void update() {
        repaint();
    }
}
