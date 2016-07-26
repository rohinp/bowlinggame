package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.sthapna.bowling.util.ListUtil.concat;

final class NoSpareNoStrike implements Line {
    private final List<Frame> line;

    public NoSpareNoStrike(List<Frame> line, Frame frame) {
        this.line = concat(line, Arrays.asList(frame));
    }

    public NoSpareNoStrike(){
        line = new ArrayList<>();
    }

    @Override
    public Line add(Frame frame) {
        return new NoSpareNoStrike(this.line, frame);
    }

    @Override
    public int totalScore() {
        return line.stream().mapToInt(Frame::score).sum();
    }
}
