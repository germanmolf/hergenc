package com.example.heroes.shared.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ListMother {

    //Para que no falle por java.lang.OutOfMemoryError: Java heap space y no tarde tanto
    private static final Integer LIST_MAX_SIZE = 10000;

    public static <T> List<T> create(Integer size, Supplier<T> creator) {
        size = Math.min(size, LIST_MAX_SIZE);
        ArrayList<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(creator.get());
        }
        return list;
    }

    public static <T> List<T> random(Supplier<T> creator) {
        return create(IntegerMother.randomPositive(), creator);
    }
}
