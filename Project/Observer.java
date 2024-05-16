package Project;

// This code snippet is defining an interface named `Observer` in Java. The interface has a single
// method `update()` declared without any implementation. Classes that implement this interface will
// need to provide their own implementation for the `update()` method. This interface is typically used
// in the Observer design pattern to allow objects to subscribe and receive updates from a subject.
public interface Observer {
    void update();
    void updateGame(int x, int y, int flag);
    void updateSounds(String soundType);
}
