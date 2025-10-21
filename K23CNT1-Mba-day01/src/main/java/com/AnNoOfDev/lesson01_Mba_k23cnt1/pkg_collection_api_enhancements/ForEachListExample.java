package com.AnNoOfDev.lesson01_Mba_k23cnt1.pkg_collection_api_enhancements;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ForEachListExample {
    public static void main(String[] args) {
        List<String> languages = Arrays.asList("Java Spring", "C#", "NodeJS API", "PHP Laravel", "JavaScript");

        System.out.println("Sử dụng kiểu Lambda:");
        languages.forEach(lang -> System.out.println(lang));

        System.out.println("\nSử dụng method reference:");
        languages.forEach(System.out::println);
    }
}
