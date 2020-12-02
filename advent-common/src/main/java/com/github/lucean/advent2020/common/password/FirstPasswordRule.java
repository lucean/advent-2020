package com.github.lucean.advent2020.common.password;

import com.github.lucean.advent2020.common.collections.FreqMap;

import static com.github.lucean.advent2020.common.password.PasswordRule.parseCharacter;
import static com.github.lucean.advent2020.common.password.PasswordRule.parseFirstConstraint;
import static com.github.lucean.advent2020.common.password.PasswordRule.parseSecondConstraint;

public class FirstPasswordRule implements PasswordRule {

    private final String character;
    private final int minUsages;
    private final int maxUsages;

    public FirstPasswordRule(final String character, final int minUsages, final int maxUsages) {
        this.character = character;
        this.minUsages = minUsages;
        this.maxUsages = maxUsages;
    }

    @Override
    public boolean test(String password) {
        var freqMap = FreqMap.of(password);

        var usages = freqMap.getOrDefault(character, 0L);

        return usages >= minUsages && usages <= maxUsages;
    }

    public static PasswordRule parse(final String passwordRule) {
        return new FirstPasswordRule(parseCharacter(passwordRule), parseFirstConstraint(passwordRule), parseSecondConstraint(passwordRule));
    }
}
