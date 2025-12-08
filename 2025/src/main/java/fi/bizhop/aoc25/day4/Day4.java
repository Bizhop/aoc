package fi.bizhop.aoc25.day4;

import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Day4 {
    public void solve(boolean testInput) throws IOException, InterruptedException {
        var input = testInput
                ? "..@@.@@@@.\n" +
                "@@@.@.@.@@\n" +
                "@@@@@.@.@@\n" +
                "@.@@@@..@.\n" +
                "@@.@@@@.@@\n" +
                ".@@@@@@@.@\n" +
                ".@.@.@.@@@\n" +
                "@.@@@.@@@@\n" +
                ".@@@@@@@@.\n" +
                "@.@.@@@.@."
                : Resources.toString(Resources.getResource("day4input.txt"), StandardCharsets.UTF_8);

        this.solvePart1(input);
        this.solvePart2(input);
    }

    private void solvePart1(String input) {
        var lines = Arrays.stream(input.split("\n")).toList();

        List<List<Boolean>> grid = new ArrayList<>();
        for(String line : lines) {
            List<Boolean> listRow = new ArrayList<>();
            for(Character character : line.toCharArray()) {
                listRow.add(character.equals('@'));
            }
            grid.add(listRow);
        }

        int accessableRolls = 0;
        for(int i = 0; i < grid.size(); i++) {
            var line = grid.get(0);
            for(int j = 0; j < line.size(); j++) {
                if(grid.get(i).get(j)) {
                    var thisAndAdjacent = getThisAndAdjacentCells(grid, i, j);
                    var count = thisAndAdjacent.stream()
                            .filter(Boolean::booleanValue)
                            .count();
                    if (count < 5) accessableRolls++;
                }
            }
        }

        System.out.println(accessableRolls);
    }

    private void solvePart2(String input) throws InterruptedException {
        var lines = Arrays.stream(input.split("\n")).toList();

        List<List<Boolean>> grid = new ArrayList<>();
        for(String line : lines) {
            List<Boolean> listRow = new ArrayList<>();
            for(Character character : line.toCharArray()) {
                listRow.add(character.equals('@'));
            }
            grid.add(listRow);
        }

        int rollsRemoved = 0;
        boolean keepRemoving = true;

        var gridVisual = new GridVisual(grid);
        while(keepRemoving) {
            int begin = rollsRemoved;
            for (int i = 0; i < grid.size(); i++) {
                var line = grid.get(0);
                for (int j = 0; j < line.size(); j++) {
                    if (grid.get(i).get(j)) {
                        var thisAndAdjacent = getThisAndAdjacentCells(grid, i, j);
                        var count = thisAndAdjacent.stream()
                                .filter(Boolean::booleanValue)
                                .count();
                        if (count < 5) {
                            gridVisual.updateCell(i, j, false);
                            //grid.get(i).set(j, false);
                            rollsRemoved++;
                            Thread.sleep(1);
                        }
                    }
                }
            }
            if(begin == rollsRemoved) keepRemoving = false;
        }

        System.out.println(rollsRemoved);
    }

    private List<Boolean> getThisAndAdjacentCells(List<List<Boolean>> grid, int x, int y) {
        if(x < 0 || y < 0) throw new RuntimeException("Illegal call");

        int rows = grid.size();
        int columns = grid.get(0).size();
        if(x > columns - 1 || y > rows - 1) throw new RuntimeException("Illegal call");

        int leftBound = x - 1 < 0 ? x : x - 1;
        int rightBound = x + 1 > columns - 1 ? x : x + 1;
        int topBound = y - 1 < 0 ? y : y - 1;
        int bottomBound = y + 1 > rows - 1 ? y : y + 1;

        List<Boolean> adjacentCells = new ArrayList<>();
        for(int i = leftBound; i <= rightBound; i++) {
            for(int j = topBound; j <= bottomBound; j++) {
                adjacentCells.add(grid.get(i).get(j));
            }
        }
        return adjacentCells;
    }
}
