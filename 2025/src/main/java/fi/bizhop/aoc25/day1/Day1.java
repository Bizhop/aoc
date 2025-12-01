package fi.bizhop.aoc25.day1;

import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day1 {
    public void solve(boolean testInput) throws IOException {
        var input = testInput
                ? "L68\n" +
                "L30\n" +
                "R48\n" +
                "L5\n" +
                "R60\n" +
                "L55\n" +
                "L1\n" +
                "L99\n" +
                "R14\n" +
                "L82"
                : Resources.toString(Resources.getResource("day1input.txt"), StandardCharsets.UTF_8);

        System.out.println("Day 1 solution: " + solve(input));
        System.out.println("Day 1, part 2 solution: " + solvePart2(input));
    }

    private long solve(String inputstring) {
        var instructionList = Arrays.stream(inputstring.split("\n")).toList();
        var dial = new Dial(50);

        return instructionList.stream()
                .map(Instruction::parseInstruction)
                .map(instruction -> {
                    return Dial.DIAL_VALUE_MIN == switch (instruction.direction) {
                        case "L" -> dial.turnLeft(instruction.steps);
                        case "R" -> dial.turnRight(instruction.steps);
                        default -> throw new RuntimeException("Invalid instruction: %s".formatted(instruction));
                    };
                })
                .filter(value -> value)
                .count();
    }

    private int solvePart2(String inputstring) {
        var instructionList = Arrays.stream(inputstring.split("\n")).toList();
        var dial = new Dial(50);

        return instructionList.stream()
                .map(Instruction::parseInstruction)
                .map(instruction -> {
                    return switch (instruction.direction) {
                        case "L" -> dial.turnLeftPart2(instruction.steps);
                        case "R" -> dial.turnRightPart2(instruction.steps);
                        default -> throw new RuntimeException("Invalid instruction: %s".formatted(instruction));
                    };
                })
                .reduce(0, Integer::sum);
    }

    record Instruction(String direction, int steps) {
        static Instruction parseInstruction(String instruction) {
            return new Instruction(
                    instruction.substring(0, 1),
                    Integer.parseInt(instruction.substring(1)));
        }
    }
}
