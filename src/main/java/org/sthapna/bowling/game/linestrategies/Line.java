package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

import java.util.List;

public interface Line {


    enum Type {
        AllStrike(new PerfectGame()),
        NORMAL(new NormalLine());

        final Line line;
        Type(Line l) {
            line = l;
        }

        Line line(){
            return line;
        }
    }

    Line add(Frame frame);
    Line add(List<Frame> frames);
    int totalScore();
    int noOfFrames();

    static Line create(Type type) {
        return type.line();
    }
    static Line create() {
        return new NormalLine();
    }
}

