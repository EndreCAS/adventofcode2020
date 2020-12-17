package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.Arrays;
import java.util.List;

public class Day04 extends Day {

    private boolean validatePassport(String passport) {
        return passport.contains("byr") &&
               passport.contains("iyr") &&
               passport.contains("eyr") &&
               passport.contains("hgt") &&
               passport.contains("hcl") &&
               passport.contains("ecl") &&
               passport.contains("pid");
    }

    private boolean isNotBetween(String value, int min, int max) {
        try {
            int y = Integer.parseInt(value);
            return y < min || y > max;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean checkEcl(String ecl) {
        String[] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for (String col : validColors) {
            if (ecl.equals(col)) {
                return true;
            }
        }
        return false;
    }

//    byr (Birth Year) - four digits; at least 1920 and at most 2002.
//    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
//    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
//    hgt (Height) - a number followed by either cm or in:
//      - If cm, the number must be at least 150 and at most 193.
//      - If in, the number must be at least 59 and at most 76.
//    hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
//    ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
//    pid (Passport ID) - a nine-digit number, including leading zeroes.
//    cid (Country ID) - ignored, missing or not.
    private boolean validatePassportFields(String passport) {
        if (!validatePassport(passport)) {
            return false;
        }
        try {
            String[] fields = passport.split("\\s");
            for (String field : fields) {
                String[] kv = field.split(":");
                String key = kv[0];
                String value = kv[1];

                switch (key) {
                    case "byr":
                        if (isNotBetween(value, 1920, 2002))
                            return false;
                        break;
                    case "iyr":
                        if (isNotBetween(value, 2010, 2020))
                            return false;
                        break;
                    case "eyr":
                        if (isNotBetween(value, 2020, 2030))
                            return false;
                        break;
                    case "hgt":
                        String height = value.substring(0, value.length() - 2);
                        if (value.contains("in")) {
                            if (isNotBetween(height, 59, 76))
                                return false;
                        } else if (value.contains("cm")) {
                            if (isNotBetween(height, 150, 193))
                                return false;
                        } else {
                            return false;
                        }
                        break;
                    case "hcl":
                        if (!value.startsWith("#") || value.length() != 7) {
                            return false;
                        }
                        Integer.parseInt(value.substring(1), 16);
                        break;
                    case "ecl":
                        if (!checkEcl(value))
                            return false;
                        break;
                    case "pid":
                        if (value.length() != 9) {
                            return false;
                        }
                        Integer.parseInt(value);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private List<String> createPassports(List<String> input) {
        StringBuilder passport = new StringBuilder();
        StringBuilder allPassports = new StringBuilder();
        for (int i = 0; i < input.size(); i++) {
            String passportLine = input.get(i);
            if (passportLine.length() != 0) {
                if (passport.length()>0) {
                    passport.append(" ");
                }
                passport.append(passportLine);
            }
            if (passportLine.length() == 0 || (i == input.size()-1)) {
                if (allPassports.length()>0) {
                    allPassports.append(".");
                }
                allPassports.append(passport);
                passport = new StringBuilder();
            }
        }

        return Arrays.asList(allPassports.toString().split("\\."));
    }

    public Object part1(List<String> input) {
        List<String> passports = createPassports(input);
        int counter = 0;
        for(String passport: passports) {
            if (validatePassport(passport)) {
                counter++;
            }
        }
        return counter;
    }

    public Object part2(List<String> input) {
        List<String> passports = createPassports(input);
        int counter = 0;

        for (String s : passports) {
            if (validatePassportFields(s)) {
                counter++;
            }
        }
        return counter;
    }

}
