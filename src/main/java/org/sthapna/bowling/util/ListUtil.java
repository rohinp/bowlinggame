package org.sthapna.bowling.util;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtil {

    public static List<Frame> concat(List<Frame> listOne,List<Frame> listTwo) {

        return listOne != null ? new ArrayList<>(Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList())):
                new ArrayList<>(listTwo);
    }

    public static Frame head(List<Frame> list) {
        return list.get(0);
    }

    public static List<Frame> tail(List<Frame> list) {
        return list.subList(1,list.size());
    }

    public static Frame next(final List<Frame> list) {
        if(list.size() > 1)
            return list.get(1);
        throw new LineEmptyException();
    }

    public static Frame nextToNext(final List<Frame> list) {
        if(list.size() >= 2)
            return list.get(2);
        throw new LineEmptyException();
    }


    private static class LineEmptyException extends RuntimeException {
    }
}
