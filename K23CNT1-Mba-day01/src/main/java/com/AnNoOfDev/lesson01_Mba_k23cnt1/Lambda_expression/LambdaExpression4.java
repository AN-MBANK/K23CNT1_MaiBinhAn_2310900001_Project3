package com.AnNoOfDev.lesson01_Mba_k23cnt1.Lambda_expression;

import java.util.Arrays;
import java.util.List;

public class LambdaExpression4 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java SpringBoot", "C#", "NodeJS", "PHP", "JavaScript");

        // Duyệt qua danh sách
        names.forEach(item -> System.out.println(item));

        System.out.println("\nDùng method reference:");
        names.forEach(System.out::println);
    }
}
