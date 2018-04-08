package com.snippets;

import java.util.List;

public class PaginationHelper<T> {
    private List<T> items;
    private int itemsPerPage;

    public PaginationHelper(List<T> items, Integer itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * Returns the number of items within the entire collection.
     *
     * @return the number of items within the entire collection.
     */
    public int itemCount() {
        return items.size();
    }

    /**
     * Returns the number of pages.
     *
     * @return the number of pages.
     */
    public int pageCount() {
        return (itemCount() - 1) / itemsPerPage + 1;
    }

    /**
     * Determines number of items on the current page.
     *
     * @param pageIndex index of the page.
     * @return number of items on the page or -1 for pageIndex values that are out of range.
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex > pageCount() - 1) {
            return -1;
        }
        if (pageIndex == pageCount() - 1) {
            int diff = itemCount() % itemsPerPage;
            return diff == 0 ? itemsPerPage : diff;
        }
        return itemsPerPage;
    }

    /**
     * Determines what page an item is on.
     *
     * @param itemIndex index of the item.
     * @return page index or -1 for itemIndex values that are out of range.
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex > itemCount() - 1) {
            return -1;
        }
        return (itemIndex - 1) / itemsPerPage;
    }
}
