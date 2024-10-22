package ch.erni.javafeatures.flexible.constructor.bodies;

public class Child extends Parent {

    private final int b;

    public Child(int a, int b) {
        super(verifyBothPositive(a, b));
        this.b = b;
    }

    private static int verifyBothPositive(int a, int b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException();
        return a;
    }

    @Override
    void printMe() {
        super.printMe();
        System.out.println("b = " + b);
    }

    public static void main(String[] args) {
        new Child(1, 2);
    }
}
