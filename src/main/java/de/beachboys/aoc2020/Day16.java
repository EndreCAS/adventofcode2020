package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;

public class Day16 extends Day {

    private boolean matchRule(Set<Integer> setOfPossibleValue, List<Integer> col) {
        for (Integer i : col) {
            if (!setOfPossibleValue.contains(i)) {
                return false;
            }
        }
        return true;
    }

    private void addIntervalToSet(String interval, Set<Integer> set) {
        String[] s = interval.split("-");
        int start = Integer.parseInt(s[0]);
        int end = Integer.parseInt(s[1]);
        for (int i = start; i <= end; i++) {
            set.add(i);
        }
    }

    public Object part1(List<String> input) {
        Set<Integer> validNums = new TreeSet<>();
        List<Integer> invalidNums = new LinkedList<>();
        int phase = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).length() == 0) {
                if (input.get(i+1).startsWith("your ticket:")) {
                    phase = 1;
                } else if (input.get(i+1).startsWith("nearby tickets:")) {
                    phase = 2;
                }
                i++;
                continue;
            }
            switch (phase) {
                case 0:
                    String[] s1 = input.get(i).split(": ");
                    String[] s2 = s1[1].split(" or ");
                    addIntervalToSet(s2[0], validNums);
                    addIntervalToSet(s2[1], validNums);
                    break;
                case 1:
                    break;
                case 2:
                    String[] strings =  input.get(i).split(",");
                    for (String s : strings) {
                        int current = Integer.parseInt(s);
                        if (!validNums.contains(current)) {
                            invalidNums.add(current);
                        }
                    }
                    break;
                default:
                    break;
            }
        }



        int sum = 0;
        for (Integer invalidNum : invalidNums) {
            sum += invalidNum;
        }
        return sum;
    }

    public Object part2(List<String> input) {

        Map<String, Set<Integer>> rules = new HashMap<>();
        Set<Integer> allValidNums = new TreeSet<>();
        List<List<Integer>> validTickets = new LinkedList<>();
        List<Integer> myTicket = new LinkedList<>();
        List<Set<String>> ruleOrder = new LinkedList<>();

        int phase = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).length() == 0) {
                if (input.get(i+1).startsWith("your ticket:")) {
                    phase = 1;
                } else if (input.get(i+1).startsWith("nearby tickets:")) {
                    phase = 2;
                }
                i++;
                continue;
            }
            switch (phase) {
                case 0:
                    String[] wholeRule = input.get(i).split(": ");
                    String[] ruleValues = wholeRule[1].split(" or ");
                    Set<Integer> validNums = new TreeSet<>();
                    addIntervalToSet(ruleValues[0], validNums);
                    addIntervalToSet(ruleValues[1], validNums);
                    rules.put(wholeRule[0], validNums);
                    allValidNums.addAll(validNums);
                    break;
                case 1:
                    String[] myTic =  input.get(i).split(",");
                    for (String s : myTic) {
                        myTicket.add(Integer.parseInt(s));
                    }
                    break;
                case 2:
                    String[] tic =  input.get(i).split(",");
                    List<Integer> ticket = new LinkedList<>();
                    boolean valid = true;
                    for (String s : tic) {
                        int current = Integer.parseInt(s);
                        if (allValidNums.contains(current)) {
                            ticket.add(current);
                        } else {
                            valid = false;
                            break;
                        }
                    }
                    if (valid) {
                        validTickets.add(ticket);
                    }
                    break;
                default:
                    break;
            }
        }

        List<List<Integer>> columns = new LinkedList<>();
        for (int i = 0; i < validTickets.get(0).size(); i++) {
            List<Integer> col = new LinkedList<>();
            for (List<Integer> validTicket : validTickets) {
                col.add(validTicket.get(i));
            }
            columns.add(col);
        }

        for (List<Integer> column : columns) {
            Set<String> possibleRules = new HashSet<>();
            for (String key : rules.keySet()) {
                if (matchRule(rules.get(key), column)) {
                    possibleRules.add(key);
                }
            }
            ruleOrder.add(possibleRules);
        }

        Map<String, Integer> fixedOrder = new HashMap<>();
        while (fixedOrder.size() != ruleOrder.size()) {
            String actualRule = "";
            for (int i = 0; i < ruleOrder.size(); i++) {
                if (ruleOrder.get(i).size() == 1) {
                    actualRule = ruleOrder.get(i).stream().findFirst().get();
                    fixedOrder.put(actualRule, i);
                    break;
                }
            }
            if (!actualRule.isEmpty()) {
                for (Set<String> strings : ruleOrder) {
                    strings.remove(actualRule);
                }
            }
        }

        long mult = 1;
        for (String key : fixedOrder.keySet()) {
            if (key.startsWith("departure")) {
                mult *= myTicket.get(fixedOrder.get(key));
            }
        }

        return mult;
    }

}
