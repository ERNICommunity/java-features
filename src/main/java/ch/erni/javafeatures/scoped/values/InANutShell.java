package ch.erni.javafeatures.scoped.values;

public class InANutShell {

    public static final ScopedValue<Integer> scopedValue = ScopedValue.newInstance();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> ScopedValue.where(scopedValue, (int) (Math.random() * 500)).run(() -> {

                for (int j = 0; j < 3; j++) {
                    // access ScopedValue
                    System.out.println(Thread.currentThread().getName() + ": " + scopedValue.get());
                    try {
                        Thread.sleep(500l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            })).start();
        }
    }
}
