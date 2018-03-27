package com.snippets;

/**
 * Java snippets.
 *
 * @author Bohdan Bachkala on 27-Mar-18.
 */
public class Library {

    /**
     * Locate middle character(s) in given string.
     * Reference: https://www.codewars.com/kata/get-the-middle-character
     *
     * @param s input string.
     * @return middle character(s).
     */
    public String middleCharacter(String s) {
        int N = s.length();
        if (N < 2) return s;
        return N % 2 == 0 ? s.substring(N / 2 - 1, N / 2 + 1) : String.valueOf(s.charAt(N / 2));
    }
}
