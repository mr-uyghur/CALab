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
    }

    @Override
    public void update(String msg, Object oldState, Object newState) {
        repaint();
    }
}
