# Java-day
Java code snippets

[![Build Status](https://travis-ci.com/aveOwl/java-day.svg?token=7qhENybGEB9cVAG6zsRP&branch=master)](https://travis-ci.com/aveOwl/java-day)

## Table of Contents

### String
* [Locate middle character](#locate-middle-character)
* [Abbreviate word](#abbreviate-word)
* [Convert CamelCase](#convert-camelcase)
* [Reverse words](#reverse-words)
* [Scramble String](#scramble-string)

### Array
* [Binary to number](#binary-to-number)
* [Split and Add](#split-and-add)
* [Spiral Array](#spiral-array)

### Algorithms
* [BinarySearch](#binarysearch)
* [Collatz Conjecture](#collatz-conjecture)
* [Number Expanded Form](#number-expanded-form)
* [Compute Best Sum](#compute-best-sum)
* [Fibonacci Product](#fibonacci-product)
* [Valid Braces](#valid-braces)

### Number
* [Number Expanded Form](#number-expanded-form)
* [Integer Depth](#integer-depth)
* [Gap In Primes](#gap-in-primes)
* [Readable Time](#readable-time)

## String

### Locate middle character

```java
    public String middleCharacter(String s) {
        int N = s.length();
        if (N < 2) return s;
        return N % 2 == 0 ? s.substring(N / 2 - 1, N / 2 + 1) : String.valueOf(s.charAt(N / 2));
    }
```

### Abbreviate word

```java
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
```

### Convert CamelCase

```java
    public String toCamelCase(String s, String d) {
        String[] words = s.split(d);
        return Arrays.stream(words, 1, words.length)
                .map(w -> w.substring(0, 1).toUpperCase() + w.substring(1))
                .reduce(words[0], String::concat);
    }
```

### Reverse words

```java
    public String reverseWord(String s, String d) {
        return Arrays.stream(s.split(d))
                .reduce((a, b) -> b + d + a)
                .get();
    }
```

### Scramble String

```java
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
```

[⬆ back to top](#table-of-contents)

## Array

### Binary to number

```java
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
```

### Split and Add

```java
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
```

### Spiral Array

```java
    public int[][] getSpiralArray(int n) {
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
```

[⬆ back to top](#table-of-contents)

## Algorithm

### BinarySearch

```java
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
```

### Collatz Conjecture

```java
    public long conjecture(long n) {
        long count = 1;
        while (n != 1) {
            n = (n % 2 == 0) ? n / 2 : n * 3 + 1;
            ++count;
        }
        return count;
    }
```

### Compute Best Sum

```java
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
```

### Fibonacci Product

```java
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
```

### Valid Braces

```java
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
```

[⬆ back to top](#table-of-contents)

## Number

### Number Expanded Form

```java
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
```

### Integer Depth

```java
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
```

### Gap In Primes

```java
    public long[] gap(long gap, long start, int end) {
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
```

### Readable Time

```java
    public String readableTime(int seconds) {
        int hour = seconds / 3600;
        int min = (seconds / 60) % 60;
        int sec = seconds % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
```

[⬆ back to top](#table-of-contents)