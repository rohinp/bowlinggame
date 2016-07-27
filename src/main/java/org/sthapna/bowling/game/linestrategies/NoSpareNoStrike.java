package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.sthapna.bowling.util.ListUtil.concat;

final class NoSpareNoStrike implements Line {
    private final List<Frame> line;

    private NoSpareNoStrike(List<Frame> line, Frame frame) {
        this.line = concat(line, Collections.singletonList(frame));
    }

    public NoSpareNoStrike(){
        line = new ArrayList<>();
    }

    private NoSpareNoStrike(List<Frame> frames) {
        this.line = frames;
    }

    @Override
    public Line add(Frame frame) {
        return new NoSpareNoStrike(this.line, frame);
    }

    @Override
    public Line add(List<Frame> frames) {
        return new NoSpareNoStrike(frames);
    }

    @Override
    public int totalScore() {
        return line.stream().mapToInt(Frame::score).sum();
    }
}
