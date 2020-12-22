package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day22 extends Day {

    private int calculateResult(List<Integer> cards) {
        return IntStream.range(0, cards.size()).map(i -> cards.get(i) * (cards.size() - i)).sum();
    }

    // return p1 wins: true; p2 wins: false
    private boolean playCombat(List<Integer> p1, List<Integer> p2) {
        while (!p1.isEmpty() && !p2.isEmpty()) {
            if (p1.get(0) > p2.get(0)) {
                p1.add(p1.get(0));
                p1.add(p2.get(0));
            } else if (p1.get(0) < p2.get(0)) {
                p2.add(p2.get(0));
                p2.add(p1.get(0));
            }
            p1.remove(0);
            p2.remove(0);
        }

        return p2.isEmpty();
    }

    // return p1 wins: true; p2 wins: false
    private boolean playRecursiveCombat(List<Integer> p1, List<Integer> p2) {
        Set<String> rounds = new HashSet<>();
        while (!p1.isEmpty() && !p2.isEmpty()) {
            if (rounds.contains(roundToString(p1, p2))) {
                return true;
            }
            rounds.add(roundToString(p1, p2));
            if (p1.get(0) <= p1.size()-1 && p2.get(0) <= p2.size()-1) {
                List<Integer> p11 = IntStream.rangeClosed(1, p1.get(0)).mapToObj(p1::get).collect(Collectors.toCollection(LinkedList::new));
                List<Integer> p22 = IntStream.rangeClosed(1, p2.get(0)).mapToObj(p2::get).collect(Collectors.toCollection(LinkedList::new));
                if (playRecursiveCombat(p11, p22)) {
                    p1.add(p1.get(0));
                    p1.add(p2.get(0));
                } else {
                    p2.add(p2.get(0));
                    p2.add(p1.get(0));
                }
            } else if (p1.get(0) > p2.get(0)) {
                p1.add(p1.get(0));
                p1.add(p2.get(0));
            } else if (p1.get(0) < p2.get(0)) {
                p2.add(p2.get(0));
                p2.add(p1.get(0));
            }
            p1.remove(0);
            p2.remove(0);
        }

        return p2.isEmpty();
    }

    private void parse(List<String> input, List<Integer> p1, List<Integer> p2) {
        boolean p1Cards = true;
        for (int i = 1; i < input.size(); i++) {
            String current = input.get(i);
            if (current.equals("")) {
                continue;
            }
            if (current.equals("Player 2:")) {
                p1Cards = false;
                continue;
            }
            if (p1Cards) {
                p1.add(Integer.valueOf(current));
            } else {
                p2.add(Integer.valueOf(current));
            }
        }
    }

    private String roundToString(List<Integer> p1, List<Integer> p2) {
        return p1.stream().map(String::valueOf).collect(Collectors.joining(",")) + "|"
                + p2.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    public Object part1(List<String> input) {
        List<Integer> p1 = new LinkedList<>();
        List<Integer> p2 = new LinkedList<>();
        parse(input, p1, p2);
        return playCombat(p1, p2) ? calculateResult(p1) : calculateResult(p2);
    }

    public Object part2(List<String> input) {
        List<Integer> p1 = new LinkedList<>();
        List<Integer> p2 = new LinkedList<>();
        parse(input, p1, p2);
        return playRecursiveCombat(p1, p2) ? calculateResult(p1) : calculateResult(p2);
    }

}
