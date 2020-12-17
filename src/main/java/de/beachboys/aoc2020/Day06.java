package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;

public class Day06 extends Day {

    private Set<Character> stringToSet(String input) {
        Set<Character> set = new TreeSet<>();
        for (char c : input.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    private int sizeOfIntersection(Collection<String> collection) {
        Set<Character> base = stringToSet("abcdefghijklmnopqrstuvwxzy");
        for(String line : collection) {
            base.retainAll(stringToSet(line));
        }
        return base.size();
    }

    private int sizeOfUnion(Collection<String> collection) {
        Set<Character> base = new TreeSet<>();
        for (String line : collection) {
            base.addAll(stringToSet(line));
        }
        return base.size();
    }

    private List<List<String>> groupInput(List<String> input) {
        List<List<String>> listOfGroups = new LinkedList<>();
        List<String> group = new LinkedList<>();
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            if (line.length() != 0) {
                group.add(line);
            }
            if (line.length() == 0 || i == input.size()-1) {
                listOfGroups.add(group);
                group = new LinkedList<>();
            }
        }
        return listOfGroups;
    }

    public Object part1(List<String> input) {
        int sum = 0;
        for (List<String> group : groupInput(input)) {
            sum += sizeOfUnion(group);
        }
        return sum;
    }

    public Object part2(List<String> input) {
        int sum = 0;
        for (List<String> group : groupInput(input)) {
            sum += sizeOfIntersection(group);
        }
        return sum;
    }

}
