package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day15 extends Day {

    public Object part1(List<String> input) {
        String[] strings =  input.get(0).split(",");
        Map<Long, List<Long>> map = new HashMap<>();
        long lastSpoken = -1;
        long current = -1;
        long turn = 1;
        for (String string : strings) {
            current = Integer.parseInt(string);
            List<Long> list = new LinkedList<>();
            list.add(turn);
            map.put(current, list);
            lastSpoken = current;
            turn++;
        }
        while (turn != 30000001) {  //2021 for part 1
            if (map.get(lastSpoken).size() == 1) {
                current = 0;
                if (!map.containsKey(current)) {
                    map.put(current, new LinkedList<>());
                }
                map.get(current).add(turn);
            } else {
                List<Long> list = map.get(lastSpoken);
                current = list.get(list.size()-1)-list.get(list.size()-2);
                if (!map.containsKey(current)) {
                    map.put(current, new LinkedList<>());
                }
                map.get(current).add(turn);
            }
            lastSpoken = current;
            turn++;
        }
        return current;
    }

    public Object part2(List<String> input) {
        return 2;
    }

}
