package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;
import java.util.stream.Collectors;

public class Day21 extends Day {

    private Set<String> intersect(Set<String> s1, Set<String> s2) {
        if (s1 == null) return s2;
        if (s2 == null) return s1;
        s1.retainAll(s2);
        return s1;
    }

    public Object part1(List<String> input) {

        Map<String, Set<String>> allergsWithIngredList = new HashMap<>();
        Map<String, String> ingredsWithAllergs = new HashMap<>();
        Map<String, String> allergsWithIngreds = new HashMap<>();
        Map<String, Integer> ingredAppears = new HashMap<>();

        for (String line : input) {
            String[] s = line.substring(0, line.length()-1).split("\\s\\(contains\\s");
            String[] ingreds = s[0].split("\\s");
            String[] allergs = s[1].split(",\\s");
            for (String a : allergs) {
                allergsWithIngredList.put(a, intersect(allergsWithIngredList.get(a), new HashSet<>(Arrays.asList(ingreds))));
            }
            for (String i : ingreds) {
                ingredsWithAllergs.put(i, "");
                if (ingredAppears.containsKey(i)) {
                    ingredAppears.put(i, ingredAppears.get(i) + 1);
                } else {
                    ingredAppears.put(i, 1);
                }
            }
        }

        while(allergsWithIngredList.size() != allergsWithIngreds.size()) {
            for (String a : allergsWithIngredList.keySet()) {
                if (allergsWithIngredList.get(a).size() == 1) {
                    String i = allergsWithIngredList.get(a).iterator().next();
                    ingredsWithAllergs.put(i, a);
                    allergsWithIngreds.put(a, i);
                    for (String a2 : allergsWithIngredList.keySet()) {
                        allergsWithIngredList.get(a2).remove(i);
                    }
                }
            }
        }

        String sb = allergsWithIngreds.keySet().stream().sorted().collect(Collectors.toList()).stream().map(s -> allergsWithIngreds.get(s) + ",").collect(Collectors.joining());
        System.out.println(sb.substring(0, sb.length()-1));

        int sum = 0;
        for (String s : ingredsWithAllergs.keySet()) {
            if (ingredsWithAllergs.get(s).isEmpty()) {
                sum += ingredAppears.get(s);
            }
        }
        return sum;
    }

    public Object part2(List<String> input) {
        return 2;
    }

}
