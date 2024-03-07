package mvc;

import java.util.*;

public class Publisher {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void subscribe(Subscriber s) {
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s) {
        subscribers.remove(s);
    }

    protected void notifySubscribers(String msg, Object oldState, Object newState) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(msg, oldState, newState);
        }
    }
}
