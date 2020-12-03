package com.github.lucean.advent2020.solutions;

import com.github.lucean.advent2020.common.io.FileUtils;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Log4j2
public class Day3 {

    public static void main(final String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        var input = Try.of(() -> FileUtils.fileAsList("day3input.txt"))
                       .getOrElse(Collections.emptyList());

        var arrWidth = input.get(0)
                            .length();

        var arr = input.stream()
                       .map(String::toCharArray)
                       .collect(toList())
                       .toArray(new char[][]{});

        log.info(traverse(3, 1, arr, arrWidth));
    }

    private static void part2() {
        var input = Try.of(() -> FileUtils.fileAsList("day3input.txt"))
                       .getOrElse(Collections.emptyList());

        var arrWidth = input.get(0)
                            .length();

        var arr = input.stream()
                       .map(String::toCharArray)
                       .collect(toList())
                       .toArray(new char[][]{});

        Stream.of(new Tuple2<>(1, 1), new Tuple2<>(3, 1), new Tuple2<>(5, 1), new Tuple2<>(7, 1), new Tuple2<>(1, 2))
              .map(t -> traverse(t._1, t._2, arr, arrWidth))
              .reduce((a, b) -> a * b)
              .ifPresentOrElse(log::info, () -> log.error("No solution found"));
    }

    public static long traverse(final int right, final int down, final char[][] input, final int inputWidth) {
        var treeCount = 0L;
        var j = 0;
        for (var i = 0; i <= input.length - 1; i += down) {

            if (isTree(input[i][j])) {
                treeCount += 1;
            }

            j = (j + right) % inputWidth;
        }

        return treeCount;
    }

    private static boolean isTree(final String ch) {
        return ch.equals("#");
    }

    private static boolean isTree(final char ch) {
        return isTree(Character.toString(ch));
    }
}
