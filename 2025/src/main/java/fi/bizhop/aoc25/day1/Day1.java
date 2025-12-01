package fi.bizhop.aoc25.day1;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Day1 {
    private static final int DIAL_VALUE_MIN = 0;
    private static final int DIAL_VALUE_MAX = 99;
    private static final int DIAL_VALUE_OVERTURN_ADJUSTMENT = DIAL_VALUE_MAX - DIAL_VALUE_MIN + 1;

    static class Dial {
        int value;

        Dial(int value) {
            this.value = value;
        }

        //returns the dial value
        int turnLeft(int steps) {
            if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
            this.value = this.value - steps;
            while (this.value < DIAL_VALUE_MIN) {
                this.value += DIAL_VALUE_OVERTURN_ADJUSTMENT;
            }
            return this.value;
        }

        //returns the times the dial hit min value
        int turnLeftPart2(int steps) {
            if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
            int numberOfHits = 0;
            int startValue = this.value;
            int targetValue = this.value - steps;
            while (targetValue < DIAL_VALUE_MIN) {
                targetValue += DIAL_VALUE_OVERTURN_ADJUSTMENT;
                numberOfHits++;
            }
            this.value = targetValue;
            if(startValue == DIAL_VALUE_MIN) {
                numberOfHits--;
            }
            if(this.value == DIAL_VALUE_MIN) {
                numberOfHits++;
            }
            return numberOfHits;
        }

        //returns the dial value
        int turnRight(int steps) {
            if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
            this.value = this.value + steps;
            while(this.value > DIAL_VALUE_MAX) {
                this.value -= DIAL_VALUE_OVERTURN_ADJUSTMENT;
            }
            return value;
        }

        //returns the times the dial hit min value
        int turnRightPart2(int steps) {
            if(steps < 0) throw new RuntimeException("Only positive values allowed in steps");
            int numberOfHits = 0;
            this.value = this.value + steps;
            while(this.value > DIAL_VALUE_MAX) {
                this.value -= DIAL_VALUE_OVERTURN_ADJUSTMENT;
                numberOfHits++;
            }
            return numberOfHits;
        }
    }

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
                : Files.asCharSource(new File("src/main/resources/day1input.txt"), StandardCharsets.UTF_8).read();

        System.out.println("Day 1 solution: " + solve(input));
        System.out.println("Day 1, part 2 solution: " + solvePart2(input));
    }

    private long solve(String inputstring) {
        var instructionList = Arrays.stream(inputstring.split("\n")).toList();
        var dial = new Dial(50);

        return instructionList.stream()
                .map(Instruction::parseInstruction)
                .map(instruction -> {
                    return DIAL_VALUE_MIN == switch (instruction.direction) {
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
