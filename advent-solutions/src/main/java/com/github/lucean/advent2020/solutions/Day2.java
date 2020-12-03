package com.github.lucean.advent2020.solutions;

import com.github.lucean.advent2020.common.io.FileUtils;
import com.github.lucean.advent2020.common.password.FirstPasswordRule;
import com.github.lucean.advent2020.common.password.PasswordRule;
import com.github.lucean.advent2020.common.password.SecondPasswordRule;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;

import java.util.Collections;

@Log4j2
public class Day2 {

    public static void main(final String[] args) {
        part1();
        part2();
    }

    public static void part1() {
        var input = Try.of(() -> FileUtils.fileAsList("day2input.txt"))
                       .getOrElse(Collections.emptyList());

        var output = input.parallelStream()
                          .map(line -> line.split(":"))
                          .map(arr -> new Tuple2<>(FirstPasswordRule.parse(arr[0]), arr[1]))
                          .filter(t -> t._1.test(t._2))
                          .count();

        log.info(output);
    }

    private static void part2() {
        var input = Try.of(() -> FileUtils.fileAsList("day2input.txt"))
                       .getOrElse(Collections.emptyList());

        var output = input.parallelStream()
                          .map(line -> line.split(":"))
                          .map(arr -> new Tuple2<>(SecondPasswordRule.parse(arr[0]), arr[1]))
                          .filter(t -> t._1.test(t._2))
                          .count();

        log.info(output);
    }
}
