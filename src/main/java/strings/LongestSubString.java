package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubString {

    public static void main(String... atrds) {
        String s1 = "abcabcbb";
        String s4 = "abcaqxmp";
        String s5 = "abcaa";
        String s2 = "bbbbb";
        String s3 = "pwwkew";
        int i = lengthOfLongestSubstring(s3);
        System.out.println("i = " + i);
        int i1 = lengthOfLongestSubstring2(s3);
        System.out.println("i1 = " + i1);
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            char key = s.charAt(j);
            if (map.containsKey(key)) {
                i = Math.max(map.get(key), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(key, j + 1);
        }
        return ans;
    }

    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int max = 1;
        Set<Character> set = new HashSet<>();
        int curMax = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i; j < chars.length; j++) {
                char aChar = chars[j];
                if (set.contains(aChar)) {
                    set.clear();
                    curMax = 0;
                    break;
                } else {
                    set.add(aChar);
                    curMax++;
                    if (curMax > max) {
                        max = curMax;
                    }
                }
            }
        }
        return max;
    }
}
