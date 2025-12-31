package com.example.codeevaluator.util;

public class OutputEvaluator {

    public static boolean isOutputMatching(String actual, String expected) {
        if (actual == null || expected == null) {
            return false;
        }
        return normalize(actual).equals(normalize(expected));
    }

    private static String normalize(String s) {
        return s.trim().replaceAll("\\s+", " ");
    }
}
