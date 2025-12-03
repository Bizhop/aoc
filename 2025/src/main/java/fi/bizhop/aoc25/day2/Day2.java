package fi.bizhop.aoc25.day2;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.LongStream;

public class Day2 {
    public void solve(boolean testInput) throws IOException {
        var input = testInput
                ? "11-22,95-115,998-1012,1188511880-1188511890,222220-222224,1698522-1698528,446443-446449,38593856-38593862,565653-565659,824824821-824824827,2121212118-2121212124"
                : Resources.toString(Resources.getResource("day2input.txt"), StandardCharsets.UTF_8);

        this.solvePart1(input);
        this.solvePart2(input);
    }

    private void solvePart1(String input) {
        var ranges = input.split(",");
        var results = Arrays.stream(ranges)
                .map(rangeString -> {
                    var rangeBeginAndEnd = rangeString.split("-");
                    long begin = Long.parseLong(rangeBeginAndEnd[0]);
                    long end = Long.parseLong(rangeBeginAndEnd[1]);
                    return LongStream.range(begin, end + 1)
                            .mapToObj(String::valueOf)
                            .map(Day2::isInvalid)
                            .filter(Objects::nonNull)
                            .toList();
                })
                .flatMap(List::stream)
                .toList();

        var sumOfInvalidIds = results.stream()
                .map(Long::parseLong)
                .reduce(0L, Long::sum);
        System.out.printf("Part1: Sum of invalid ID:s is: %d%n", sumOfInvalidIds);
    }

    private void solvePart2(String input) {
        var ranges = input.split(",");
        var results = Arrays.stream(ranges)
                .map(rangeString -> {
                    var rangeBeginAndEnd = rangeString.split("-");
                    long begin = Long.parseLong(rangeBeginAndEnd[0]);
                    long end = Long.parseLong(rangeBeginAndEnd[1]);
                    return LongStream.range(begin, end + 1)
                            .mapToObj(String::valueOf)
                            .map(Day2::isInvalid2)
                            .filter(Objects::nonNull)
                            .toList();
                })
                .flatMap(List::stream)
                .toList();

        var sumOfInvalidIds = results.stream()
                .map(Long::parseLong)
                .reduce(0L, Long::sum);
        System.out.printf("Part2: Sum of invalid ID:s is: %d%n", sumOfInvalidIds);
    }

    //returns the String if invalid, null if valid
    public static String isInvalid(String id) {
        //odd length is always valid
        if(id.length() % 2 == 1) return null;

        //compare halves
        return id.substring(0, id.length() / 2).equals(id.substring(id.length() / 2)) ? id : null;
    }

    public static String isInvalid2(String id) {
        //length one cannot contain any duplicates
        if(id.length() == 1) return null;

        //get possible dividers
        List<Integer> possibleDividers = new ArrayList<>();
        for(int i=2; i <= id.length(); i++) {
            if(id.length() % i == 0) possibleDividers.add(i);
        }

        for(final var divider : possibleDividers) {
            var parts = splitIntoParts(id, divider);
            if(allPartsAreEqual(parts)) return id;
        }
        return null;
    }

    private static List<String> splitIntoParts(String id, int numParts) {
        int length = id.length();
        int partSize = length / numParts;

        List<String> parts = new ArrayList<>();

        for (int i = 0; i < numParts; i++) {
            int start = i * partSize;
            int end = start + partSize;
            parts.add(id.substring(start, end));
        }

        return parts;
    }

    private static boolean allPartsAreEqual(List<String> parts) {
        for(int i=0; i < parts.size() - 1; i++) {
            if(!parts.get(i).equals(parts.get(i+1))) return false;
        }
        return true;
    }
}
