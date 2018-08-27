package test.strings;

public class LongestPalindromicSubstring {
    public static void main(String... star) {
        String s1 = "babad";
        String s2 = "cbbd";
        String s3 = "bb";
        String s4 = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
        long begin = System.currentTimeMillis();
        String s = longestPalindrome2(s4);
        System.out.println("s = " + s);
        long end = System.currentTimeMillis();
        System.out.println("time for first algorithm = " + (end - begin));

        begin = System.currentTimeMillis();
        String s5 = longestPalindrome2(s4);
        System.out.println("s5 = " + s5);
        end = System.currentTimeMillis();
        System.out.println("time for first algorithm = " + (end - begin));
    }

    /*public static String longestPalindromeRecursion(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        String palindrome = "";
        char[] chars = s.toCharArray();
        for (int i = 1; i < length; i++) {
            if !checkRecOdd() thene checkOdd

        }
    }

    private static int[] checkRecOdd(char[] chars, int middle, int left, int right) {
        char middleChar = chars[middle];
        char leftChar = chars[left];
        char rightChar = chars[right];
        if (leftChar == rightChar)
    }

    private static int[] checkRecEven(char[] chars, int middle, int left, int right) {

    }*/

    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        String palindrome = "";
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                if (j - i <= palindrome.length()) {
                    continue;
                }
                String substring = s.substring(i, j);
                String original = s.replace(substring, "");
                String reverseSubString = new StringBuilder(substring).reverse().toString();
                String newString = s.replace(reverseSubString, "");
                if (original.equals(newString) && substring.length() > palindrome.length()) {
                    palindrome = substring;
                }
            }
        }
        return palindrome;
    }

    public static String longestPalindrome2(String s) {
        int length = s.length();
        if (length == 1) {
            return s;
        }
        String palindrome = "";
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length + 1; j++) {
                int substringLength = j - i;
                if (substringLength <= palindrome.length()) {
                    continue;
                }
                String substring = s.substring(i, j);
                if (substring.charAt(0) != substring.charAt(substringLength - 1)) {
                    continue;
                }
                String reverseSubString = new StringBuilder(substring).reverse().toString();
                if (substring.equals(reverseSubString) && substring.length() > palindrome.length()) {
                    palindrome = substring;
                }
            }
        }
        return palindrome;
    }
}
