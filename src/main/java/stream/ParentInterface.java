package test.stream;

public interface ParentInterface {

    default void doSomething() {
        System.out.println("Parent interface");
    }

    void doSomething2();
}
