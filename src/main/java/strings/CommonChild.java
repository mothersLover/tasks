package strings;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CommonChild {

    @Test
    public void test1() {
        String s1 = "SHINCHAN";
        String s2 = "NOHARAAA";
        Assert.assertEquals(3, commonChild(s1, s2));
    }

    @Test
    public void test2() {
        String s1 = "ABCD";
        String s2 = "ABDC";
        Assert.assertEquals(3, commonChild(s1, s2));
    }

    @Test
    public void test3() {
        String s1 = "HARRY";
        String s2 = "SALLY";
        Assert.assertEquals(2, commonChild(s1, s2));
    }

    @Test
    public void test4() {
        String s1 = "AA";
        String s2 = "BB";
        Assert.assertEquals(0, commonChild(s1, s2));
    }

    @Test
    public void test5() {
        String s1 = "ABCDEF";
        String s2 = "FBDAMN";
        Assert.assertEquals(2, commonChild(s1, s2));
    }

    @Test
    public void test6() {
        String s1 = "OUDFRMYMAW";
        String s2 = "AWHYFCCMQX";
        Assert.assertEquals(2, commonChild(s1, s2));
    }

    @Test
    public void test7() {
        String s1 = "WEWOUCUIDGCGTRMEZEPXZFEJWISRSBBSYXAYDFEJJDLEBVHHKS";
        String s2 = "FDAGCXGKCTKWNECHMRXZWMLRYUCOCZHJRRJBOAJOQJZZVUYXIC";
        Assert.assertEquals(15, commonChild(s1, s2));
    }

    static int commonChild(String s1, String s2) {
        return child(s1, s2);
    }

    public static int child(String str1, String str2) {
        int result[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i <= str1.length(); i++) {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0 || j == 0)
                    result[i][j] = 0;
                else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    result[i][j] = result[i - 1][j - 1] + 1;
                } else {
                    result[i][j] = Math.max(result[i - 1][j], result[i][j - 1]);
                }
            }
        }
        return result[str1.length()][str2.length()];
    }

    static int commonChild2(String s1, String s2) {
        int[] count1 = new int[255];
        int[] count2 = new int[255];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            count1[c]++;
            c = s2.charAt(i);
            count2[c]++;
        }
        for (int i = 0; i < 255; i++) {
            if ((count1[i] > 0 && count2[i] == 0) || (count1[i] == 0 && count2[i] > 0)
                    || (count1[i] == 0 && count2[i] == 0)) {
                count1[i] = 0;
                count2[i] = 0;
            }
        }

        int sum1 = Arrays.stream(count1).sum();
        int sum2 = Arrays.stream(count2).sum();
        if (sum1 == 0 || sum2 == 0) {
            return 0;
        }

        String firstString, secondString;
        int[] resCount1, resCount2;
        if (sum1 < sum2) {
            firstString = s1;
            secondString = s2;
            resCount1 = count1;
            resCount2 = count2;
        } else {
            firstString = s2;
            secondString = s1;
            resCount1 = count2;
            resCount2 = count1;
        }

        Map<Character, Integer> repetitions = new HashMap<>();
        ArrayList<Integer> integers = new ArrayList<>();

        for (int i = 0; i < firstString.length(); i++) {
            char c = firstString.charAt(i);
            int indexInSecondString = secondString.indexOf(c);
            if (resCount1[c] == 0) {
                continue;
            }
            if (resCount2[c] > 1) {
                Integer curIndex = repetitions.get(c);
                if (curIndex == null) {
                    repetitions.put(c, indexInSecondString);
                } else {
                    indexInSecondString = s2.indexOf(c, curIndex);
                    repetitions.put(c, ++curIndex);
                }
            }
            integers.add(indexInSecondString);
        }

        int max = 0;
        int candidate;
        for (int i = 0; i < integers.size(); i++) {
            int curMax = 1;
            candidate = integers.get(i);
            for (int j = i + 1; j < integers.size(); j++) {
                if (integers.get(j) > candidate) {
                    curMax++;
                    candidate = integers.get(j);
                    max = Math.max(max, curMax);
                }
            }
        }

        return max;
    }
}
