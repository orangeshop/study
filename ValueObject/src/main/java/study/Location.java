package study;

import study.position.Position;

public abstract class Location {
    Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void comment(String msg);
}
