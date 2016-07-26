package org.sthapna.bowling.game.linestrategies;

import org.sthapna.bowling.game.Frame;

public interface Line {
    enum Type {
        NoSpareNoStrike(new NoSpareNoStrike()),
        AllSpare(new SpareEach()),
        AllStrike(new PerfectGame()),
        DEFAULT(new AnyCond());

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

