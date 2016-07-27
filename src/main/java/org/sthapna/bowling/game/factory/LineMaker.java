package org.sthapna.bowling.game.factory;

import org.sthapna.bowling.game.Frame;
import org.sthapna.bowling.game.Symbols;
import org.sthapna.bowling.game.linestrategies.Line;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static org.sthapna.bowling.game.Frame.*;
import static org.sthapna.bowling.game.linestrategies.Line.*;
import static org.sthapna.bowling.util.ListUtil.head;
import static org.sthapna.bowling.util.ListUtil.next;
import static org.sthapna.bowling.util.ListUtil.tail;

public enum LineMaker {
    AllStrike {
        @Override
        public Line make(List<String> line) {
            return create(Type.AllStrike);
        }
    },
    AllSpare {
        @Override
        public Line make(List<String> tokens) {
            return loop(tokens.stream().filter(e -> e.matches("\\d"))
                    .collect(Collectors.toList()), create(Type.AllSpare));
        }

        private Line loop(final List<String> tokens, final Line accLine) {
            if(accLine.noOfFrames() == 9)
                return accLine.add(makeFrame(tokens));
            return loop(tail(tokens),accLine.add(makeFrame(tokens)));
        }

        private Frame makeFrame(List<String> tokens) {
            return tokens.size() != 2 ? spare(parseInt(tokens.get(0))):
                    last(parseInt(tokens.get(0)),STRIKE_SCORE - parseInt(tokens.get(0)), parseInt(tokens.get(1)));
        }
    },
    AllPin {
        @Override
        Line make(List<String> tokens) {
            return create(Type.NoSpareNoStrike).add(tokens.stream().filter(e -> e.matches("\\d"))
                    .map(f -> onePin(parseInt(f))).collect(Collectors.toList()));
        }
    },

    NoSpareNoStrike {
        @Override
        Line make(List<String> tokens) {
            return loop(tokens, create(Type.NoSpareNoStrike));
        }

        private  Line loop(final List<String> tokens, final Line accLine) {
            if(tokens.isEmpty())
                return accLine;
            return loop(tail(tail(tokens)),accLine.add(frame(parseInt(tokens.get(0)), parseInt(tokens.get(1)))));
        }
    },

    Default {
        @Override
        Line make(List<String> tokens) {
            return loop(tokens, create(Type.DEFAULT));
        }

        private  Line loop(final List<String> tokens, final Line accLine) {
            if(accLine.noOfFrames() == 9)
                return accLine.add(makeFrame(tokens));
            if(head(tokens).equals(Symbols.STRIKE.val())) return loop(tail(tokens),accLine.add(Frame.strike()));
            if(next(tokens).equals(Symbols.SPARE.val())) return loop(tail(tail(tokens)),accLine.add(Frame.spare(parseInt(tokens.get(0)))));
            if(next(tokens).equals(Symbols.PIN.val())) return loop(tail(tail(tokens)),accLine.add(Frame.onePin(parseInt(tokens.get(0)))));
            return loop(tail(tail(tokens)),accLine.add(frame(parseInt(tokens.get(0)), parseInt(tokens.get(1)))));
        }

        private Frame makeFrame(List<String> tokens) {
            return tokens.size() == 3 ? Frame.last(parseInt(tokens.get(0)),parseInt(tokens.get(1)),parseInt(tokens.get(2))):
                    Frame.last(parseInt(tokens.get(0)),parseInt(tokens.get(1)),0);
        }
    };


    abstract Line make(List<String> line);
}
