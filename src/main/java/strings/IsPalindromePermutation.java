package strings;

import org.junit.Assert;
import org.junit.Test;

public class IsPalindromePermutation {

    @Test
    public void testTable() {
        String test = "aabbaacd";
        int[] table = CharHelper.buildCharFrequencyTable(test);
        int[] compare = new int[] {4, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        Assert.assertArrayEquals(compare, table);
    }

    @Test
    public void test() {
        String pali = "Rats live on no evil star";
        boolean palindrome = isPalindrome(pali);
        Assert.assertTrue(palindrome);
        boolean permutationOfPalindrome = isPermutationOfPalindrome(pali);
        Assert.assertTrue(permutationOfPalindrome);
    }

    public boolean isPalindrome(String s) {
        int[] table = CharHelper.buildCharFrequencyTable(s);
        boolean wasOdd = false;
        for (int i : table) {
            if (i % 2 != 0) {
                if (wasOdd) {
                    return false;
                }
                wasOdd = true;
            }
        }
        return true;
    }

    private static class CharHelper {
        static int getCharNumber(Character character) {
            int a = Character.getNumericValue('a');
            int z = Character.getNumericValue('z');
            int n = Character.getNumericValue(character);
            if (a <= n && n <= z) {
                return n - a;
            }
            return -1;
        }

        static int[] buildCharFrequencyTable(String s) {
            String s1 = s.toLowerCase();
            int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
            for (char c : s1.toCharArray()) {
                int charNumber = getCharNumber(c);
                if (charNumber != -1) {
                    table[charNumber]++;
                }
            }
            return table;
        }
    }

    /* Toggle the ith bit in the integer. */
    public static int toggle(int bitVector, int index) {
        if (index < 0) return bitVector;

        int mask = 1 << index;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    /* Create bit vector for string. For each letter with value i,
     * toggle the ith bit. */
    public static int createBitVector(String phrase) {
        int bitVector = 0;
        for (char c : phrase.toCharArray()) {
            int x = CharHelper.getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    /* Check that at most one bit is set by subtracting one from the
     * integer and ANDing it with the original integer. */
    public static boolean checkAtMostOneBitSet(int bitVector) {
        return (bitVector & (bitVector - 1)) == 0;
    }

    public static boolean isPermutationOfPalindrome(String phrase) {
        int bitVector = createBitVector(phrase);
        return checkAtMostOneBitSet(bitVector);
    }
}
