package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends Day {

    public Object part1(List<String> input) {
        int depart = Integer.valueOf(input.get(0));
        List<Integer> busIds = Arrays.stream(input.get(1).replaceAll("x,", "").split(",")).map(Integer::valueOf).collect(Collectors.toCollection(LinkedList::new));
        int min = depart;
        int minId = 0;
        for (int id : busIds) {
            int dif = id - (depart % id);
            if (dif < min) {
                min = dif;
                minId = id;
            }
        }
        return min * minId;
    }

    public Object part2(List<String> input) {
        return 2;
    }

}
