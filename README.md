# Java-day
Java code snippets

[![Build Status](https://travis-ci.com/aveOwl/java-day.svg?token=7qhENybGEB9cVAG6zsRP&branch=master)](https://travis-ci.com/aveOwl/java-day)

## Table of Contents

### String
* [Locate middle character](#locate-middle-character)

### Array
* [Binary to number](#binary-to-number)

### Algorithms
* [BinarySearch](#binarysearch)

## String

### Locate middle character

```java
    public String middleCharacter(String s) {
        int N = s.length();
        if (N < 2) return s;
        return N % 2 == 0 ? s.substring(N / 2 - 1, N / 2 + 1) : String.valueOf(s.charAt(N / 2));
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

[⬆ back to top](#table-of-contents)