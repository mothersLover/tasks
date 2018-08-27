package test.datastructures.hashtables;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountTriplets {

    @Test
    public void test1() {
        List<Long> longs = Arrays.asList(1L, 4L, 16L, 64L);
        Assert.assertEquals(2, countTriplets(longs, 4));
    }

    @Test
    public void test2() {
        List<Long> longs = Arrays.asList(1L, 2L, 2L, 4L);
        Assert.assertEquals(2, countTriplets(longs, 2));
    }

    @Test
    public void test3() {
        List<Long> longs = Arrays.asList(1L, 3L, 9L, 9L, 27L, 81L);
        Assert.assertEquals(6, countTriplets(longs, 3));
    }

    @Test
    public void test4() {
        List<Long> longs = Arrays.asList(1L, 5L, 5L, 25L, 125L);
        Assert.assertEquals(4, countTriplets(longs, 5));
    }

    @Test
    public void test5() {
        List<Long> longs = Arrays.asList(1L, 5L, 5L, 25L, 125L);
        Assert.assertEquals(4, countTriplets(longs, 5));
    }

    @Test
    public void test6() {
        List<Long> longs = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            longs.add(1L);
        }
        Assert.assertEquals(161700, countTriplets(longs, 1));
    }

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        final Long denominator = r;
        Map<Long, List<Long>> map = arr.stream().collect(Collectors.groupingBy(item -> item / denominator));
        if (map.size() == 1) {
            long size = (long) arr.size();
            return ((size - 2) * (size - 1) * size) / 6;
        }
        long first = 0, second = 1, third = denominator;
        long max = 0;
        while(!map.isEmpty()) {
            int localMax = 1;
            List<Long> firstNumbers = map.get(first);
            List<Long> secondNumbers = map.get(second);
            List<Long> thirdNumbers = map.get(third);
            if (thirdNumbers == null) {
                break;
            }
            if (firstNumbers != null && secondNumbers != null) {
                localMax *= firstNumbers.size();
                localMax *= secondNumbers.size();
                localMax *= thirdNumbers.size();
            }
            map.remove(first);
            max += localMax;
            first = second;
            second = third;
            third = second * denominator;
        }
        return max;
    }
}
