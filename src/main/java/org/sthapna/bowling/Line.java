package org.sthapna.bowling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.sthapna.bowling.Frame.*;
import static org.sthapna.bowling.util.ListUtil.*;

public interface Line {
    enum Type {
        NoSpareNoStrike(new NoSpareNoStrike()),
        AllSpare(new SpareEach()),
        AllStrike(new PerfectGame()),
        DEFAULT(new AnyCondLine());

        Line line;
        Type(Line l) {
            line = l;
        }

        Line line(){
            return line;
        }
    }

    Line add(Frame frame);
    int totalScore();

    static Line create(Type type) {
        return type.line();
    }
}

final class PerfectGame implements Line {

    private final int TURNS = 2;
    private final int TOTAL_FRAMES = 10;

    @Override
    public Line add(Frame frame) {
        throw new CannotAddInPerfectGame();
    }

    @Override
    public int totalScore() {
        return (STRIKE_SCORE * TURNS  + STRIKE_SCORE) * TOTAL_FRAMES;
    }

    private class CannotAddInPerfectGame extends RuntimeException {}
}

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
        return loop(tail(line), acc + calculate(line));
    }

    private int calculate(final List<Frame> line) {
        if(head(line).isLast())
            return line.get(0)._2() + STRIKE_SCORE;
        return next(line)._1() + STRIKE_SCORE;
    }
}

final class AnyCondLine implements Line {

    private final List<Frame> line;

    public AnyCondLine(List<Frame> line, Frame frame) {
        this.line = concat(line, Arrays.asList(frame));
    }

    public AnyCondLine(){
        line = new ArrayList<>();
    }

    @Override
    public Line add(Frame frame) {
        return new AnyCondLine(this.line, frame);
    }

    @Override
    public int totalScore() {
        return loop(line,0);
    }

    private int loop(final List<Frame> line,int acc) {
        if(line.isEmpty())
            return acc;
        return loop(tail(line),acc + calculate(line));
    }

    private int calculate(final List<Frame> line) {
        Frame c = head(line);
        if(c.isLast()) {
            if(c.isStrike()) return c.score() - c._1()  + STRIKE_SCORE;
            if(c.isSpare()) return  c.score() - c._1() - c._2() + STRIKE_SCORE;
        }
        if(c.isStrike()){
            if(next(line).isLast()) return next(line)._1() + next(line)._2() + STRIKE_SCORE;
            if(next(line).isStrike()) return 2 * STRIKE_SCORE + nextToNext(line)._1() + nextToNext(line)._2();
        }
        if(c.isSpare()) return next(line)._1() + STRIKE_SCORE;
        return c.score();
    }
}