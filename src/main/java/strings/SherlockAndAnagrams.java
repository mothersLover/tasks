package strings;

import org.junit.Assert;
import org.junit.Test;
import test.algorithms.Factorial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SherlockAndAnagrams {

    @Test
    public void testSumMethod() {
        int sum = sum(3);
        Assert.assertEquals(6, sum);

        sum = sum(4);
        Assert.assertEquals(10, sum);

        sum = sum(10);
        Assert.assertEquals(55, sum);
    }

    @Test
    public void test1() {
        String s = "ifailuhkqq";
        Assert.assertEquals(3, sherlockAndAnagrams(s));
    }

    @Test
    public void test2() {
        String s = "kkkk";
        Assert.assertEquals(10, sherlockAndAnagrams(s));
    }

    @Test
    public void test3() {
        String s = "abba";
        Assert.assertEquals(4, sherlockAndAnagrams(s));
    }

    @Test
    public void test4() {
        String s = "abcd";
        Assert.assertEquals(0, sherlockAndAnagrams(s));
    }

    @Test
    public void test5() {
        String s = "ifailuhkqqhucpoltgtyovarjsnrbfpvmupwjjjfiwwhrlkpekxxnebfrwibylcvkfealgonjkzwlyfhhkefuvgndgdnbelgruel";
        Assert.assertEquals(399, sherlockAndAnagrams(s));
    }

    @Test
    public void test6() {
        String s = "gffryqktmwocejbxfidpjfgrrkpowoxwggxaknmltjcpazgtnakcfcogzatyskqjyorcftwxjrtgayvllutrjxpbzggjxbmxpnde";
        Assert.assertEquals(471, sherlockAndAnagrams(s));
    }

    @Test
    public void test7() {
        String s = "mqmtjwxaaaxklheghvqcyhaaegtlyntxmoluqlzvuzgkwhkkfpwarkckansgabfclzgnumdrojexnrdunivxqjzfbzsodycnsnmw";
        Assert.assertEquals(370, sherlockAndAnagrams(s));
    }

    @Test
    public void test8() {
        String s = "ofeqjnqnxwidhbuxxhfwargwkikjqwyghpsygjxyrarcoacwnhxyqlrviikfuiuotifznqmzpjrxycnqktkryutpqvbgbgthfges";
        Assert.assertEquals(403, sherlockAndAnagrams(s));
    }

    @Test
    public void test9() {
        String s = "zjekimenscyiamnwlpxytkndjsygifmqlqibxxqlauxamfviftquntvkwppxrzuncyenacfivtigvfsadtlytzymuwvpntngkyhw";
        Assert.assertEquals(428, sherlockAndAnagrams(s));
    }

    static int sherlockAndAnagrams(String s) {
        return sherlockAndAnagrams1(s);
    }

    static int sherlockAndAnagrams1(String s) {
        int length = s.length();
        int sum = 0;
        Map<String, Integer> map = new HashMap<>(length * length);
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j < length - i; j++) {
                String substring = s.substring(j, j + i + 1);
                substring = sortString(substring);
                Integer integer = map.get(substring);
                if (integer == null) {
                    integer = 1;
                } else {
                    integer++;
                }
                map.put(substring, integer);
            }
        }

        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            Integer occurrence = stringIntegerEntry.getValue();
            if (occurrence > 1) {
                sum += combinationWithoutRepetitionChoose2(occurrence);
            }
        }

        return sum;
    }

    static int sherlockAndAnagrams2(String s) {
        int length = s.length();
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(length * length / 2);
        for (int i = 0; i <= length / 2; i++) {
            for (int j = 0; j < length - i; j++) {
                CharSequence charSequence = s.subSequence(j, j + 1 + i);
                int subStringSum = charSequence.chars().sum();
                Integer integer = map.get(subStringSum);
                if (integer == null) {
                    integer = 1;
                } else {
                    integer++;
                }
                map.put(subStringSum, integer);
            }
        }

        for (Map.Entry<Integer, Integer> stringIntegerEntry : map.entrySet()) {
            Integer occurrence = stringIntegerEntry.getValue();
            if (occurrence > 1) {
                sum += combinationWithoutRepetitionChoose2(occurrence);
            }
        }

        return sum;
    }

    static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    static int sum(int i) {
        if (i <= 1) {
            return 1;
        }

        return i + sum(i - 1);
    }

    static int combinationWithoutRepetitionChoose2(int n) {
        if (n == 2) {
            return 1;
        }
        if (n < 2) {
            return 0;
        }
        return (n*n - n) / 2;
    }

    static long combinationWithoutRepetition(int n, int k) {
        if (n == k) {
            return 1;
        }
        return Factorial.factorial(n) / (Factorial.factorial(k) * Factorial.factorial(n - k));
    }
}
