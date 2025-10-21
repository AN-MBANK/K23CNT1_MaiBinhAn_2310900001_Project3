package com.AnNoOfDev.lesson01_Mba_k23cnt1.Lambda_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortLambdaExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java SpringBoot", "C# NetCore", "PHP", "JavaScript");

        // Sắp xếp, sử dụng biểu thức lambda
        Collections.sort(list, (String str1, String str2) -> str1.compareTo(str2));

        for (String str : list) {
            System.out.println(str);
        }
    }
}