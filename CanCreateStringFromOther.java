package test.datastructures.hashtables;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class CanCreateStringFromOther {

    @Test
    public void test1() {
        String[] s1 = new String[] {"give", "me", "one", "grand", "today", "night"};
        String[] s2 = new String[] {"give", "one", "grand", "today"};
        Assert.assertEquals("Yes", checkMagazine(s1, s2));
    }

    @Test
    public void test2() {
        String[] s1 = new String[] {"two", "times", "three", "is", "not", "four"};
        String[] s2 = new String[] {"two", "times", "two", "is", "four"};
        Assert.assertEquals("No", checkMagazine(s1, s2));
    }

    @Test
    public void test3() {
        String[] s1 = new String[] {"ive", "got", "a", "lovely", "bunch", "of", "coconuts"};
        String[] s2 = new String[] {"ive", "got", "some", "coconuts"};
        Assert.assertEquals("No", checkMagazine(s1, s2));
    }

    // Complete the checkMagazine function below.
    static String checkMagazine(String[] magazine, String[] note) {
        return checkMagazineHashMap(magazine, note);
    }

    static String checkMagazineSet(String[] magazine, String[] note) {
        Set<String> strings = new HashSet<>(magazine.length);
        strings.addAll(Arrays.asList(magazine));
        for (String s : note) {
            if (strings.contains(s)) {
                strings.remove(s);
            } else {
                return "No";
            }
        }
        return "Yes";
    }

    static String checkMagazineHashMap(String[] magazine, String[] note) {
        Map<String, Integer> map = new HashMap<>(magazine.length);
        for (String s : magazine) {
            Integer integer = map.get(s);
            Set<Character> chars = new HashSet<>();
            if (integer == null) {
                integer = 1;
            } else {
                integer++;
            }
            map.put(s, integer);
        }
        for (String s : note) {
            Integer integer = map.get(s);
            if (integer == null || integer < 1) {
                return "No";
            } else {
                integer--;
                map.put(s, integer);
            }
        }
        return "Yes";
    }

    static String checkMagazineSort(String[] magazine, String[] note) {
        return "No";
    }
}
