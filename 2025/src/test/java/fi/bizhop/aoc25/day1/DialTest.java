package fi.bizhop.aoc25.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DialTest {
    @Test
    public void turnLeftPart2_goingToNegative() {
        var dial = new Dial(1);
        var result = dial.turnLeftPart2(2);
        assertEquals(1, result);
    }

    @Test
    public void turnLeftPart2_goingToNegativeFromZero() {
        var dial = new Dial(0);
        var result = dial.turnLeftPart2(1);
        assertEquals(0, result);
    }

    @Test
    public void turnLeftPart2_hittingZero() {
        var dial = new Dial(1);
        var result = dial.turnLeftPart2(1);
        assertEquals(1, result);
    }
}
