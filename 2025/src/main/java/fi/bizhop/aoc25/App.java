package fi.bizhop.aoc25;

import fi.bizhop.aoc25.day1.Day1;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        var day = 1;
        var testInput = false;

        switch (day) {
            case 1 -> new Day1().solve(testInput);
            default -> System.out.println("Valid day number needed");
        }
    }
}
