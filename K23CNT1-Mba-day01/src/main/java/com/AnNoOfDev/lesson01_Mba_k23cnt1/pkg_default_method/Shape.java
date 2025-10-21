package com.AnNoOfDev.lesson01_Mba_k23cnt1.pkg_default_method;

public interface Shape {
    void draw();
    default void setColor(String color){
        System.out.println("Draw With Color");
}
public interface Interface1 {
    default void method1(){
        System.out.println("Interface1.method1");
    }
}

    public interface Interface2 {
        default void method1() {
            System.out.println("Interface1.method1");
        }
    }

public class MultiInheritance implements Interface1,Interface2{
        @Override
        public void method1(){
            Interface1.super.method1();
        }
        public void method2(){
            System.out.println("MultiInhertance.method2");
        }
        public static  void main(String[] args){
            MultiInheritance mi = new MultiInheritance();
            mi.method1();
            mi.method2();
        }
    }}