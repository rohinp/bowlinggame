package org.sthapna.bowling.game.factory;

import org.sthapna.bowling.game.Frame;
import org.sthapna.bowling.game.Symbols;
import org.sthapna.bowling.game.linestrategies.Line;
import org.sthapna.bowling.parser.StringParser;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static org.sthapna.bowling.game.Frame.*;
import static org.sthapna.bowling.game.linestrategies.Line.*;
import static org.sthapna.bowling.util.ListUtil.*;

public enum LineFactory {
    AllStrike {
        @Override
        public Line builder(List<String> line) {
            return perfectGame();
        }
    },

    AllSpare {
        @Override
        public Line builder(List<String> tokens) {
            return loop(tokens.stream().filter(e -> e.matches("\\d"))
                    .collect(Collectors.toList()), create());
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
        Line builder(List<String> tokens) {
            return create().add(init(tokens).stream().filter(e -> e.matches("\\d"))
                    .map(f -> onePin(parseInt(f))).collect(Collectors.toList()))
                    .add(last(parseInt(lastElm(tokens)),0,0));
        }
    },

    NoSpareNoStrike {
        @Override
        Line builder(List<String> tokens) {
            return loop(tokens, create());
        }

        private  Line loop(final List<String> tokens, final Line accLine) {
            if(accLine.noOfFrames() == 9)
                return accLine.add(last(parseInt(tokens.get(0)), parseInt(tokens.get(1)),0));
            return loop(tail(tail(tokens)),accLine.add(frame(parseInt(tokens.get(0)), parseInt(tokens.get(1)))));
        }
    },

    Default {
        @Override
        Line builder(List<String> tokens) {
            return loop(tokens, create());
        }

        private  Line loop(final List<String> tokens, final Line accLine) {
            if(accLine.noOfFrames() == 9) return accLine.add(makeFrame(tokens));
            if(head(tokens).equals(Symbols.STRIKE.val())) return loop(tail(tokens),accLine.add(strike()));
            if(next(tokens).equals(Symbols.SPARE.val())) return loop(tail(tail(tokens)),accLine.add(spare(parseInt(tokens.get(0)))));
            if(next(tokens).equals(Symbols.PIN.val())) return loop(tail(tail(tokens)),accLine.add(onePin(parseInt(tokens.get(0)))));
            return loop(tail(tail(tokens)),accLine.add(frame(parseInt(tokens.get(0)), parseInt(tokens.get(1)))));
        }

        private Frame makeFrame(List<String> tokens) {
            return tokens.size() == 3 ? last(parseInt(tokens.get(0)),parseInt(tokens.get(1)),parseInt(tokens.get(2))):
                    last(parseInt(tokens.get(0)),parseInt(tokens.get(1)),0);
        }
    };

    public static Line select(StringParser parse) {
        if(parse.isAllStrike()) return AllStrike.builder(parse.tokens());
        if(parse.isAllSpare()) return AllSpare.builder(parse.tokens());
        if(parse.isAllPin()) return AllPin.builder(parse.tokens());
        if(parse.isNoStrikeNoSpare()) return NoSpareNoStrike.builder(parse.tokens());
        return Default.builder(parse.tokens());
    }

    abstract Line builder(List<String> line);
}
