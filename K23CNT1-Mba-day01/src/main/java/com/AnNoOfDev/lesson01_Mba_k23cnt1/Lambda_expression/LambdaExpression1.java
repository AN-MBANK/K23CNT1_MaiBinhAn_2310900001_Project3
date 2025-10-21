package com.AnNoOfDev.lesson01_Mba_k23cnt1.Lambda_expression;

@FunctionalInterface
interface SayHello1 {
    void sayHello();
}


public class LambdaExpression1 {
    public static void main(String[] args) {
        SayHello1 sayHello = () -> {
            System.out.println("Hello AnNotDev");
        };
        sayHello.sayHello();
    }
}