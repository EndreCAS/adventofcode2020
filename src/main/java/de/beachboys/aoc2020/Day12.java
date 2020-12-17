package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.List;

public class Day12 extends Day {

    // E:0+, S:1+, W:2-, N:3-
    public Object part1(List<String> input) {
        int EW = 0;
        int SN = 0;
        int facing = 0;
        for (String s : input) {
            int value = Integer.parseInt(s.substring(1));
            switch (s.charAt(0)) {
                case 'S':
                    SN += value;
                    break;
                case 'N':
                    SN -= value;
                    break;
                case 'E':
                    EW += value;
                    break;
                case 'W':
                    EW -= value;
                    break;
                case 'L':
                    facing = (facing - value / 90) % 4;
                    break;
                case 'R':
                    facing = (facing + value / 90) % 4;
                    break;
                case 'F':
                    switch (facing) {
                        case 0:
                            EW += value;
                            break;
                        case 1:
                        case -3:
                            SN += value;
                            break;
                        case 2:
                        case -2:
                            EW -= value;
                            break;
                        case 3:
                        case -1:
                            SN -= value;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return Math.abs(EW) + Math.abs(SN);
    }

    public Object part2(List<String> input) {
        int wpEW = 10;
        int wpSN = -1;
        int EW = 0;
        int SN = 0;
        int temp;
        for (String s : input) {
            int value = Integer.parseInt(s.substring(1));
            switch (s.charAt(0)) {
                case 'N':
                    wpSN -= value;
                    break;
                case 'S':
                    wpSN += value;
                    break;
                case 'E':
                    wpEW += value;
                    break;
                case 'W':
                    wpEW -= value;
                    break;
                case 'F':
                    EW += value * wpEW;
                    SN += value * wpSN;
                    break;
                case 'L':
                    value = 360 - value;
                case 'R':
                    int turn = (value / 90) % 4;
                    switch (turn) {
                        case 0:
                            break;
                        case 1:
                        case -3:
                            temp = wpEW;
                            wpEW = -1 * wpSN;
                            wpSN = temp;
                            break;
                        case 2:
                        case -2:
                            wpEW = -1 * wpEW;
                            wpSN = -1 * wpSN;
                            break;
                        case 3:
                        case -1:
                            temp = wpEW;
                            wpEW = wpSN;
                            wpSN = -1 * temp;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
        return Math.abs(EW) + Math.abs(SN);
    }

}
