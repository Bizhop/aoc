package fi.bizhop.aoc25.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day2Test {
    @Test
    public void testIsInvalid() {
        assertEquals("11", Day2.isInvalid("11"));
        assertEquals("22", Day2.isInvalid("22"));
        assertEquals("99", Day2.isInvalid("99"));
        assertEquals("1010", Day2.isInvalid("1010"));
        assertEquals("222222", Day2.isInvalid("222222"));
        assertEquals("1188511885", Day2.isInvalid("1188511885"));
        assertEquals("446446", Day2.isInvalid("446446"));
        assertEquals("38593859", Day2.isInvalid("38593859"));
    }

    @Test
    public void testIsInvalid2() {
        assertEquals("11", Day2.isInvalid2("11"));
        assertEquals("22", Day2.isInvalid2("22"));
        assertEquals("99", Day2.isInvalid2("99"));
        assertEquals("111", Day2.isInvalid2("111"));
        assertEquals("999", Day2.isInvalid2("999"));
        assertEquals("1010", Day2.isInvalid2("1010"));
        assertEquals("1188511885", Day2.isInvalid2("1188511885"));
        assertEquals("222222", Day2.isInvalid2("222222"));
        assertEquals("446446", Day2.isInvalid2("446446"));
        assertEquals("38593859", Day2.isInvalid2("38593859"));
        assertEquals("565656", Day2.isInvalid2("565656"));
        assertEquals("824824824", Day2.isInvalid2("824824824"));
        assertEquals("2121212121", Day2.isInvalid2("2121212121"));
    }
}
