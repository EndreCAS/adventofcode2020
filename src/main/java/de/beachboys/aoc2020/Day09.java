package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day09 extends Day {

    public Object part1(List<String> input) {
        int preambleLength = 25;
        List<Long> longList = input.stream().map(Long::valueOf).collect(Collectors.toList());
        for (int i = preambleLength; i < longList.size(); i++) {
            boolean hasPair = false;
            for (int j = i - preambleLength; j < i - 1; j++) {
                for (int k = j + 1; k < i; k++) {
                    if (longList.get(i) == longList.get(j) + longList.get(k)) {
                        hasPair = true;
                        break;
                    }
                }
                if (hasPair) {
                    break;
                }
            }
            if (!hasPair) {
                return longList.get(i);
            }
        }
        return 0;
    }

    public Object part2(List<String> input) {
        long numToSumUp = (Long)part1(input);
        List<Long> longList = input.stream().map(Long::valueOf).collect(Collectors.toList());
        for (int i = 2; i < longList.size(); i++) { // size of the contiguous set
            for (int j = 0; j < longList.size() - i; j++) {
                long sum = longList.get(j);
                long max = longList.get(j);
                long min = longList.get(j);
                for (int k = j+1; k < j+i; k++) {
                    sum += longList.get(k);
                    if (max < longList.get(k)) max = longList.get(k);
                    if (min > longList.get(k)) min = longList.get(k);
                }
                if (sum == numToSumUp) {
                    return min + max;
                }
            }
        }
        return 0;
    }

}
