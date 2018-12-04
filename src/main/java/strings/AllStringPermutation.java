package strings;

import org.junit.Test;

public class AllStringPermutation {

    void permutation(String string) {
        permutation(string, "");
    }

    void permutation(String s, String prefix) {
        if (s.length() == 0) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < s.length(); i++) {
                String rem = s.substring(0, i) + s.substring(i + 1);
                permutation(rem, prefix + s.charAt(i));
            }
        }
    }

    @Test
    public void test() {
        permutation("abcd");
    }
}
