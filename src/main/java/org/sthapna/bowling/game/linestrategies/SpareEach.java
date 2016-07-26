package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.sthapna.bowling.game.Frame.STRIKE_SCORE;
import static org.sthapna.bowling.util.ListUtil.*;

final class SpareEach implements Line {
    private final List<Frame> line;

    public SpareEach(List<Frame> line, Frame frame) {
        this.line = concat(line, Arrays.asList(frame));
    }

    public SpareEach(){
        line = new ArrayList<>();
    }

    @Override
    public Line add(Frame frame) {
        return new SpareEach(this.line, frame);
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
