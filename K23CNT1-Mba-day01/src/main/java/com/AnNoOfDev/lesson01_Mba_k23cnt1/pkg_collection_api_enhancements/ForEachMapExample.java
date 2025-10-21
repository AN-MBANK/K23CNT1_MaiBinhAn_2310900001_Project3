package com.AnNoOfDev.lesson01_Mba_k23cnt1.pkg_collection_api_enhancements;

import java.util.HashMap;
import java.util.Map;

public class ForEachMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Java");
        map.put(2, "C#");
        map.put(3, "NodeJS");
        map.put(4, "Python");

        System.out.println("Duyệt Map:");
        map.forEach((key, value) -> System.out.println(key + " => " + value));
    }
}
