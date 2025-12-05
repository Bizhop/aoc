package fi.bizhop.aoc25.day3;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    public void solve(boolean testInput) throws IOException {
        var input = testInput
                ?
                "987654321111111\n" +
                "811111111111119\n" +
                "234234234234278\n" +
                "818181911112111"
                : Resources.toString(Resources.getResource("day3input.txt"), StandardCharsets.UTF_8);

        this.solvePart1(input);
        this.solvePart2(input);
    }

    private void solvePart1(String input) {
        var banks = input.split("\n");

        var joltages = Arrays.stream(banks)
                .map(bank -> {
                    List<Character> values = bank.chars().mapToObj(c -> (char)c).toList();
                    //find the largest value leaving last battery out
                    int indexOfLargestFirstValue = largestValueIndex(values, 0, values.size() - 1);
                    //find the largest value from that point right
                    int indexOfLargestSecondValue = largestValueIndex(values, indexOfLargestFirstValue + 1, values.size());
                    return "" + values.get(indexOfLargestFirstValue) + values.get(indexOfLargestSecondValue);
                })
                .toList();

        var totalJoltageOutput = joltages.stream()
                .map(Integer::parseInt)
                .reduce(0 , Integer::sum);

        System.out.println(totalJoltageOutput);
    }

    private void solvePart2(String input) {
        var banks = input.split("\n");

        var joltages = Arrays.stream(banks)
                .map(bank -> {
                    List<Character> values = bank.chars().mapToObj(c -> (char)c).toList();
                    List<Character> responseValues = new ArrayList<>();
                    //find the largest value leaving last 11 batteries out
                    int lastFoundIndex = largestValueIndex(values, 0, values.size() - 11);
                    responseValues.add(values.get(lastFoundIndex));
                    //find the rest of the values
                    for(int i=1; i < 12; i++) {
                        lastFoundIndex = largestValueIndex(values, lastFoundIndex + 1, values.size() - 11 + i);
                        responseValues.add(values.get(lastFoundIndex));
                    }
                    StringBuilder response = new StringBuilder();
                    for(Character value : responseValues) {
                        response.append(value);
                    }
                    return response.toString();
                })
                .toList();

        var totalJoltageOutput = joltages.stream()
                .map(Long::parseLong)
                .reduce(0L, Long::sum);

        System.out.println(totalJoltageOutput);
    }

    private int largestValueIndex(List<Character> values, int begin, int end) {
        var valuesSorted = values.subList(begin, end).stream().sorted().toList();
        var largestValue = valuesSorted.get(valuesSorted.size() - 1);
        for(int i=begin; i < end; i++) {
            if(values.get(i).equals(largestValue)) return i;
        }
        throw new RuntimeException("Largest value not found (bug?)");
    }
}
