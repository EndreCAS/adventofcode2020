package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 extends Day {

    private long maskedValue(long value, String mask) {
        StringBuilder binary = new StringBuilder(Long.toBinaryString(value));
        while (binary.length() < mask.length()) {
            binary.insert(0, '0');
        }
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != 'X') {
                binary.setCharAt(i, mask.charAt(i));
            }
        }
        return Long.parseLong(binary.toString(), 2);
    }

    private String maskedMem(long mem, String mask) {
        StringBuilder maskedMem = new StringBuilder(Long.toBinaryString(mem));
        while (maskedMem.length() < mask.length()) {
            maskedMem.insert(0, '0');
        }
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != '0') {
                maskedMem.setCharAt(i, mask.charAt(i));
            }
        }
        return maskedMem.toString();
    }

    private void recursion(String maskedMem, long value, Map<String, Long> map) {
        if (!maskedMem.contains("X")) {
            map.put(maskedMem, value);
            return;
        }
        for (int i = 0; i < maskedMem.length(); i++) {
            if (maskedMem.charAt(i) == 'X') {
                recursion(maskedMem.substring(0, i) + '0' + maskedMem.substring(i + 1), value, map);
                recursion(maskedMem.substring(0, i) + '1' + maskedMem.substring(i + 1), value, map);
                break;
            }
        }
    }

    public Object part1(List<String> input) {
        long sum = 0;
        String mask = "";
        Map<Integer, Long> map = new HashMap<>();
        for (String current : input) {
            if (current.startsWith("mask")) {
                mask = current.substring(7);
            }
            if (current.startsWith("mem")) {
                String[] kv = current.split("] = ");
                int key = Integer.parseInt(kv[0].substring(4));
                long value = maskedValue(Long.parseLong(kv[1]), mask);
                map.put(key, value);
            }
        }
        for (Long i: map.values()) {
            sum += i;
        }

        return sum;
    }

    public Object part2(List<String> input) {
        long sum = 0;
        String mask = "";
        Map<String, Long> map = new HashMap<>();
        for (String current : input) {
            if (current.startsWith("mask")) {
                mask = current.substring(7);
                continue;
            }
            if (current.startsWith("mem")) {
                String[] kv = current.split("] = ");
                long mem = Long.parseLong(kv[0].substring(4));
                long value = Long.parseLong(kv[1]);
                String maskedMem = maskedMem(mem, mask);
                recursion(maskedMem, value, map);
            }
        }
        for (Long i: map.values()) {
            sum += i;
        }

        return sum;
    }

}
