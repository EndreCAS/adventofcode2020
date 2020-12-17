package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.LinkedList;
import java.util.List;

public class Day08 extends Day {

    public Object part1(List<String> input) {
        int accSum = 0;
        int i = 0;
        List<Integer> visitedInstructions = new LinkedList<>();
        while (!visitedInstructions.contains(i)) {
            visitedInstructions.add(i);
            String line = input.get(i);
            String[] kv = line.split("\\s");
            String ins = kv[0];
            int val = Integer.parseInt(kv[1]);

            switch (ins) {
                case "acc":
                    accSum += val;
                    i++;
                    break;
                case "jmp":
                    i += val;
                    break;
                case "nop":
                    i++;
                    break;
            }
        }

        return accSum;
    }

    public Object part2(List<String> input) {
        for (int j = -1; j < input.size(); j++) {
            int accSum = 0;
            int i = 0;
            List<Integer> visitedInstructions = new LinkedList<>();
            while (!visitedInstructions.contains(i)) {
                if (i >= input.size()) {
                    return accSum;
                }
                visitedInstructions.add(i);
                String line = input.get(i);
                String[] kv = line.split("\\s");
                String instruction = kv[0];
                int value = Integer.parseInt(kv[1]);

                switch (instruction) {
                    case "acc":
                        accSum += value;
                        i++;
                        break;
                    case "jmp":
                        if (i == j) {
                            i++;
                        } else {
                            i += value;
                        }
                        break;
                    case "nop":
                        if (i == j) {
                            i += value;
                        } else {
                            i++;
                        }
                        break;
                }
            }
        }

        return 0;
    }

}
