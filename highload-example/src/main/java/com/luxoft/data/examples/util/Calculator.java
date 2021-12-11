package com.luxoft.data.examples.util;

public class Calculator {

    private static char[] possibleSymbols =
            {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.'};

    public static double checkNumber(String arg) {
        int verifiedLength = 0;
        for (int i = 0; i < arg.length(); i++) {
            char c = arg.charAt(i);
            for (char p : possibleSymbols) {
                if (c == p) {
                    verifiedLength++;
                }
            }
        }
        if (verifiedLength == arg.length()) {
            return Double.parseDouble(arg);
        }

        return 1000.0;
    }

    public static double checkNumber2(String arg) {
        int verifiedLength = 0;
        for (int i = 0; i < arg.length(); i++) {
            boolean found = false;
            char c = arg.charAt(i);
            for (char p : possibleSymbols) {
                if (c == p) {
                    verifiedLength++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return 1000.0;
            }

        }
        if (verifiedLength == arg.length()) {
            return Double.parseDouble(arg);
        }

        return 1000.0;
    }

    public static double checkNumber3(String arg) {
        double value;

        try {
            value = Double.parseDouble(arg);
        } catch (NumberFormatException e) {
            value = 1000;
        }

        return value;
    }

}
