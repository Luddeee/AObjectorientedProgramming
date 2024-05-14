package Project;

// This code snippet is defining a Java interface named `GameStrategy`. This interface has one method
// `applySettings` that takes an object of type `MineSweeper` as a parameter. Any class that implements
// this interface must provide an implementation for the `applySettings` method. This interface can be
// used to define a strategy for applying settings to a `MineSweeper` game.
public interface GameStrategy {
    void applySettings(MineSweeper game);
}
