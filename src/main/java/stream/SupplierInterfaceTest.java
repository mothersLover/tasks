package test.stream;

import org.junit.Test;

import java.util.stream.Stream;

public class SupplierInterfaceTest {


    /**
     * The Supplier functional interface is yet another Function specialization that does not take any arguments.
     * It is typically used for lazy generation of values.
     */
    @Test
    public void test() {
        final int[] fibs = {0, 1};
        Stream<Integer> fibStream = Stream.generate(() -> {
            int result = fibs[1];
            int fibNext = fibs[0] + fibs[1];
            fibs[0] = fibs[1];
            fibs[1] = fibNext;
            return result;
        });
        Object[] objects = fibStream.skip(1).limit(1000).toArray();
        System.out.println();

        Stream<Integer> iterate = Stream.iterate(0, i -> i + 1);
        iterate.limit(10).forEach(System.out::println);
    }
}
