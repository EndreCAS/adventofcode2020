package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day02 extends Day {

    public Object part1(List<String> input) {
        int min;
        int max;
        char letter;
        String pw;
        int counter = 0;
        for (String line : input) {
            String[] result = line.split("\\s");
            String[] interval = result[0].split("-");
            min = Integer.parseInt(interval[0]);
            max = Integer.parseInt(interval[1]);
            letter = result[1].charAt(0);
            pw = result[2];
            int appearance = 0;
            for (int i = 0; i < pw.length(); i++) {
                if (pw.charAt(i) == letter) {
                    appearance++;
                }
            }
            if (min <= appearance && appearance <= max) {
                counter++;
            }
        }
        return counter;
    }

    public Object part2(List<String> input) {
        int pos1;
        int pos2;
        char letter;
        String pw;
        int counter = 0;
        for (String line : input) {
            String[] result = line.split("\\s");
            String[] positions = result[0].split("-");
            pos1 = Integer.parseInt(positions[0]);
            pos2 = Integer.parseInt(positions[1]);
            letter = result[1].charAt(0);
            pw = result[2];
            if (pw.charAt(pos1-1) == letter ^ pw.charAt(pos2-1) == letter)
                counter++;
        }
        return counter;
    }

}
