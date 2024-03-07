package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber {
    // NOTE(rtk0c): the diagrams specify View to be a concrete class. It makes no sense. Whatever for the points.
    @Override
    public void update(String msg, Object oldState, Object newState) {}
}
