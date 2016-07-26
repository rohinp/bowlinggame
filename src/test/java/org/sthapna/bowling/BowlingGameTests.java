package org.sthapna.bowling;

import org.junit.Assert;
import org.junit.Test;

public class BowlingGameTests {


    @Test
    public void itShouldTakeAllStrikeAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("XXXXXXXXXXXX");

        //then
        Assert.assertEquals(300,bowlingGame.totalScore());
    }

    @Test
    public void itShouldTakeAllSpareAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("5/5/5/5/5/5/5/5/5/5/5");

        //then
        Assert.assertEquals(150,bowlingGame.totalScore());
    }
}
