package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 extends Day {

    public Object part1(List<String> input) {
        List<Integer> intList = input.stream().map(Integer::valueOf).collect(Collectors.toList());
        intList.add(0);
        intList.sort(null);
        int one = 0;
        int two = 0;
        int three = 1;
        for (int i = 0; i < intList.size() - 1; i++) {
            int dif = intList.get(i + 1)-intList.get(i);
            if (dif == 1) {
                one++;
            } else if (dif == 2) {
                two++;
            } else if (dif == 3) {
                three++;
            }
        }
        return one * three;
    }

    private long recursion(Map<Integer, List<Integer>> treeMap, Map<Integer, Long> routMap, int current) {
        if (routMap.containsKey(current)) {
            return routMap.get(current);
        }
        long sum = 0;
        for (int i : treeMap.get(current)) {
            sum += recursion(treeMap, routMap, i);
        }
        routMap.put(current, sum);
        return sum;
    }

    public Object part2(List<String> input) {
        List<Integer> intList = input.stream().map(Integer::valueOf).collect(Collectors.toList());
        intList.add(0);
        intList.sort(null);
        int myJoltage = intList.get(intList.size() - 1) + 3;
        intList.add(myJoltage);
        Map<Integer, List<Integer>> treeMap = new HashMap<>();
        Map<Integer, Long> routMap = new HashMap<>();
        for (int i = 0; i < intList.size() - 1; i++) {
            List<Integer> list = new LinkedList<>();
            list.add(intList.get(i + 1));
            if (i + 2 < intList.size()) {
                if (intList.get(i + 2) - intList.get(i) <= 3) {
                    list.add(intList.get(i + 2));
                }
            }
            if (i + 3 < intList.size()) {
                if (intList.get(i + 3) - intList.get(i) <= 3) {
                    list.add(intList.get(i + 3));
                }
            }
            treeMap.put(intList.get(i), list);
        }
        treeMap.put(myJoltage, new LinkedList<>());
        routMap.put(myJoltage, (long) 1);

        return recursion(treeMap, routMap, 0);
    }

}
