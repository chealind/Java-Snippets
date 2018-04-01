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
        lib.toExpandedForm(number) == result

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
}
