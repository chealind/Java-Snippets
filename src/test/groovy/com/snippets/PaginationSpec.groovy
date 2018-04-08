package com.snippets

import spock.lang.Specification
import spock.lang.Unroll

class PaginationSpec extends Specification {

    @Unroll
    def "should calculate items per page"() {
        given:
        def paginationHelper = [items, itemsPerPage] as PaginationHelper

        expect:
        paginationHelper.pageItemCount(pageIndex) == result

        where:
        items                          | itemsPerPage | pageIndex || result
        ["a", "b", "c", "d", "e", "f"] | 4            | 1         || 2
        [7, 9, 11]                     | 1            | 0         || 1
        [7, 9, 11, 5, 21]              | 3            | 2         || -1
        [7, 9]                         | 2            | 0         || 2
    }

    @Unroll
    def "should calculate page for item"() {
        given:
        def paginationHelper = [items, itemsPerPage] as PaginationHelper

        expect:
        paginationHelper.pageIndex(itemIndex) == result

        where:
        items                          | itemsPerPage | itemIndex || result
        ["a", "b", "c", "d", "e", "f"] | 4            | 2         || 0
        ["a", "b", "c", "d", "e", "f"] | 4            | 5         || 1
        ["a", "b", "c", "d", "e", "f"] | 4            | 9         || -1
    }
}
