package org.sthapna.bowling.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUtil {

    public static<T> List<T> concat(List<T> listOne,List<T> listTwo) {
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
        return list.subList(0,list.size() - 1);
    }

    public static <T> T fromLast(final List<T> list,int index){
        return list.get(list.size() - index);
    }

    private static class LineEmptyException extends RuntimeException {
    }
}
