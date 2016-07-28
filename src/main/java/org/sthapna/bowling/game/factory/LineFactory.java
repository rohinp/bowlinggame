package org.sthapna.bowling.game.factory;

import org.sthapna.bowling.game.linestrategies.Line;
import org.sthapna.bowling.parser.StringParser;

public class LineFactory {

    public static Line select(StringParser parse) {
        if(parse.isAllStrike())
            return LineBuilder.AllStrike.builder(parse.tokens());
        if(parse.isAllSpare())
            return LineBuilder.AllSpare.builder(parse.tokens());
        if(parse.isAllPin())
            return LineBuilder.AllPin.builder(parse.tokens());
        if(parse.isNoStrikeNoSpare())
            return LineBuilder.NoSpareNoStrike.builder(parse.tokens());
        return LineBuilder.Default.builder(parse.tokens());
    }

}
