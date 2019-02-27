package test.stream;

@FunctionalInterface
public interface MyFunctionalInterface<R,T> {

    R apply(T t);
}
