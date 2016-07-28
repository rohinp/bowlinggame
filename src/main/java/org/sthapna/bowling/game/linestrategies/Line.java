package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.sthapna.bowling.util.ListUtil.concat;
import static org.sthapna.bowling.util.ListUtil.tail;

public interface Line {

    Line add(Frame frame);
    Line add(List<Frame> frames);
    int totalScore();
    int noOfFrames();

    static Line perfectGame() {
        return new Line() {

            private final int TOTAL_FRAMES = 10;

            @Override
            public Line add(Frame frame) {
                throw new CannotAddInPerfectGame();
            }

            @Override
            public Line add(List<Frame> frames) {
                throw new CannotAddInPerfectGame();
            }

            @Override
            public int totalScore() {
                int TURNS = 2;
                return (Frame.STRIKE_SCORE * TURNS + Frame.STRIKE_SCORE) * TOTAL_FRAMES;
            }

            @Override
            public int noOfFrames() {
                return TOTAL_FRAMES;
            }

            class CannotAddInPerfectGame extends RuntimeException {}
        };
    }

    static Line create() {
        return new NormalLine();
    }

    final class NormalLine implements Line {

        private final List<Frame> line;

        private NormalLine(List<Frame> line, Frame frame) {
            this.line = concat(line, Collections.singletonList(frame));
        }

        public NormalLine(){
            line = new ArrayList<>();
        }

        private NormalLine(List<Frame> frames) {
            this.line = frames;
        }

        @Override
        public Line add(Frame frame) {
            return new NormalLine(this.line, frame);
        }

        @Override
        public Line add(List<Frame> frames) {
            return new NormalLine(frames);
        }

        @Override
        public int totalScore() {
            return loop(line,0);
        }

        @Override
        public int noOfFrames() {
            return line.size();
        }

        private int loop(final List<Frame> line,int acc) {
            if(line.isEmpty())
                return acc;
            return loop(tail(line),acc + Operations.apply(line));
        }

    }
}



