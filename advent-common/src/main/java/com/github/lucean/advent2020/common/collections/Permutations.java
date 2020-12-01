package com.github.lucean.advent2020.common.collections;

import lombok.experimental.UtilityClass;
import org.paukov.combinatorics3.Generator;

import java.util.List;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class Permutations {

    public static <T> List<List<T>> generateCombinations(final List<T> input, final int size) {
        return Generator.combination(input)
                        .simple(size)
                        .stream()
                        .collect(toList());
    }
}
