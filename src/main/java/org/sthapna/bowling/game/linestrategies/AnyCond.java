package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.sthapna.bowling.game.Frame.STRIKE_SCORE;
import static org.sthapna.bowling.util.ListUtil.*;

final class AnyCond implements Line {

    private final List<Frame> line;

    public AnyCond(List<Frame> line, Frame frame) {
        this.line = concat(line, Arrays.asList(frame));
    }

    public AnyCond(){
        line = new ArrayList<>();
    }

    public AnyCond(List<Frame> frames) {
        this.line = frames;
    }

    @Override
    public Line add(Frame frame) {
        return new AnyCond(this.line, frame);
    }

    @Override
    public Line add(List<Frame> frames) {
        return new AnyCond(frames);
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
        if(head(line).isLast()) return calculateIfLast(line);
        if(head(line).isStrike())return calculateIfStrike(line);
        if(head(line).isSpare()) return next(line)._1() + STRIKE_SCORE;
        return head(line).score();
    }

    private int calculateIfLast(final List<Frame> line){
        Frame c = head(line);
        if(c.isStrike()) return c.score() - c._1()  + STRIKE_SCORE;
        if(c.isSpare()) return  c.score() - c._1() - c._2() + STRIKE_SCORE;
        return c.score();
    }

    private int calculateIfStrike(final List<Frame> line){
        if(next(line).isLast()) return next(line)._1() + next(line)._2() + STRIKE_SCORE;
        else if(next(line).isStrike()) return 2 * STRIKE_SCORE + nextToNext(line)._1();
        else return STRIKE_SCORE + next(line).score();
    }

}
