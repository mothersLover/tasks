package strings;

import org.junit.Test;

import java.util.Arrays;

public class PrintAnagram {

    @Test
    public void test1() {
        String s = "abba";
        printAnagram(s.toCharArray());
    }

    @Test
    public void test2() {
        String s = "ifailuhkqq";
        printAnagram(s.toCharArray());
    }

    public void printAnagram(char[] a) {
        int length = a.length;
        printAnagram(a, length, length);
    }

    private void printAnagram(char[] a, int max, int n) {
        if (max == 1) {
            System.out.println(Arrays.toString(a));
        }
        for (int i = -1; i < max - 1; i++) {
            if (i != -1) {
                a[i] ^= a[max - 1] ^= a[i] ^= a[max - 1];
            }
            printAnagram(a, max - 1, n);

            if (i != -1) {
                a[i] ^= a[max - 1] ^= a[i] ^= a[max - 1];
            }
        }
    }
}
