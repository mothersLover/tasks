package test.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FunctionalInterfaceTest {

    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,4,5};
        int[] ints = multiplyArray(arr, integer -> integer * 3);
        Assert.assertTrue(Arrays.equals(new int[] {3,6,9,12,15}, ints));
    }

    private int[] multiplyArray(int[] arr, MyFunctionalInterface<Integer, Integer> function) {
        for (int i = 0; i < arr.length; i++) {
            Integer apply = function.apply(arr[i]);
            arr[i] = apply;
        }
        return arr;
    }
}
