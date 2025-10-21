package com.AnNoOfDev.lesson01_Mba_k23cnt1.Lambda_expression;

@FunctionalInterface
interface Calculator1 {
    int add(int a, int b);
}

@FunctionalInterface
interface Calculator2 {
    void add(int a, int b);
}

public class LambdaExpression3 {
    public static void main(String[] args) {

        Calculator1 calc1 = (int a, int b) -> (a + b);
        System.out.println(calc1.add(21, 22));

        Calculator2 calc2 = (a, b) -> {
            int sum = a + b;
            System.out.println(a + "+" + b + "=" + sum);
        };
        calc2.add(41, 42);
    }
}
