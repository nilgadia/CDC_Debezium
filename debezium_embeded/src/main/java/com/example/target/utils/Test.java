package com.example.target.utils;

public class Test {
    public static void main(String[] args) {
        C1 c = new C1();
        I1.say();
        c.hello();
        I2.say();
        I3.say();
        // c.say()
        // C1.say()
    }
}
@FunctionalInterface
interface I1 {
    void doSomething();
    default void hello() {
        System.out.println("Hello from I1");
    }
    static void say() {
        System.out.println("Say from I1");
    }
}
@FunctionalInterface
interface I2 {
    void doSomething();
    default void hello() {
        System.out.println("Hello from I2");
    }
    static void say() {
        System.out.println("Say from I2");
    }
}
interface I3 extends I1, I2 {
    void doSomething();

    static void say() {
        System.out.println("Say I3");
    }

    // Compilation error, if we will not override
    @Override
    default void hello() {
        I1.super.hello();
    }
}
class C1 implements I3 {

    @Override
    public void doSomething() {
        System.out.println("Do something");
    }

    static void say() {
        System.out.println("Say I3");
    }
}
