package fi.bizhop.aoc24.day2;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void solve(boolean testInput) throws IOException {
        var input = testInput
                ? "7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9\n"
                : Files.asCharSource(new File("input/day2input.txt"), StandardCharsets.UTF_8).read();

        System.out.println(solve(input));
        System.out.println(solvePart2(input));
    }

    private static long solve(String input) {
        var reports = new ArrayList<List<Integer>>();

        for(var line : input.split("\n")) {
            var level = new ArrayList<Integer>();
            for (var number : line.split(" ")) {
                level.add(Integer.parseInt(number));
            }
            reports.add(level);
        }

        return reports.stream()
                .filter(Day2::reportIsGood)
                .count();
    }

    private static long solvePart2(String input) {
        var reports = new ArrayList<List<Integer>>();

        for(var line : input.split("\n")) {
            var level = new ArrayList<Integer>();
            for (var number : line.split(" ")) {
                level.add(Integer.parseInt(number));
            }
            reports.add(level);
        }

        return reports.stream()
                .filter(Day2::reportIsGoodWithProblemDampener)
                .count();
    }

    private static boolean reportIsGood(List<Integer> levels) {
        return (allIncreasing(levels) || allDecreasing(levels)) && safeDistance(levels);
    }

    private static boolean reportIsGoodWithProblemDampener(List<Integer> levels) {
        //try first without changing anything
        if((allIncreasing(levels) || allDecreasing(levels)) && safeDistance(levels)) {
            return true;
        }

        //try all combinations removing one level
        for (var i = 0; i < levels.size(); i++) {
            var newLevels = new ArrayList<>(levels);
            newLevels.remove(i);
            if((allIncreasing(newLevels) || allDecreasing(newLevels)) && safeDistance(newLevels)) {
                return true;
            }
        }
        return false;
    }
    private static boolean allIncreasing(List<Integer> levels) {
        for (var i = 1; i < levels.size(); i++) {
            if (levels.get(i) < levels.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean allDecreasing(List<Integer> levels) {
        for (var i = 1; i < levels.size(); i++) {
            if (levels.get(i) > levels.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean safeDistance(List<Integer> levels) {
        for (var i = 1; i < levels.size(); i++) {
            var distance = Math.abs(levels.get(i) - levels.get(i - 1));
            if(distance < 1 || distance > 3) {
                return false;
            }
        }
        return true;
    }
}
