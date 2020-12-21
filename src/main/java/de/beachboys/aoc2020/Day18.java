package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day18 extends Day {

    private long eval(String expr) {
        String[] items = expr.split("\\s");
        long result = Integer.parseInt(items[0]);
        for (int i = 1; i < items.length; i += 2) {
            if (items[i].equals("+")) {
                result += Integer.parseInt(items[i+1]);
            } else if (items[i].equals("*")) {
                result *= Integer.parseInt(items[i+1]);
            }
        }
        return result;
    }

    private long eval2(String expr) {
        String[] mult = expr.split("\\s\\*\\s");
        long result = 1;
        for (String s : mult) {
            if (s.contains("+")) {
                String[] add = s.split("\\s\\+\\s");
                long sum = 0;
                for (String value : add) {
                    sum += Long.valueOf(value);
                }
                result *= sum;
            } else {
                result *= Long.valueOf(s);
            }
        }
        return result;
    }

    private long evalParenth(String expr) {
        if (!expr.contains("(")) {
            return eval2(expr);
        }

        StringBuilder sb = new StringBuilder(expr);
        int openIndex = 0;
        int closeIndex = expr.length();
        for (int i = 0; i < expr.length(); i++) {
            if (expr.charAt(i) == '(') {
                openIndex = i;
            } else if (expr.charAt(i) == ')') {
                closeIndex = i;
                break;
            }
        }
        String parenthesed = expr.substring(openIndex, closeIndex+1);
        long newValue = eval2(parenthesed.substring(1, parenthesed.length()-1));
        String newExpr = expr.replace(parenthesed, Long.toString(newValue));
        return evalParenth(newExpr);
    }

    public Object part1(List<String> input) {
        return input.stream().mapToLong(this::evalParenth).sum();
    }

    public Object part2(List<String> input) { return 2; }

}
