package com.github.lucean.advent2020.solutions;

import com.github.lucean.advent2020.common.collections.Permutations;
import com.github.lucean.advent2020.common.io.FileUtils;
import io.vavr.Tuple3;
import io.vavr.Tuple4;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;

import static java.util.stream.Collectors.toSet;

@Log4j2
public class Day1 {

    private static final int TARGET = 2020;

    public static void main(final String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        var input = Try.of(() -> FileUtils.fileAsList("day1input.txt", Integer.class, Integer::compareTo))
                       .getOrElse(Collections.emptyList());

        var candidates = input.stream()
                              .filter(i -> i < TARGET / 2)
                              .collect(toSet());

        input.stream()
             .flatMap(i -> candidates.stream()
                                     .map(j -> new Tuple3<>(i, j, i + j)))
             .filter(t -> t._3 == TARGET)
             .findFirst()
             .ifPresentOrElse(tuple -> log.info("Solution: " + tuple._1 * tuple._2), () -> log.error("No solution found"));
    }

    public static void part2() {
        var input = Try.of(() -> FileUtils.fileAsList("day1input.txt", Integer.class, Integer::compareTo))
                       .getOrElse(Collections.emptyList());

        Permutations.generateCombinations(input, 3)
                    .stream()
                    .map(c -> new Tuple4<>(c.get(0), c.get(1), c.get(2), c.get(0) + c.get(1) + c.get(2)))
                    .filter(t -> t._4 == TARGET)
                    .findFirst()
                    .ifPresentOrElse(tuple -> log.info("Solution: " + tuple._1 * tuple._2 * tuple._3), () -> log.error("No solution found"));
    }
}
