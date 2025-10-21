package com.AnNoOfDev.lesson01_Mba_k23cnt1.method_ref;

@FunctionalInterface
interface ExecuteFunction {
    int execute(int a, int b);
}

class MathUtils {
    public MathUtils() {
        System.out.println("MathUtils: constructor");
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int minus(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }
}

public class DemoMethodRef {
    public static void main(String[] args) {
        int a = 5, b = 10;

        // Static method reference
        ExecuteFunction add = MathUtils::sum;
        System.out.println("Sum = " + add.execute(a, b));

        ExecuteFunction minus = MathUtils::minus;
        System.out.println("Minus = " + minus.execute(a, b));

        // Instance method reference
        MathUtils math = new MathUtils();
        ExecuteFunction mul = math::multiply;
        System.out.println("Multiply = " + mul.execute(a, b));

        // Method reference với String
        String[] languages = {"Java", "C#", "PHP", "JavaScript"};
        System.out.println("\nSắp xếp mảng:");
        java.util.Arrays.sort(languages, String::compareToIgnoreCase);
        for (String s : languages)
            System.out.println(s);
    }
}
