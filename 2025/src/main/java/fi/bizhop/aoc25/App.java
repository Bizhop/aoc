package fi.bizhop.aoc25;

import fi.bizhop.aoc25.day1.Day1;
import fi.bizhop.aoc25.day2.Day2;
import fi.bizhop.aoc25.day3.Day3;
import fi.bizhop.aoc25.day4.Day4;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int day = 4;
        final boolean testInput = false;

        switch (day) {
            case 1 -> new Day1().solve(testInput);
            case 2 -> new Day2().solve(testInput);
            case 3 -> new Day3().solve(testInput);
            case 4 -> new Day4().solve(testInput);
            default -> System.out.println("Valid day number needed");
        }
    }
}
