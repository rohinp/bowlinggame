package org.sthapna.bowling;

import org.junit.Assert;
import org.junit.Test;

public class FrameTests {

    @Test
    public void itShouldCreateFrame_NormalTwoTries(){
        //given
        Frame frame = Frame.frame(2,3);
        //when

        //then
        Assert.assertEquals(5,frame.score());
    }

    @Test
    public void itShouldCreateFrame_OnePin(){
        //given
        Frame frame = Frame.onePin(3);
        //when

        //then
        Assert.assertEquals(3,frame.score());
    }

    @Test
    public void itShouldCreateFrame_Spare(){
        //given
        Frame frame = Frame.spare(6);
        //when

        //then
        Assert.assertEquals(Frame.STRIKE_SCORE,frame.score());
    }

    @Test
    public void itShouldCreateFrame_Strike(){
        //given
        Frame frame = Frame.strike();
        //when

        //then
        Assert.assertEquals(10,frame.score());
    }

    @Test
    public void itShouldCreateLastFrame(){
        //given
        Frame frame = Frame.last(10,2,10);
        //when

        //then
        Assert.assertEquals(22,frame.score());
    }

}
