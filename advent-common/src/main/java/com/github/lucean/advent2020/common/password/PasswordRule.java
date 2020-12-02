package com.github.lucean.advent2020.common.password;

@FunctionalInterface
public interface PasswordRule {

    boolean test(final String password);

    static String parseCharacter(final String passwordRule) {
        return passwordRule.split("\s")[1];
    }

    static int parseFirstConstraint(final String passwordRule) {
        return Integer.parseInt(passwordRule.split("\s")[0].split("-")[0]);
    }

    static int parseSecondConstraint(final String passwordRule) {
        return Integer.parseInt(passwordRule.split("\s")[0].split("-")[0]);
    }
}
