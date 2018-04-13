package com.snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;
import static java.lang.String.valueOf;

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
        return N % 2 == 0 ? s.substring(N / 2 - 1, N / 2 + 1) : valueOf(s.charAt(N / 2));
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
            String abbreviation = word.substring(0, 1) + valueOf(N - 2) + word.substring(N - 1, N);
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
    public String expanded(int n) {
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
    public int depth(int n) {
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
    public int bestTotal(int limit, int k, List<Integer> list) {
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

    /**
     * Compute product of consecutive fibonacci numbers.
     * Reference: https://www.codewars.com/kata/product-of-consecutive-fib-numbers
     *
     * @param n number's product upper bound.
     * @return pair of consecutive fibonacci numbers and a flag which
     * is {@literal 1} if upper bound was matched and {@literal 0} otherwise.
     */
    public long[] productFib(long n) {
        long current = 0L;
        long next = 1L;
        long temp;
        while (current * next < n) {
            temp = current;
            current = next;
            next = temp + next;
        }
        return new long[]{current, next, current * next == n ? 1 : 0};
    }

    /**
     * Searches for gap in prime numbers.
     *
     * @param gap   length between two prime numbers.
     * @param start start point of search.
     * @param end   end point of search.
     * @return two prime numbers if gap was found, {@literal null} otherwise.
     */
    public long[] primeGaps(long gap, long start, int end) {
        long prev = Long.MIN_VALUE;
        for (long k = start; k < end; k++) {
            if (isPrime(k)) {
                if (k - prev == gap) {
                    return new long[]{prev, k};
                }
                prev = k;
            }
        }
        return null;
    }

    public boolean isPrime(long n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * Convert string to camelCase.
     * Reference: https://www.codewars.com/kata/convert-string-to-camel-case
     *
     * @param s string to convert.
     * @param d word delimiter.
     * @return string in camelCase.
     */
    public String toCamelCase(String s, String d) {
        String[] words = s.split(d);
        return Arrays.stream(words, 1, words.length)
                .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
                .reduce(words[0], String::concat);
    }

    /**
     * Reverse words in a given string.
     * Reference: https://www.codewars.com/kata/reversed-words
     *
     * @param s words separated by delimiter.
     * @param d word delimiter.
     * @return reverse order of words.
     */
    public String reverseWords(String s, String d) {
        return Arrays.stream(s.split(d))
                .reduce((a, b) -> b + d + a)
                .get();
    }

    /**
     * Returns true if a portion of s1 characters can be rearranged to match s2, otherwise returns false.
     * Reference: https://www.codewars.com/kata/scramblies
     *
     * @param s1 first string of characters.
     * @param s2 second string of characters.
     * @return true if a portion of s1 characters can be rearranged to match s2, otherwise returns false.
     */
    public boolean scramble(String s1, String s2) {
        if (s1.length() < s2.length()) return false;
        if (s1.equals(s2)) return true;
        Map<String, Integer> letterIndex = new HashMap<>();

        for (String s : s2.split("")) {
            if (letterIndex.containsKey(s)) letterIndex.put(s, letterIndex.get(s) + 1);
            else letterIndex.put(s, 1);
        }

        for (String s : s1.split("")) {
            if (letterIndex.containsKey(s)) {
                if (letterIndex.get(s) == 1) letterIndex.remove(s);
                else letterIndex.put(s, letterIndex.get(s) - 1);
            }
        }
        return letterIndex.size() == 0;
    }

    /**
     * Returns spiral array 1...n2
     * Reference: https://www.codewars.com/kata/the-clockwise-spiral
     * <p>
     * Example:
     * N = 3
     * Output: [[1,2,3],[8,9,4],[7,6,5]]
     * 1 2 3
     * 8 9 4
     * 7 6 5
     *
     * @param n array bound.
     * @return spiral array.
     */
    public int[][] spiral(int n) {
        int[][] arr = new int[n][n];
        int v = 1, minRow = 0, minCol = 0, maxRow = n - 1, maxCol = n - 1;

        while (v <= n * n) {
            // complete top row
            for (int k = minCol; k <= maxCol; k++) {
                arr[minRow][k] = v++;
            }
            // complete top col
            for (int k = minRow + 1; k <= maxRow; k++) {
                arr[k][maxCol] = v++;
            }
            // complete bottom row
            for (int k = maxCol - 1; k >= minCol; k--) {
                arr[maxRow][k] = v++;
            }
            // complete spiral
            for (int k = maxRow - 1; k >= minRow + 1; k--) {
                arr[k][minCol] = v++;
            }
            minCol++;
            minRow++;
            maxCol--;
            maxRow--;
        }
        return arr;
    }

    /**
     * Format time in seconds in readable format.
     * Reference: https://www.codewars.com/kata/human-readable-time
     *
     * @param seconds time in seconds.
     * @return time in readable format.
     */
    public String readableTime(int seconds) {
        int hour = seconds / 3600;
        int min = (seconds / 60) % 60;
        int sec = seconds % 60;
        return format("%02d:%02d:%02d", hour, min, sec);
    }

    /**
     * Method takes a string of braces, and determines if the order of the braces is valid.
     *
     * @param braces string containing braces such as [], {}, ()
     * @return true if order of braces is valid, false otherwise.
     */
    public boolean validBraces(String braces) {
        final Map<String, String> braceIndex = new HashMap<>();
        braceIndex.put("(", ")");
        braceIndex.put("[", "]");
        braceIndex.put("{", "}");

        final Deque<String> braceStack = new LinkedList<>();
        for (String brace : braces.split("")) {
            if (braceIndex.containsKey(brace)) {
                braceStack.push(braceIndex.get(brace));
            } else if (braceStack.isEmpty() || !braceStack.pop().equals(brace)) {
                return false;
            }
        }
        return braceStack.isEmpty();
    }

    /**
     * Convert int number to roman format.
     * For example: 4 => IV, 9 => IX.
     * Reference: https://www.codewars.com/kata/roman-numerals-encoder
     *
     * @param number integer number.
     * @return number in roman format.
     */
    public String romanFormat(int number) {
        Map<Integer, String> romanIndex = new TreeMap<>(Collections.reverseOrder());
        romanIndex.put(1000, "M");
        romanIndex.put(900, "CM");
        romanIndex.put(500, "D");
        romanIndex.put(400, "CD");
        romanIndex.put(100, "C");
        romanIndex.put(90, "XC");
        romanIndex.put(50, "L");
        romanIndex.put(40, "XL");
        romanIndex.put(10, "X");
        romanIndex.put(9, "IX");
        romanIndex.put(5, "V");
        romanIndex.put(4, "IV");
        romanIndex.put(1, "I");

        StringBuilder sb = new StringBuilder();
        for (int n : romanIndex.keySet()) {
            while (number >= n) {
                sb.append(romanIndex.get(n));
                number -= n;
            }
        }
        return sb.toString();
    }

    /**
     * Given an two dimensional array, returns the array with elements arranged from
     * outermost elements to the middle element, traveling clockwise.
     * Reference: https://www.codewars.com/kata/snail
     *
     * @param array a two dimensional array.
     * @return a one dimensional array with sorted elements.
     */
    public int[] snail(int[][] array) {
        if (array[0].length == 0) return new int[0];

        int N = array.length;
        int[] snail = new int[N * N];
        int minCol = 0, minRow = 0, maxCol = N - 1, maxRow = N - 1;
        int index = 0;

        while (index < N * N) {
            for (int i = minCol; i <= maxCol; i++) {
                snail[index++] = array[minRow][i];
            }
            for (int i = minRow + 1; i <= maxRow; i++) {
                snail[index++] = array[i][maxCol];
            }
            for (int i = maxCol - 1; i >= minCol; i--) {
                snail[index++] = array[maxRow][i];
            }
            for (int i = maxRow - 1; i >= minRow + 1; i--) {
                snail[index++] = array[i][minCol];
            }
            minCol++;
            maxCol--;
            minRow++;
            maxRow--;
        }
        return snail;
    }

    /**
     * Evaluate reverse polish notation expression.
     * Reference: https://www.codewars.com/kata/reverse-polish-notation-calculator
     *
     * @param expression operators and operands in reverse polish notation.
     * @return evaluated expression.
     */
    public double evaluatePolish(String expression) {
        if (expression.isEmpty()) return 0;
        Deque<Double> stack = new LinkedList<>();

        Arrays.stream(expression.split(" ")).forEach(s -> {
            Double b, a;
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a - b);
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a / b);
                    break;
                default:
                    stack.push(Double.parseDouble(s));
            }
        });
        return stack.pop();
    }

    /**
     * Transform duration in seconds to readable format.
     * Reference: https://www.codewars.com/kata/human-readable-time
     *
     * @param time duration in seconds.
     * @return duration in readable format.
     */
    public String formatDuration(int time) {
        if (time == 0) return "now";
        StringBuilder result = new StringBuilder();

        int year = time / 3600 / 24 / 365;
        int day = time / 3600 / 24 % 365;
        int hour = time / 3600 % 24;
        int min = time / 60 % 60;
        int sec = time % 60;

        if (year > 0) {
            result.append(format("%d %s", year, year == 1 ? "year" : "years"));
        }
        if (day > 0) {
            if (result.length() > 0) result.append(hour > 0 ? ", " : " and ");
            result.append(format("%d %s", day, day == 1 ? "day" : "days"));
        }
        if (hour > 0) {
            if (result.length() > 0) result.append(min > 0 ? ", " : " and ");
            result.append(format("%d %s", hour, hour == 1 ? "hour" : "hours"));
        }
        if (min > 0) {
            if (result.length() > 0) result.append(sec > 0 ? ", " : " and ");
            result.append(format("%d %s", min, min == 1 ? "minute" : "minutes"));
        }
        if (sec > 0) {
            if (result.length() > 0) result.append(" and ");
            result.append(format("%d %s", sec, sec == 1 ? "second" : "seconds"));
        }
        return result.toString();
    }

    /**
     * Compute total for numbers by prime factors.
     * Reference: https://www.codewars.com/kata/54d496788776e49e6b00052f
     *
     * @param array array of numbers.
     * @return totals by prime factors.
     */
    public String primeFactorTotal(int[] array) {
        StringBuilder sb = new StringBuilder();
        Set<Integer> primes = new TreeSet<>();
        for (int n : array) {
            primes.addAll(primeFactors(n));
        }
        for (int p : primes) {
            int sum = 0;
            for (int n : array) {
                if (n % p == 0) sum += n;
            }
            sb.append(format("(%d %d)", p, sum));
        }
        return sb.toString();
    }

    private Set<Integer> primeFactors(int number) {
        Set<Integer> primeFactors = new HashSet<>();
        int n = number;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                primeFactors.add(i);
                n /= i;
            }
        }
        return primeFactors;
    }
}
