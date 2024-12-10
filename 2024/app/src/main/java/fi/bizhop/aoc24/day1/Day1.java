package fi.bizhop.aoc24.day1;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Day1 {
    public static void solve(boolean testInput) throws IOException {
        var input = testInput
                ? "3   4\n4   3\n2   5\n1   3\n3   9\n3   3\n"
                : Files.asCharSource(new File("input/day1input.txt"), StandardCharsets.UTF_8).read();

        System.out.println(solve(input));
        System.out.println(solvePart2(input));
    }

    private static int solve(String input) {
        var list1 = new ArrayList<Integer>();
        var list2 = new ArrayList<Integer>();

        for (var line : input.split("\n")) {
            var parts = line.split("   ");
            list1.add(Integer.parseInt(parts[0]));
            list2.add(Integer.parseInt(parts[1]));
        }

        var sorted1 = list1.stream().sorted().toList();
        var sorted2 = list2.stream().sorted().toList();

        assert sorted1.size() == sorted2.size();

        var totalDistance = 0;
        for(var i = 0; i < sorted1.size(); i++) {
            var distance = Math.abs(sorted1.get(i) - sorted2.get(i));
            totalDistance += distance;
        }

        return totalDistance;
    }

    private static int solvePart2(String input) {
        var leftList = new ArrayList<Integer>();
        var rightMap = new HashMap<Integer, Integer>();

        for (var line : input.split("\n")) {
            var parts = line.split("   ");
            leftList.add(Integer.parseInt(parts[0]));

            addToMap(rightMap, Integer.parseInt(parts[1]));
        }

        var similarity = 0;
        for (var left : leftList) {
            if (rightMap.containsKey(left)) {
                similarity += left * rightMap.get(left);
            }
        }

        return similarity;
    }

    private static void addToMap(HashMap<Integer, Integer> map, int value) {
        if (map.containsKey(value)) {
            map.put(value, map.get(value) + 1);
        } else {
            map.put(value, 1);
        }
    }
}
