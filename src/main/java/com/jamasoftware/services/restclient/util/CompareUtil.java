package com.jamasoftware.services.restclient.util;

public class CompareUtil {
    // This will be modified further to accommodate other checks for misspelled words
    public static boolean closeEnough(String one, String two) {
        return one.equals(two)
                || one.toLowerCase().equals(two.toLowerCase())
                || one.toLowerCase().replaceAll(" ", "_").equals(two.toLowerCase().replaceAll(" ", "_"));

    }
}
