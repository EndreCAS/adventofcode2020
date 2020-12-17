package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day17 extends Day {

    private int countActive(List<List<String>> pocketDim) {
        int count = 0;
        for (List<String> strings : pocketDim) {
            for (int j = 0; j < pocketDim.get(0).size(); j++) {
                for (int k = 0; k < pocketDim.get(0).get(0).length(); k++)
                    if (strings.get(j).charAt(k) == '#') {
                        count++;
                    }
            }
        }
        return count;
    }

    private void printState(List<List<String>> pocketDim) {
        for (int i = 0; i < pocketDim.size(); i++) {
            for (int j = 0; j < pocketDim.get(0).size(); j++) {
                System.out.println(pocketDim.get(i).get(j));
            }
            if (i != pocketDim.size()-1) {
                System.out.println("---------");
            }
        }
    }

    private void printNeighbourCounts(List<List<String>> pocketDim) {
        for (int i = 0; i < pocketDim.size(); i++) {
            for (int j = 0; j < pocketDim.get(0).size(); j++) {
                StringBuilder line = new StringBuilder();
                for (int k = 0; k < pocketDim.get(0).get(0).length(); k++) {
                    line.append(countNeighbours(pocketDim, i, j, k)).append(".");
                }
                System.out.println(line);
            }
            if (i != pocketDim.size()-1) {
                System.out.println("---------");
            }
        }
    }

    private int countNeighbours(List<List<String>> pocketDim,  int x, int y, int z) {
        int count = 0;
        for (int i = x - 1; i < x + 2; i++) {
            if (i < 0 || i >= pocketDim.size()) {
                continue;
            }
            for (int j = y - 1; j < y + 2; j++) {
                if (j < 0 || j >= pocketDim.get(0).size()) {
                    continue;
                }
                for (int k = z - 1; k < z + 2; k++) {
                    if (k < 0 || k >= pocketDim.get(0).get(0).length() ||
                            (i == x && j == y && k == z)) {
                        continue;
                    }
                    if (pocketDim.get(i).get(j).charAt(k) == '#') {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private int count4DNeighbours(List<List<List<String>>> pocketDim,  int x, int y, int z, int w) {
        int count = 0;
        for (int i = x - 1; i < x + 2; i++) {
            if (i < 0 || i >= pocketDim.size()) {
                continue;
            }
            for (int j = y - 1; j < y + 2; j++) {
                if (j < 0 || j >= pocketDim.get(0).size()) {
                    continue;
                }
                for (int k = z - 1; k < z + 2; k++) {
                    if (k < 0 || k >= pocketDim.get(0).get(0).size()) {
                        continue;
                    }
                    for (int l = w - 1; l < w + 2; l++) {
                        if (l < 0 || l >= pocketDim.get(0).get(0).get(0).length() ||
                                (i == x && j == y && k == z && l == w)) {
                            continue;
                        }
                        if (pocketDim.get(i).get(j).get(k).charAt(l) == '#') {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

/*
If a cube is active and exactly 2 or 3 of its neighbors are also active, the cube remains active. Otherwise, the cube becomes inactive.
If a cube is inactive but exactly 3 of its neighbors are active, the cube becomes active. Otherwise, the cube remains inactive.
 */
    private List<List<String>> getNextState(List<List<String>> currentState) {
        List<List<String>> newState = new LinkedList<>();
        for (int i = 0; i < currentState.size(); i++) {
            List<String> newLevel = new LinkedList<>();
            for (int j = 0; j < currentState.get(0).size(); j++) {
                StringBuilder newLine = new StringBuilder();
                for (int k = 0; k < currentState.get(0).get(0).length(); k++) {
                    char currentPos = currentState.get(i).get(j).charAt(k);
                    int activeNeighbours = countNeighbours(currentState, i, j, k);
                    if (currentPos == '#' && (activeNeighbours == 2 || activeNeighbours == 3)) {
                        newLine.append('#');
                    } else if (currentPos == '.' && activeNeighbours == 3) {
                        newLine.append('#');
                    } else {
                        newLine.append('.');
                    }
                }
                newLevel.add(newLine.toString());
            }
            newState.add(newLevel);
        }
        return newState;
    }

    private List<List<List<String>>> getNext4DState(List<List<List<String>>> currentState) {
        List<List<List<String>>> newState = new LinkedList<>();
        for (int i = 0; i < currentState.size(); i++) {
            List<List<String>> newSphare = new LinkedList<>();
            for (int j = 0; j < currentState.get(0).size(); j++) {
                List<String> newLevel = new LinkedList<>();
                for (int k = 0; k < currentState.get(0).get(0).size(); k++) {
                    StringBuilder newLine = new StringBuilder();
                    for (int l = 0; l < currentState.get(0).get(0).get(0).length(); l++) {
                        char currentPos = currentState.get(i).get(j).get(k).charAt(l);
                        int activeNeighbours = count4DNeighbours(currentState, i, j, k, l);
                        if (currentPos == '#' && (activeNeighbours == 2 || activeNeighbours == 3)) {
                            newLine.append('#');
                        } else if (currentPos == '.' && activeNeighbours == 3) {
                            newLine.append('#');
                        } else {
                            newLine.append('.');
                        }
                    }
                    newLevel.add(newLine.toString());
                }
                newSphare.add(newLevel);
            }
            newState.add(newSphare);
        }
        return newState;
    }

    private void adjustSize(List<List<String>> pocketDim) {
        for (List<String> strings : pocketDim) {
            for (int j = 0; j < pocketDim.get(0).size(); j++) {
                String newLine = "." + strings.get(j) + ".";
                strings.remove(j);
                strings.add(j, newLine);
            }
        }

        int lineLength = pocketDim.get(0).get(0).length();
        pocketDim.forEach(strings -> {
            strings.add(0, getEmptyLine(lineLength));
            strings.add(getEmptyLine(lineLength));
        });

        pocketDim.add(0, getEmptyLevel(pocketDim.get(0).size(), lineLength));
        pocketDim.add(getEmptyLevel(pocketDim.get(0).size(), lineLength));
    }

    private void adjust4DSize(List<List<List<String>>> pocketDim) {
        pocketDim.forEach(this::adjustSize);
        pocketDim.add(0, getEmptySphare(pocketDim.get(0).size(), pocketDim.get(0).get(0).size(), pocketDim.get(0).get(0).get(0).length()));
        pocketDim.add(getEmptySphare(pocketDim.get(0).size(), pocketDim.get(0).get(0).size(), pocketDim.get(0).get(0).get(0).length()));
    }

    private List<List<String>> getEmptySphare(int width, int height, int depth) {
        List<List<String>> new3D = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            new3D.add(getEmptyLevel(height, depth));
        }
        return new3D;
    }

    private List<String> getEmptyLevel(int width, int height) {
        List<String> newLevel = new LinkedList<>();
        for (int j = 0; j < width; j++) {
            newLevel.add(getEmptyLine(height));
        }
        return newLevel;
    }

    private String getEmptyLine(int size) {
        return ".".repeat(Math.max(0, size));
    }

    public Object part1(List<String> input) {
        List<List<String>> pocketDim = new LinkedList<>();
        pocketDim.add(input);
        for (int i = 0; i < 6; i++) {
            adjustSize(pocketDim);
            pocketDim = getNextState(pocketDim);
        }
        return countActive(pocketDim);
    }

    public Object part2(List<String> input) {
        List<List<List<String>>> pocketDim = new LinkedList<>();
        List<List<String>> initSpahre = new LinkedList<>();
        initSpahre.add(input);
        pocketDim.add(initSpahre);
        for (int i = 0; i < 6; i++) {
            adjust4DSize(pocketDim);
            pocketDim = getNext4DState(pocketDim);
        }
        int sum = 0;
        for (List<List<String>> lists : pocketDim) {
            sum += countActive(lists);
        }
        return sum;
    }

}
