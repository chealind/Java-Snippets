package com.snippets;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    /**
     * Convert given number to it's expanded representation.
     * Reference: https://www.codewars.com/kata/write-number-in-expanded-form
     *
     * @param n natural number.
     * @return number in expanded form.
     */
    public String toExpandedForm(int n) {
        int e = 1;
        StringBuilder result = new StringBuilder();

        while (n != 0) {
            int k = n % 10;
            if (k != 0) result.insert(0, " + " + k * e);
            e *= 10;
            n /= 10;
        }
        return result.substring(3);
    }

    /**
     * Split and add both sides of an array together.
     * Reference: https://www.codewars.com/kata/split-and-then-add-both-sides-of-an-array-together
     *
     * @param numbers arrays of integers.
     * @param n       number of splits.
     * @return reduced array.
     */
    public int[] splitAndAdd(int[] numbers, int n) {
        List<Integer> list = new ArrayList<>();
        for (int entry : numbers) {
            list.add(entry);
        }

        while (n > 0) {
            int loop = list.size() / 2;
            int offset = list.size() - loop - 1;
            for (int i = 0; i < loop; i++) {
                int elem = list.remove(0);
                list.set(offset, list.get(offset) + elem);
            }
            --n;
        }

        return list.stream()
                .mapToInt(i -> i)
                .toArray();
    }

    /**
     * Compute depth for the given number.
     *
     * @param n natural number greater then zero.
     * @return depth for the given number.
     */
    public int computeDepth(int n) {
        Set<Integer> digits = new HashSet<>();
        int depth = 0;
        for (int i = 1; digits.size() < 10; i++, depth++) {
            int k = n * i;
            while (k != 0) {
                digits.add(k % 10);
                k /= 10;
            }
        }
        return depth;
    }


    /**
     * Compute best sum of {@code k} subset for the given {@code list}.
     * Reference: https://www.codewars.com/kata/55e7280b40e1c4a06d0000aa
     *
     * @param limit maximum sum.
     * @param k     length of a sub set.
     * @param list  set of numbers.
     * @return biggest sum of k integers up to the limit.
     */
    public int bestSum(int limit, int k, List<Integer> list) {
        int[] dist = list.stream().mapToInt(d -> d).toArray();
        int[] subset = new int[k];
        List<Integer> totals = new ArrayList<>();

        computeTotals(dist, 0, subset, 0, k, totals, limit);

        return totals.stream().max(Integer::compareTo).orElse(-1);
    }

    public void computeTotals(int[] dist, int i, int[] subset, int j, int k, List<Integer> totals, int limit) {
        if (j == k) { // subset is full
            int subTotal = 0;
            for (int m = 0; m < k; m++) {
                subTotal += subset[m];
            }
            if (subTotal <= limit) {
                totals.add(subTotal);
            }
            return;
        }

        if (i >= dist.length) return;

        subset[j] = dist[i];
        computeTotals(dist, i + 1, subset, j + 1, k, totals, limit);

        computeTotals(dist, i + 1, subset, j, k, totals, limit);
    }
}
