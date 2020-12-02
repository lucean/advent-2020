package com.github.lucean.advent2020.common.collections;

import com.github.lucean.advent2020.common.streams.StreamUtils;

import java.util.HashMap;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class FreqMap<T> extends HashMap<T, Long> {

    public static <T> FreqMap<T> of(final Iterable<T> iter) {
        return StreamUtils.fromIterable(iter)
                          .collect(groupingBy(identity(), FreqMap::new, counting()));
    }

    public static FreqMap<String> of(final String str) {
        return str.chars()
                  .mapToObj(c -> (char) c)
                  .map(String::valueOf)
                  .collect(groupingBy(identity(), FreqMap::new, counting()));
    }
}
