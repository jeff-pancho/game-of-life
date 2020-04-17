package main.game.lifeform;

public interface TriConsumer<X, Y, Z> {
    void accept(X a, Y b, Z c);
}