package ch.erni.javafeatures.flexible.constructor.bodies;

public class Parent {

    private final int a;

    public Parent(int a) {
        this.a = a;
        printMe();
    }

    void printMe() {
        System.out.println("a = " + a);
    }
}
