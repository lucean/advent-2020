package com.github.lucean.advent2020.common.streams;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtils {

    public static <T> Stream<T> fromIterable(final Iterable<T> iter) {
        return StreamSupport.stream(iter.spliterator(), false);
    }
}
