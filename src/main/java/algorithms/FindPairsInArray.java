package test.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FindPairsInArray {

    @Test
    public void test() {
        int[] arr = new int[] {1,2,3,4,5,6,-1,-2,12,23,-5};
        int count = calculatePairs(arr);
        Assert.assertEquals(3, count);
    }

    public static int calculatePairs(int[] arr) {
        Set<Integer> integers = new HashSet<>(arr.length);
        int count = 0;
        for(int i = 0; i < arr.length; i++) {
            if (i < arr.length / 2) {
                integers.add(arr[i]);
            } else {
                if (integers.contains(arr[i] * -1)) {
                    count++;
                }
            }
        }
        return count;
    }
}
