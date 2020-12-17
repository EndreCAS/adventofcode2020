package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;

public class Day07 extends Day {

    private String myBag = "shiny gold";
    private Map<String, Map<String, Integer>> bagRules;

    private Map<String, Map<String, Integer>> processRules(List<String> rules) {
        Map<String, Map<String, Integer>> bagRules = new HashMap<>();

        for (String rule : rules) {
            String[] kv = rule.substring(0, rule.length() - 1).split(" contain "); //remove '.' and split
            String key = kv[0].substring(0, kv[0].length() - 5); //remove bags from key

            if (kv[1].equals("no other bags")) {    // handle the special case
                bagRules.put(key, new HashMap<>());
                continue;
            }

            String[] subRules = kv[1].split(", ");
            Map<String, Integer> map = new HashMap<>();
            for (String subRule : subRules) {
                String[] words = subRule.split("\\s");
                map.put(words[1] + " " + words[2], Integer.parseInt(words[0]));
            }
            bagRules.put(key, map);
        }

        return bagRules;
    }

    public Object part1(List<String> input) {

        bagRules = processRules(input);
        Set<String> resultSet = new TreeSet<>();
        Set<String> setToProcess = new TreeSet<>();
        setToProcess.add(myBag);
        while (!setToProcess.isEmpty()) {
            String id = setToProcess.iterator().next();
            for (String key : bagRules.keySet()) {
                if (bagRules.get(key).containsKey(id)) {
                    setToProcess.add(key);
                    resultSet.add(key);
                }
            }
            setToProcess.remove(id);
        }

        return resultSet.size();
    }

    private int recursion(String currentKey) {
        Map<String, Integer> map = bagRules.get(currentKey);
        if (map.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (String key : map.keySet()) {
            sum = map.get(key) + map.get(key) * recursion(key) + sum;
        }
        return sum;
    }

    public Object part2(List<String> input) {
        int countBags = 0;
        bagRules = processRules(input);
        countBags += recursion(myBag);

        return countBags;
    }

}
