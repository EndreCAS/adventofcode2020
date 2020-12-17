package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day03 extends Day {

    public Object part1(List<String> input) {
        return treeCounter(3,1, input);
    }

    private long treeCounter(int right, int down, List<String> input) {
        int counter = 0;
        int hPos = right;
        for (int i = down; i < input.size(); i += down) {
            String currentLine = input.get(i);
            if (hPos >= currentLine.length()) {
                hPos -= currentLine.length();
            }
            if (currentLine.charAt(hPos) == '#') {
                counter++;
            }
            hPos += right;
        }
        return counter;
    }

    public Object part2(List<String> input) {
        long a = treeCounter(1, 1, input);
        long b = treeCounter(3, 1, input);
        long c = treeCounter(5, 1, input);
        long d = treeCounter(7, 1, input);
        long e = treeCounter(1, 2, input);

        return a*b*c*d*e;
    }

}
