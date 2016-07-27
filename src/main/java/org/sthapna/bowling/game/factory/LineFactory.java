package org.sthapna.bowling.game.factory;

import org.sthapna.bowling.game.Frame;
import org.sthapna.bowling.game.linestrategies.Line;
import org.sthapna.bowling.parser.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class LineFactory {
    public static Line select(StringParser parse) {
        if(parse.isAllStrike())
            return Line.create(Line.Type.AllStrike);
        if(parse.isAllSpare())
            return makeAllSpareLine(parse.tokens());
        if(parse.isAllPin())
            return makeAllPinLine(parse.tokens());
        return null;
    }

    private static Line makeAllPinLine(List<String> tokens) {
        return Line.create(Line.Type.NoSpareNoStrike).add(
                tokens.stream().filter(e -> e.matches("\\d"))
                        .map(f -> Frame.onePin(Integer.parseInt(f))).collect(Collectors.toList()));
    }

    private static Line makeAllSpareLine(List<String> tokens){
        Line l = Line.create(Line.Type.AllSpare).add(
                tokens.subList(0,tokens.size() - 3).stream().filter(e -> e.matches("\\d"))
                        .map(f -> Frame.spare(Integer.parseInt(f))).collect(Collectors.toList()));
        List<String> lastFrame = tokens.subList(tokens.size() - 3, tokens.size());
        return l.add(Frame.last(Integer.parseInt(lastFrame.get(0)),
                Frame.STRIKE_SCORE - Integer.parseInt(lastFrame.get(0)),Integer.parseInt(lastFrame.get(lastFrame.size()-1))));
    }
}
