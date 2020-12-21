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

    private long evalParenth(String expr) {
        if (!expr.contains("(")) {
            return eval(expr);
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
        long newValue = eval(parenthesed.substring(1, parenthesed.length()-1));
        String newExpr = expr.replace(parenthesed, Long.toString(newValue));
        return evalParenth(newExpr);
    }

    public Object part1(List<String> input) {
//        System.out.println(evalParenth("8 + (4 + 2 * 2 * 2 + 5) + 2 + (7 * (7 + 7 + 7 + 7) + 7 + 2) * ((8 + 9 * 6 + 6 * 5) + 6 + 7 * 4 * (3 + 8 * 6 + 5 + 3 * 6) * 7) * 5"));
//        System.out.println(evalParenth("1 + 2 * 3 + 4 * 5 + 6"));
//        System.out.println(evalParenth("1 + (2 * 3) + (4 * (5 + 6))"));
//        System.out.println(evalParenth("2 * 3 + (4 * 5)"));
//        System.out.println(evalParenth("5 + (8 * 3 + 9 + 3 * 4 * 3)"));
//        System.out.println(evalParenth("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"));
//        System.out.println(evalParenth("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"));

        long sum = 0;
        for (String s : input) {
            sum += evalParenth(s);
            System.out.println(evalParenth(s));
        }


        return sum;
    }

    public Object part2(List<String> input) {
        return 2;
    }

}
