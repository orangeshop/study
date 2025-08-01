package study.ValueObject;

import org.junit.jupiter.api.Test;
import study.Monster;
import study.Player;
import study.position.ImmutableValueObject.ImmutablePosition;
import study.position.Position;
import study.position.ValueObject.MutablePosition;

import static org.junit.jupiter.api.Assertions.*;


class ValueObjectTestClass {

    @Test
    void PositionTest() {
        Player player = new Player();
        Monster monster = new Monster();

        MutablePosition mutablePosition = MutablePosition.of(0, 0);

        player.setPosition(mutablePosition);
        monster.setPosition(mutablePosition);

        assertEquals(MutablePosition.of(0, 0), monster.getPosition());
        assertEquals(MutablePosition.of(0, 0), player.getPosition());

        player.getPosition().move(1,0);

        assertEquals(MutablePosition.of(1, 0), player.getPosition());
        assertNotEquals(MutablePosition.of(0, 0), monster.getPosition());

    }

    @Test
    void ImmutablePositionTest() {
        Player player = new Player();
        Monster monster = new Monster();

        Position position = ImmutablePosition.of(0, 0);

        player.setPosition(position);
        monster.setPosition(position);

        assertEquals(ImmutablePosition.of(0, 0), monster.getPosition());
        assertEquals(ImmutablePosition.of(0, 0), player.getPosition());

        player.setPosition(player.getPosition().move(1,0));

        assertEquals(ImmutablePosition.of(1, 0), player.getPosition());
        assertEquals(ImmutablePosition.of(0, 0), monster.getPosition());

    }
}