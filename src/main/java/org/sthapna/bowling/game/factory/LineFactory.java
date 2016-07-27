package org.sthapna.bowling.game.factory;

import org.sthapna.bowling.game.linestrategies.Line;
import org.sthapna.bowling.parser.StringParser;

public class LineFactory {

    public static Line select(StringParser parse) {
        if(parse.isAllStrike())
            return LineMaker.AllStrike.make(parse.tokens());
        if(parse.isAllSpare())
            return LineMaker.AllSpare.make(parse.tokens());
        if(parse.isAllPin())
            return LineMaker.AllPin.make(parse.tokens());
        if(parse.isNoStrikeNoSpare())
            return LineMaker.NoSpareNoStrike.make(parse.tokens());
        return LineMaker.Default.make(parse.tokens());
    }

}
