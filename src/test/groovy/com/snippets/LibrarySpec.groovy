package com.snippets

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Test specification for java code.
 *
 * @author Bohdan Bachkala on 27-Mar-18.
 */
class LibrarySpec extends Specification {
    def lib = [] as Library

    @Unroll
    def "should return middle character"() {
        expect:
        lib.middleCharacter(input) == result

        where:
        input         || result
        ""            || ""
        "a"           || "a"
        "sun"         || "u"
        "lake"        || "ak"
        "sorrow"      || "rr"
        "frustration" || "r"
    }

    @Unroll
    def "should decode number from binary"() {
        expect:
        lib.binaryToInt(input) == result

        where:
        input                 || result
        [0, 0, 0, 1] as int[] || 1
        [0, 0, 1, 0] as int[] || 2
        [0, 1, 0, 1] as int[] || 5
        [0, 1, 1, 0] as int[] || 6
        [1, 0, 0, 1] as int[] || 9
        [1, 0, 1, 1] as int[] || 11
        [1, 1, 1, 1] as int[] || 15
    }

    @Unroll
    def "should perform binary search"() {
        expect:
        lib.binarySearch(array, value) == result

        where:
        array                                     | value || result
        [10, 11, 12, 16, 18, 23, 29, 33] as int[] | 12    || 2
        [10, 11, 12, 16, 18, 23, 29, 33] as int[] | 54    || -1
    }

    @Unroll
    def "should compute Collatz Conjecture"() {
        expect:
        lib.conjecture(number) == result

        where:
        number || result
        20     || 8
        31     || 107
        17     || 13
        170    || 11
    }

    @Unroll
    def "should abbreviate string"() {
        expect:
        lib.abbreviate(s) == result

        where:
        s                                || result
        "internationalization"           || "i18n"
        "elephant-rides are really fun!" || "e6t-r3s are r4y fun!"
    }

    @Unroll
    def "should convert number to expanded form"() {
        expect:
        lib.expanded(number) == result

        where:
        number || result
        21     || "20 + 1"
        145    || "100 + 40 + 5"
        985041 || "900000 + 80000 + 5000 + 40 + 1"
    }

    @Unroll
    def "should split and add array"() {
        expect:
        lib.splitAndAdd(array, n) == result

        where:
        array                                                                  | n  || result
        [1, 2, 3, 4, 5] as int[]                                               | 2  || [5, 10] as int[]
        [1, 2, 3, 4, 5] as int[]                                               | 3  || [15] as int[]
        [32, 45, 43, 23, 54, 23, 54, 34] as int[]                              | 2  || [183, 125] as int[]
        [3, 234, 25, 345, 45, 34, 234, 235, 345] as int[]                      | 3  || [305, 1195] as int[]
        [23, 345, 345, 345, 34536, 567, 568, 6, 34536, 54, 7546, 456] as int[] | 20 || [79327] as int[]
    }

    @Unroll
    def "should compute number depth"() {
        expect:
        lib.depth(number) == result

        where:
        number || result
        42     || 9
        1      || 10
        31     || 10
        17     || 7
        170    || 7
    }

    @Unroll
    def "should compute best sub set sum"() {
        expect:
        lib.bestTotal(limit, k, list) == result

        where:
        limit | k | list                         || result
        174   | 3 | [50, 55, 57, 58, 60]         || 173
        163   | 3 | [50]                         || -1
        230   | 3 | [91, 74, 73, 85, 73, 81, 87] || 228
    }

    @Unroll
    def "should compute fibonacci nubmer's product"() {
        expect:
        lib.productFib(n) == result

        where:
        n    || result
        4895 || [55, 89, 1] as long[]
        5895 || [89, 144, 0] as long[]
    }

    @Unroll
    def "should search for gap in primes"() {
        expect:
        lib.primeGaps(gap, start, end) == result

        where:
        gap | start | end || result
        2   | 100   | 110 || [101, 103] as long[]
        4   | 100   | 110 || [103, 107] as long[]
        6   | 100   | 110 || null
        8   | 300   | 400 || [359, 367] as long[]
    }

