package com.snippets;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Decode a binary representation of a number.
     * Reference: https://www.codewars.com/kata/ones-and-zeros
     *
     * @param a array of ones and zeros.
     * @return decoded number.
     */
    public int binaryToInt(int[] a) {
        int N = a.length;
        int result = 0;
        for (int i = N - 1, k = 0; i >= 0; i--, k++) {
            if (a[i] == 1) {
                result += (int) Math.pow(2, k);
            }
        }
        return result;
    }

    /**
     * Recursive implementation of the binary search algorithm.
     * Reference: https://www.geeksforgeeks.org/binary-search/
     *
     * @param array array of values.
     * @param value element to search for.
     * @return index of an element in the array, if there is no element {@literal -1}.
     */
    public int binarySearch(int[] array, int value) {
        return rank(array, value, 0, array.length - 1);
    }

    private int rank(int[] array, int value, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (value > array[mid]) {
            return rank(array, value, mid + 1, hi);
        } else if (value < array[mid]) {
            return rank(array, value, lo, mid - 1);
        } else {
            return mid;
        }
    }

    /**
     * Computes Collatz Conjecture.
     * Reference: https://en.wikipedia.org/wiki/Collatz_conjecture
     *
     * @param n natural number.
     * @return length of a Collatz Conjecture.
     */
    public long conjecture(long n) {
        long count = 1;
        while (n != 1) {
            n = (n % 2 == 0) ? n / 2 : n * 3 + 1;
            ++count;
        }
        return count;
    }

    /**
     * Abbreviates given string.
     * e.g. internationalization => i18n
     * Reference: https://www.codewars.com/kata/word-a10n-abbreviation
     *
     * @param s string to abbreviate.
     * @return abbreviated string.
     */
    public String abbreviate(String s) {
        Pattern p = Pattern.compile("[A-Za-z]{4,}");
        Matcher m = p.matcher(s);

        while (m.find()) {
            String word = m.group();
            int N = word.length();
            String abbreviation = word.substring(0, 1) + String.valueOf(N - 2) + word.substring(N - 1, N);
            s = s.replaceFirst(word, abbreviation);
        }
        return s;
    }
}
