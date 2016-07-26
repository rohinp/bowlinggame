package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.List;

final class PerfectGame implements Line {

    @Override
    public Line add(Frame frame) {
        throw new PerfectGame.CannotAddInPerfectGame();
    }

    @Override
    public Line add(List<Frame> frames) {
        throw new PerfectGame.CannotAddInPerfectGame();
    }

    @Override
    public int totalScore() {
        int TOTAL_FRAMES = 10;
        int TURNS = 2;
        return (Frame.STRIKE_SCORE * TURNS + Frame.STRIKE_SCORE) * TOTAL_FRAMES;
    }

    private class CannotAddInPerfectGame extends RuntimeException {}
}
