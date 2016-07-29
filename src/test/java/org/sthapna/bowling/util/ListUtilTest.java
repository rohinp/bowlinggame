package org.sthapna.bowling.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.sthapna.bowling.util.ListUtil.*;

public class ListUtilTest {

    private final List<Integer> one = new ArrayList<Integer>(){{add(1);add(2);}};
    private final List<Integer> two = new ArrayList<Integer>(){{add(3);add(4);}};

    private final List<Integer> test = new ArrayList<Integer>(){{add(1);add(2);add(3);add(4);}};

    @Test
    public void testConcat() throws Exception {
        //given

        //when

        //then
        assertArrayEquals(test.toArray(), concat(one,two).toArray());

    }

    @Test
    public void testHead() throws Exception {
        //given

        //when

        //then
        assertEquals(1,head(one).intValue());
    }

    @Test
    public void testTail() throws Exception {
        //given
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(2);
            add(3);
            add(4);
        }};
        //when

        //then
        assertArrayEquals(expected.toArray(),tail(test).toArray());
    }

    @Test
    public void testNext() throws Exception {
        //given

        //when

        //then
        assertEquals(2,next(one).intValue());
    }

    @Test
    public void testNextToNext() throws Exception {
        //given

        //when

        //then
        assertEquals(3,nextToNext(test).intValue());
    }

    @Test
    public void testInit() throws Exception {
        //given
        ArrayList<Integer> expected = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        //when

        //then
        assertArrayEquals(expected.toArray(),init(test).toArray());
    }

    @Test
    public void testFromLast() throws Exception {
        //given

        //when

        //then
        assertEquals(2,fromLast(one,1).intValue());
    }
}