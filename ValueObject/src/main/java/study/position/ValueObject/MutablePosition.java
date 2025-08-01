package study.position.ValueObject;

import study.position.Position;

import java.util.Objects;

public class MutablePosition implements Position {
    int x;
    int y;

    public static MutablePosition of(int x, int y) {
        return new MutablePosition(x, y);
    }

    private MutablePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MutablePosition mutablePosition = (MutablePosition) o;
        return x == mutablePosition.x && y == mutablePosition.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public Position move(int newX, int newY) {
        this.setX(newX);
        this.setY(newY);
        return this;
    }
}
