package org.sthapna.bowling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BowlingGameTests {


    @Test
    public void itShouldTakeAllStrikeAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("XXXXXXXXXXXX");

        //then
        assertEquals(300,bowlingGame.totalScore());
    }

    @Test
    public void itShouldTakeAllSpareAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("5/5/5/5/5/5/5/5/5/5/5");

        //then
        assertEquals(150,bowlingGame.totalScore());
    }

    @Test
    public void itShouldTakeAllPinAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("9-9-9-9-9-9-9-9-9-9-");

        //then
        assertEquals(90,bowlingGame.totalScore());
    }

    @Test
    public void itShouldTakeNoStrikeNoSpareAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("12345123451234512345");

        //then
        assertEquals(60,bowlingGame.totalScore());
    }

    @Test
    public void itShouldTakeNormalGameAndCalculateTotal(){
        //given
        BowlingGame bowlingGame = BowlingGame.play();

        //when
        bowlingGame.input("1234X2345X346/7/450");

        //then
        assertEquals(103,bowlingGame.totalScore());
    }
}
