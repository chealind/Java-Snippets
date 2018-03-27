package com.snippets

import spock.lang.Specification

/**
 * Test specification for java code.
 *
 * @author Bohdan Bachkala on 27-Mar-18.
 */
class LibrarySpec extends Specification {

    def "should return true"() {
        given:
        def lib = [] as Library

        when:
        def result = lib.method()

        then:
        result
    }
}
