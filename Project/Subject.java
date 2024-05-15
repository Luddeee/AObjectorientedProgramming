package Project;

// This code snippet is defining a Java interface named `Subject`. This interface declares three
// methods:
// 1. `registerObserver(Observer o)`: This method is used to register an observer object.
// 2. `removeObserver(Observer o)`: This method is used to remove an observer object.
// 3. `notifyObservers()`: This method is used to notify all registered observers.
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
    void notifyGameObserver(int x, int y, int flag);
}
