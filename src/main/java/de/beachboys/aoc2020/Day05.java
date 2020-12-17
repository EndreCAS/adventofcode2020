package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

public class Day05 extends Day {

    private int getSeatID(String seat) {
        int row = Integer.parseInt(seat.substring(0, 7).
                replaceAll("B", "1").
                replaceAll("F", "0"), 2);
        int col = Integer.parseInt(seat.substring(7).
                replaceAll("R", "1").
                replaceAll("L", "0"), 2);
        return row * 8 + col;
    }

    public Object part1(List<String> input) {
        int max = 0;
        for (String seat : input) {
            int seatID = getSeatID(seat);
            if (max < (seatID))
                max = seatID;
        }
        return max;
    }

    public Object part2(List<String> input) {
        TreeSet<Integer> allSeats = new TreeSet<>();
        for (String seat : input) {
            allSeats.add(getSeatID(seat));
        }
        for (int i = allSeats.first()+1; i < allSeats.last()-1; i++) {
            if (!allSeats.contains(i) && allSeats.contains(i-1) && allSeats.contains(i+1))
                return i;
        }
        return -1;
    }

}
