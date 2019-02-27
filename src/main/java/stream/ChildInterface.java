package test.stream;

public interface ChildInterface extends ParentInterface {
    @Override
    default void doSomething() {
        ParentInterface.super.doSomething();
    }
}
