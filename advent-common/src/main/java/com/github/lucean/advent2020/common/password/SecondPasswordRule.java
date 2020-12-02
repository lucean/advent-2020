package com.github.lucean.advent2020.common.password;

import com.github.lucean.advent2020.common.collections.FreqMap;

import java.util.stream.IntStream;

import static com.github.lucean.advent2020.common.password.PasswordRule.parseCharacter;
import static com.github.lucean.advent2020.common.password.PasswordRule.parseFirstConstraint;
import static com.github.lucean.advent2020.common.password.PasswordRule.parseSecondConstraint;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class SecondPasswordRule implements PasswordRule {

    private final String character;
    private final int minUsages;
    private final int maxUsages;

    public SecondPasswordRule(final String character, final int minUsages, final int maxUsages) {
        this.character = character;
        this.minUsages = minUsages;
        this.maxUsages = maxUsages;
    }

    @Override
    public boolean test(String password) {
        var characterMap = IntStream.range(0, password.length())
                                    .boxed()
                                    .collect(toMap(identity(), i-> String.valueOf(password.charAt(i))));

        var first = characterMap.getOrDefault(minUsages, "");
        var second = characterMap.getOrDefault(maxUsages, "");

        if (first.equals(character) && second.equals(character)) {
            return false;
        } else if (!first.equals(character) && !second.equals(character)) {
            return false;
        } else {
            return true;
        }
    }

    public static PasswordRule parse(final String passwordRule) {
        return new SecondPasswordRule(parseCharacter(passwordRule), parseFirstConstraint(passwordRule), parseSecondConstraint(passwordRule));
    }
}
