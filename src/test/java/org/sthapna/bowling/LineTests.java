package org.sthapna.bowling;

import org.junit.Assert;
import org.junit.Test;

import static org.sthapna.bowling.Frame.*;
import static org.sthapna.bowling.Line.*;

public class LineTests {
    @Test
    public void itShouldCreateALineWith_NoSpareNoStrike(){
        //given
        Line line = create(Type.NoSpareNoStrike);
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
                .add(frame(4,5));
        //then
        Assert.assertEquals(60, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllPinNoStrikeNoSpare(){
        //given
        Line line = create(Type.NoSpareNoStrike);
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
                .add(onePin(5));
        //then
        Assert.assertEquals(50, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllStrike(){
        //given
        Line line = create(Type.AllStrike);
        //when

        //then
        Assert.assertEquals(300, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_AllSpare(){
        //given
        Line line = create(Type.AllSpare);

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
        Assert.assertEquals(150, line.totalScore());

    }

    @Test
    public void itShouldCreateALineWith_CoupleOfStrikeCoupleOfSpare_LastFrameNormal(){
        //given
        Line line = create(Type.DEFAULT);

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
        Assert.assertEquals(103, line.totalScore());

    }
}
