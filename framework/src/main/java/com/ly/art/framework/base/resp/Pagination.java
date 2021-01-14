package com.ly.art.framework.base.resp;

import java.util.ArrayList;
import java.util.List;

public class Pagination<E> {

    private long count;

    private List<E> items = new ArrayList<E>();

    public Pagination() {}

    public Pagination(long count, List<E> items) {
        this.count = count;
        this.items = items;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<E> getItems() {
        return items;
    }

    public void setItems(List<E> items) {
        this.items = items;
    }

}
