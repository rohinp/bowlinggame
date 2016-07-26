package org.sthapna.bowling.game.parser;

import org.junit.Test;
import org.sthapna.bowling.parser.StringParser;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class StringParserTests {

    @Test
    public void itShouldParseUserInputToTokens(){
        //given
        StringParser stringParser = StringParser.parse("12345123451234512345");
        //when

        //then
        assertArrayEquals("12345123451234512345".split(""),stringParser.tokens().toArray());
    }

    @Test
    public void itShouldParseUserInputToTokens_checkForAllStrike(){
        //given
        StringParser stringParser = StringParser.parse("XXXXXXXXXXXX");
        //when

        //then
        assertTrue(stringParser.isAllStrike());
    }

    @Test
    public void itShouldParseUserInputToTokens_checkForAllSpare(){
        //given
        StringParser stringParser = StringParser.parse("5/5/5/5/5/5/5/5/5/5/5");
        //when

        //then
        assertTrue(stringParser.isAllSpare());
    }

    @Test
    public void itShouldParseUserInputToTokens_checkForAllPin(){
        //given
        StringParser stringParser = StringParser.parse("9-9-9-9-9-9-9-9-9-9-");
        //when

        //then
        assertTrue(stringParser.isAllPin());
    }
}
