# Java-day
Java code snippets

[![Build Status](https://travis-ci.com/aveOwl/java-day.svg?token=7qhENybGEB9cVAG6zsRP&branch=master)](https://travis-ci.com/aveOwl/java-day)

## Table of Contents

### String
* [Locate middle character](#locate-middle-character)
* [Abbreviate word](#abbreviate-word)

### Array
* [Binary to number](#binary-to-number)
* [Split and Add](#split-and-add)

### Algorithms
* [BinarySearch](#binarysearch)
* [Collatz Conjecture](#collatz-conjecture)
* [Number Expanded Form](#number-expanded-form)

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

[⬆ back to top](#table-of-contents)

## Algorithms

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

[⬆ back to top](#table-of-contents)