package test.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class Factorial {

    @Test
    public void test() {
        long j = factorial(100);
        long i = factorial(100) / (factorial(97) * factorial(3));
        Assert.assertEquals(161700, i);
    }

    public static long factorial(int n) {
        if (n <= 1) {
            return n;
        }
        long i = n * factorial(n - 1);
        System.out.println("i = " + i);
        return i;
    }
}
