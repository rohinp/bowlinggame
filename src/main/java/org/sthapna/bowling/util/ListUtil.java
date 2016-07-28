package org.sthapna.bowling.util;

import org.sthapna.bowling.game.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtil {

    public static List<Frame> concat(List<Frame> listOne,List<Frame> listTwo) {
        return listOne != null ? new ArrayList<>(Stream.concat(listOne.stream(), listTwo.stream()).collect(Collectors.toList())):
                new ArrayList<>(listTwo);
    }

    public static<T> T head(List<T> list) {
        return list.get(0);
    }

    public static<T> List<T> tail(List<T> list) {
        return list.subList(1,list.size());
    }

    public static<T> T next(final List<T> list) {
        if(list.size() > 1)
            return list.get(1);
        throw new LineEmptyException();
    }

    public static<T> T nextToNext(final List<T> list) {
        if(list.size() >= 2)
            return list.get(2);
        throw new LineEmptyException();
    }

    public static <T> List<T> init(final List<T> list) {
        return list.subList(0,list.size() - 2);
    }

    public static <T> T lastElm(final List<T> list){
        return list.get(list.size() - 1);
    }

    private static class LineEmptyException extends RuntimeException {
    }
}
