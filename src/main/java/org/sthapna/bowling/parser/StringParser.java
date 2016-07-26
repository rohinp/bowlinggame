package org.sthapna.bowling.parser;

import org.sthapna.bowling.game.Symbols;

import java.util.Arrays;
import java.util.List;

public class StringParser {

    public final int HALF_OF_LINE = 10;
    private final List<String> tokens;

    public StringParser(List<String> tokens) {
        this.tokens = tokens;
    }

    public static StringParser parse(String input) {
        return new StringParser(Arrays.asList(input.split("")));
    }

    public List<String> tokens() {
        return tokens;
    }

    public boolean isAllStrike() {
        return tokens.stream().allMatch(e -> Symbols.STRIKE.val().equals(e));
    }

    public boolean isAllSpare() {
        return tokens.stream().filter(e -> Symbols.SPARE.val().equals(e)).count() == HALF_OF_LINE;
    }

    public boolean isAllPin() {
        return tokens.stream().filter(e -> Symbols.PIN.val().equals(e)).count() == HALF_OF_LINE;
    }
}
