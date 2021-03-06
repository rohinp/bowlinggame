package org.sthapna.bowling.game.linestrategies;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.sthapna.bowling.game.Frame.*;
import static org.sthapna.bowling.game.linestrategies.Line.create;
import static org.sthapna.bowling.game.linestrategies.Line.perfectGame;

public class LineTests {
    @Test
    public void itShouldCreateALineWith_NoSpareNoStrike(){
        //given
        Line line = create();
        //when
        line = line.add(frame(1,2))
                .add(frame(3,4))
                .add(frame(5,1))
                .add(frame(2,3))
                .add(frame(4,5))
                .add(frame(1,2))
                .add(frame(3,4))
                .add(frame(5,1))
                .add(frame(2,3))
                .add(last(4,5,0));
        //then
        assertEquals(60, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllPinNoStrikeNoSpare(){
        //given
        Line line = create();
        //when
        line = line.add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(onePin(5))
                .add(last(5,0,0));
        //then
        assertEquals(50, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllStrike(){
        //given
        Line line = perfectGame();
        //when

        //then
        assertEquals(300, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllSpare(){
        //given
        Line line = create();

        //when
        line = line.add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(spare(5))
                .add(last(5,5,5));

        //then
        assertEquals(150, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_CoupleOfStrikeConsecutiveSpare_LastFrameNormal(){
        //given
        Line line = create();

        //when
        line = line.add(frame(1,2))
                .add(frame(3,4))
                .add(strike())
                .add(frame(2,3))
                .add(frame(4,5))
                .add(strike())
                .add(frame(3,4))
                .add(spare(6))
                .add(spare(7))
                .add(last(4,5,0));

        //then
        assertEquals(103, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_ConsecutiveStrikeCoupleOfSpare_LastFrameNormal(){
        //given
        Line line = create();

        //when
        line = line.add(frame(1,2))
                .add(frame(3,4))
                .add(strike())
                .add(strike())
                .add(frame(2,3))
                .add(frame(4,5))
                .add(spare(6))
                .add(frame(3,4))
                .add(spare(7))
                .add(last(4,5,0));

        //then
        assertEquals(104, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_ConsecutiveStrikeCoupleOfSpare_LastFrameAllStrike(){
        //given
        Line line = create();

        //when
        line = line.add(frame(1,2))
                .add(frame(3,4))
                .add(strike())
                .add(strike())
                .add(frame(2,3))
                .add(frame(4,5))
                .add(spare(6))
                .add(frame(3,4))
                .add(spare(7))
                .add(last(STRIKE_SCORE,STRIKE_SCORE,STRIKE_SCORE));

        //then
        assertEquals(131, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_ConsecutiveStrikeLastFrameAllStrike(){
        //given
        Line line = create();

        //when
        line = line.add(frame(1,2))
                .add(frame(3,4))
                .add(strike())
                .add(strike())
                .add(frame(2,3))
                .add(frame(4,5))
                .add(spare(6))
                .add(frame(3,4))
                .add(strike())
                .add(last(STRIKE_SCORE,STRIKE_SCORE,STRIKE_SCORE));

        //then
        assertEquals(141, line.totalScore());

    }
}
