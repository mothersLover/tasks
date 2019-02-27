package test.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamDeleteRepeatTest {

    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,1};
        int[] arr1 = new int[] {4,5,6};

        Stream<int[]> concat = Stream.concat(Stream.of(arr), Stream.of(arr1));
        Object[] collect = concat.flatMapToInt(Arrays::stream).boxed().toArray();
        int[] uniqueInts = Stream.concat(Stream.of(arr), Stream.of(arr1)).flatMapToInt(Arrays::stream).distinct().toArray();
        Assert.assertTrue(Arrays.equals(new int[] {1,2,3,4,5,6}, uniqueInts));
        Stream<Integer> concat1 = Stream.concat(Arrays.stream(arr).boxed(), Arrays.stream(arr1).boxed());
        Object[] objects = concat1.toArray();
        Assert.assertTrue(Arrays.equals(collect, objects));

        Object[] uniqueValues = Arrays.stream(arr).boxed().distinct().toArray();
        int[] ints = Arrays.stream(arr).distinct().toArray();
        Assert.assertTrue(Arrays.equals(new Object[] {1,2,3}, uniqueValues));
    }
}
