package study.position.ImmutableValueObject;

import study.position.Position;

import java.util.Objects;

public class ImmutablePosition implements Position {
    private final int x;
    private final int y;

    public static ImmutablePosition of(int x, int y) {
        return new ImmutablePosition(x, y);
    }

    private ImmutablePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImmutablePosition moveX(int distance) {
        return new ImmutablePosition(this.x + distance, this.y);
    }

    public ImmutablePosition moveY(int distance) {
        return new ImmutablePosition(this.x, this.y + distance);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImmutablePosition immutablePosition = (ImmutablePosition) o;
        return x == immutablePosition.x && y == immutablePosition.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Position move(int newX, int newY) {
        return new ImmutablePosition(newX, newY);
    }
}
