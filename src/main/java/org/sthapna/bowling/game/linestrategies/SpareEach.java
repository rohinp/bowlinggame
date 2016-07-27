package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.sthapna.bowling.game.Frame.STRIKE_SCORE;
import static org.sthapna.bowling.util.ListUtil.*;

final class SpareEach implements Line {
    private final List<Frame> line;

    private SpareEach(List<Frame> line, Frame frame) {
        this.line = concat(line, Collections.singletonList(frame));
    }

    public SpareEach(){
        line = new ArrayList<>();
    }

    private SpareEach(List<Frame> frames) {
        this.line = frames;
    }

    @Override
    public Line add(Frame frame) {
        return new SpareEach(this.line, frame);
    }

    @Override
    public Line add(List<Frame> frames) {
        return new SpareEach(frames);
    }

    @Override
    public int totalScore() {
        return loop(line,0);
    }

    private int loop(final List<Frame> line,final int acc) {
        if(line.isEmpty())
            return acc;
        return loop(tail(line), acc + (head(line).isLast()?head(line)._2() + STRIKE_SCORE : next(line)._1() + STRIKE_SCORE));
    }

}
