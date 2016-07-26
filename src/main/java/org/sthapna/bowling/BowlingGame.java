package org.sthapna.bowling;

import org.sthapna.bowling.game.factory.LineFactory;
import org.sthapna.bowling.game.linestrategies.Line;
import org.sthapna.bowling.parser.StringParser;

public class BowlingGame {
    Line game;

    public static BowlingGame play() {
        return new BowlingGame();
    }

    public void input(String line) {
        game = LineFactory.select(StringParser.parse(line));
    }

    public int totalScore() {
        return game.totalScore();
    }
}
