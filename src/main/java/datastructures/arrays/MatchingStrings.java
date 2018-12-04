package datastructures.arrays;

import java.util.HashMap;
import java.util.Map;

public class MatchingStrings {

    public static void main(String... args) {
        String[] s1 = new String[]{"aba","baba","aba","xzxb"};
        String[] s2 = new String[]{"aba","xzxb","ab"};
        matchingStrings(s1, s2);
    }

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {
        Map<String, Integer> map = new HashMap<>(strings.length);
        int[] i = new int[queries.length];
        for (String s : strings) {
            if (map.containsKey(s)) {
                Integer integer = map.get(s);
                map.put(s, ++integer);
            } else {
                map.put(s, 1);
            }
        }

        int count = 0;

        for (String s : queries) {
            Integer integer = map.get(s);
            if (integer == null) {
                i[count] = 0;
            } else {
                i[count] = integer;
            }
            count++;
        }

        return i;
    }
}
