package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.LinkedList;
import java.util.List;

public class Day11 extends Day {

    private boolean compareStates(List<String> state1, List<String> state2) {
        for (int i = 0; i < state1.size(); i++) {
            for (int j = 0; j < state1.get(0).length(); j++) {
                if (state1.get(i).charAt(j) != state2.get(i).charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int countOccupiedAdjacent(List<String> input, int posX, int posY) {
        int count = 0;
        for (int i = posX-1; i < posX+2; i++) {
            if (i < 0 || i >= input.size()) {
                continue;
            }
            for (int j = posY - 1; j < posY + 2; j++) {
                if (j < 0 || j >= input.get(0).length() || (i == posX && j == posY)) {
                    continue;
                }
                if (input.get(i).charAt(j) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private int countOccupiedVisible(List<String> input, int row, int col) {
        int count = 0;
        int i;
        int j;
        //up
        for (i = row - 1; i >= 0; i--) {
            if (input.get(i).charAt(col) == 'L') {
                break;
            }
            if (input.get(i).charAt(col) == '#') {
                count++;
                break;
            }
        }
        //down
        for (i = row + 1; i < input.size(); i++) {
            if (input.get(i).charAt(col) == 'L') {
                break;
            }
            if (input.get(i).charAt(col) == '#') {
                count++;
                break;
            }
        }
        //left
        for (i = col - 1; i >= 0; i--) {
            if (input.get(row).charAt(i) == 'L') {
                break;
            }
            if (input.get(row).charAt(i) == '#') {
                count++;
                break;
            }
        }
        //right
        for (i = col + 1; i < input.get(0).length(); i++) {
            if (input.get(row).charAt(i) == 'L') {
                break;
            }
            if (input.get(row).charAt(i) == '#') {
                count++;
                break;
            }
        }
        //upleft
        i = row - 1; j = col - 1;
        while (i >= 0 && j >= 0) {
            if (input.get(i).charAt(j) == 'L') {
                break;
            }
            if (input.get(i).charAt(j) == '#') {
                count++;
                break;
            }
            i--; j--;
        }
        //downleft
        i = row + 1; j = col - 1;
        while (i < input.size() && j >= 0) {
            if (input.get(i).charAt(j) == 'L') {
                break;
            }
            if (input.get(i).charAt(j) == '#') {
                count++;
                break;
            }
            i++; j--;
        }
        //upright
        i = row - 1; j = col + 1;
        while (i >= 0 && j < input.get(0).length()) {
            if (input.get(i).charAt(j) == 'L') {
                break;
            }
            if (input.get(i).charAt(j) == '#') {
                count++;
                break;
            }
            i--; j++;
        }
        //downright
        i = row + 1; j = col + 1;
        while (i < input.size() && j < input.get(0).length()) {
            if (input.get(i).charAt(j) == 'L') {
                break;
            }
            if (input.get(i).charAt(j) == '#') {
                count++;
                break;
            }
            i++; j++;
        }

        return count;
    }

    private int countOccupied(List<String> input) {
        int count = 0;
        for (String s : input) {
            for (int j = 0; j < input.get(0).length(); j++) {
                if (s.charAt(j) == '#') {
                    count++;
                }
            }
        }
        return count;
    }

    private List<String> getNextState(List<String> currentState) {
        List<String> nextState = new LinkedList<>();
        for (int i = 0; i < currentState.size(); i++) {
            StringBuilder newLine = new StringBuilder();
            for (int j = 0; j < currentState.get(0).length(); j++) {
                char currentPos = currentState.get(i).charAt(j);
                if (currentPos == 'L' && countOccupiedAdjacent(currentState, i, j) == 0){
                    newLine.append('#');
                } else if (currentPos == '#' && countOccupiedAdjacent(currentState, i, j) >= 4){
                    newLine.append('L');
                } else {
                    newLine.append(currentPos);
                }
            }
            nextState.add(newLine.toString());
        }
        return nextState;
    }

    private List<String> getNextState2(List<String> currentState) {
        List<String> nextState = new LinkedList<>();
        for (int i = 0; i < currentState.size(); i++) {
            StringBuilder newLine = new StringBuilder();
            for (int j = 0; j < currentState.get(0).length(); j++) {
                char currentPos = currentState.get(i).charAt(j);
                if (currentPos == 'L' && countOccupiedVisible(currentState, i, j) == 0){
                    newLine.append('#');
                } else if (currentPos == '#' && countOccupiedVisible(currentState, i, j) >= 5){
                    newLine.append('L');
                } else {
                    newLine.append(currentPos);
                }
            }
            nextState.add(newLine.toString());
        }
        return nextState;
    }

    public Object part1(List<String> input) {

        List<String> currentState = input;
        List<String> nextState = getNextState(input);
        while (true) {
            if (compareStates(currentState, nextState)) {
                return countOccupied(nextState);
            }
            currentState = nextState;
            nextState = getNextState(currentState);
        }

    }

    public Object part2(List<String> input) {
        List<String> currentState = input;
        List<String> nextState = getNextState2(input);
        while (true) {
            if (compareStates(currentState, nextState)) {
                return countOccupied(nextState);
            }
            currentState = nextState;
            nextState = getNextState2(currentState);
        }
    }

}
