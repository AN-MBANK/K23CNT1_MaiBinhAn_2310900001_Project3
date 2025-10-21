package com.AnNoOfDev.lesson01_Mba_k23cnt1.pkg_stream_api;

import java.util.Arrays;
import java.util.List;

public class StreamExample {
    public static void withOutStream() {
        List<Integer> integerList = Arrays.asList(11, 22, 59, 33, 44, 66);
        int count = 0;
        for (Integer i : integerList) {
            if (i % 2 == 0)
                count++;
        }
        System.out.println("withoutStream => Số phần tử chẵn: " + count);
    }

    public static void withStream() {
        List<Integer> integerList = Arrays.asList(11, 22, 59, 33, 44, 66);
        long count = integerList.stream().filter(num -> num % 2 == 0).count();
        System.out.println("withStream => Số phần tử chẵn: " + count);
    }

    public static void main(String[] args) {
        StreamExample.withOutStream();
        StreamExample.withStream();
    }
}
