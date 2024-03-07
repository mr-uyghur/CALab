package mvc;

public interface Subscriber {
    void update(String msg, Object oldState, Object newState);
}