    @Unroll
    def "should convert string to camel case"() {
        expect:
        lib.toCamelCase(string, delimeter) == result

        where:
        string                | delimeter || result
        "the_Stealth_Warrior" | "_"       || "theStealthWarrior"
        "The-Stealth-Warrior" | "-"       || "TheStealthWarrior"
    }

    @Unroll
    def "should reverse words order"() {
        expect:
        lib.reverseWords(string, delimeter) == result

        where:
        string              | delimeter || result
        "I like eating"     | " "       || "eating like I"
        "The-world-is-nice" | "-"       || "nice-is-world-The"
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    @Unroll
    def "should scramble strings"() {
        expect:
        lib.scramble(s1, s2) == result

        where:
        s1                | s2           || result
        "scriptingjava"   | "javascript" || true
        "aabbcamaomsccdd" | "commas"     || true
        "rwer3"           | "severe"     || false
        "scriptjavx"      | "javascript" || false
        ""                | ""           || true
    }

    @Unroll
    def "should create spiral array"() {
        expect:
        lib.spiral(n) == result

        where:
        n || result
        3 || [[1, 2, 3], [8, 9, 4], [7, 6, 5]] as int[][]
        4 || [[1, 2, 3, 4], [12, 13, 14, 5], [11, 16, 15, 6], [10, 9, 8, 7]] as int[][]
        5 || [[1, 2, 3, 4, 5], [16, 17, 18, 19, 6], [15, 24, 25, 20, 7], [14, 23, 22, 21, 8], [13, 12, 11, 10, 9]] as int[][]
    }

    @Unroll
    def "should format time from seconds"() {
        expect:
        lib.readableTime(seconds) == result

        where:
        seconds || result
        0       || "00:00:00"
        5       || "00:00:05"
        13      || "00:00:13"
        60      || "00:01:00"
        80      || "00:01:20"
        86399   || "23:59:59"
        359999  || "99:59:59"
    }

    @SuppressWarnings("GroovyPointlessBoolean")
    @Unroll
    def "should validate brace order"() {
        expect:
        lib.validBraces(braces) == result

        where:
        braces     || result
        "(){}[]"   || true
        "([{}])"   || true
        "(}"       || false
        "[(])"     || false
        "[({})](]" || false
    }

    @Unroll
    def "should convert to roman style"() {
        expect:
        lib.romanFormat(number) == result

        where:
        number || result
        1      || "I"
        4      || "IV"
        6      || "VI"
        1000   || "M"
        1990   || "MCMXC"
        1666   || "MDCLXVI"
        2008   || "MMVIII"
    }

    @Unroll
    def "should sort elements of the array (snail)"() {
        expect:
        lib.snail(array) == result

        where:
        array                                        || result
        [[]] as int[][]                              || [] as int[]
        [[1, 2, 3], [8, 9, 4], [7, 6, 5]] as int[][] || [1, 2, 3, 4, 5, 6, 7, 8, 9] as int[]
        [[1, 2, 3], [4, 5, 6], [7, 8, 9]] as int[][] || [1, 2, 3, 6, 9, 8, 7, 4, 5] as int[]
    }

    @Unroll
    def "should evaluate reverse polish notation"() {
        expect:
        lib.evaluatePolish(expression) == result

        where:
        expression                       || result
        ""                               || 0
        "1 2 3"                          || 3
        "1 2 3.5"                        || 3.5
        "1 3 +"                          || 4
        "4 5 *"                          || 20
        "7 6 -"                          || 1
        "4 2 /"                          || 2
        "5 1 2 + 4 * + 3 -"              || 14
        "15 7 1 1 + - / 3 * 2 1 1 + + -" || 5
    }

    @Unroll
    def "should transform seconds in readable form"() {
        expect:
        lib.formatDuration(time) == result

        where:
        time      || result
        1         || "1 second"
        62        || "1 minute and 2 seconds"
        120       || "2 minutes"
        3600      || "1 hour"
        3662      || "1 hour, 1 minute and 2 seconds"
        33243586  || "1 year, 19 days, 18 hours, 19 minutes and 46 seconds"
        205851834 || "6 years, 192 days, 13 hours, 3 minutes and 54 seconds"
        15731080  || "182 days, 1 hour, 44 minutes and 40 seconds"
        0         || "now"
    }

    @Unroll
    def "should compute divided total"() {
        expect:
        lib.primeFactorTotal(array) == result

        where:
        array                                                       || result
        [] as int[]                                                 || ""
        [12, 15] as int[]                                           || "(2 12)(3 27)(5 15)"
        [15, 21, 24, 30, 45] as int[]                               || "(2 54)(3 135)(5 90)(7 21)"
        [107, 158, 204, 100, 118, 123, 126, 110, 116, 100] as int[] || "(2 1032)(3 453)(5 310)(7 126)(11 110)(17 204)(29 116)(41 123)(59 118)(79 158)(107 107)"
    }

    @Unroll
    def "should reduce to single color"() {
        expect:
        lib.triangle(row) == result

        where:
        row                   || result
        "B"                   || 'B' as char
        "GB"                  || 'R' as char
        "RRR"                 || 'R' as char
        "RGBG"                || 'B' as char
        "RBRGBRB"             || 'G' as char
        "RBRGBRBGGRRRBGBBBGG" || 'G' as char
    }

    @Unroll
    def "should sort numbers by weight"() {
        expect:
        lib.orderWeight(row) == result

        where:
        row                                                                                 || result
        "103 123 4444 99 2000"                                                              || "2000 103 123 4444 99"
        "2000 10003 1234000 44444444 9999 11 11 22 123"                                     || "11 11 2000 10003 22 123 1234000 44444444 9999"
        "71899703 200 6 91 425 4 67407 7 96488 6 4 2 7 31064 9 7920 1 34608557 27 72 18 81" || "1 2 200 4 4 6 6 7 7 18 27 72 81 9 91 425 31064 7920 67407 96488 34608557 71899703"
    }

    @Unroll
    def "should calculate squared integer list"() {
        expect:
        lib.listSquared(start, end) == result

        where:
        start | end   || result
        1     | 250   || "[[1, 1], [42, 2500], [246, 84100]]"
        42    | 250   || "[[42, 2500], [246, 84100]]"
        1800  | 2000  || "[[1880, 4884100]]"
        2000  | 2000  || "[]"
        5000  | 10000 || "[[6237, 45024100], [9799, 96079204], [9855, 113635600]]"
    }

    @Unroll
    def "should factorize positive number"() {
        expect:
        lib.factor(number) == result

        where:
        number            || result
        13                || "13 - 1"
        24                || "2 - 3, 3 - 1"
        32767             || "7 - 1, 151 - 1, 31 - 1"
        72057554846356487 || "72057554846356487 - 1"
    }

    @Unroll
    def "should compute multiplicative persistence for a number"() {
        expect:
        lib.persistence(number) == result

        where:
        number || result
        39     || 3
        4      || 0
        25     || 2
        999    || 4
        444    || 3
    }

    @Unroll
    def "should compute next queue element"() {
        expect:
        lib.nextQueue(arr, n) == result

        where:
        arr                                                             | n        || result
        ["Sheldon", "Leonard", "Penny", "Rajesh", "Howard"] as String[] | 1        || "Sheldon"
        ["Sheldon", "Leonard", "Penny", "Rajesh", "Howard"] as String[] | 6        || "Sheldon"
        ["Sheldon", "Leonard", "Penny", "Rajesh", "Howard"] as String[] | 1802     || "Penny"
        ["Sheldon", "Leonard", "Penny", "Rajesh", "Howard"] as String[] | 534      || "Rajesh"
        ["Sheldon", "Leonard", "Penny", "Rajesh", "Howard"] as String[] | 28643950 || "Leonard"
    }
}