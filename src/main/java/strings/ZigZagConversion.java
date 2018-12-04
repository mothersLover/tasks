package strings;

public class ZigZagConversion {

    public static void main(String... aas) {
        String s1 = "PAYPALISHIRING";
        String convert = convert2(s1, 3);
        System.out.println("convert = " + convert);
    }

    public static String convert(String s, int numRows) {
        if (s == null || "".equals(s)) {
            return "";
        }
        if (numRows <= 1) {
            return s;
        }
        boolean down = true;
        int i = 0;
        int j = 0;
        int step = 0;
        int k = numRows * 2 - 2;
        int columns = (s.length() / k + 1) * (numRows - 1);
        char [][] arr = new char [numRows][columns];
        char[] chars = s.toCharArray();
        while (step < s.length()) {
            arr[i][j] = chars[step];
            step++;
            if (i == 0) {
                down = true;
            }
            if (i == numRows - 1) {
                down = false;
            }
            if (down == true) {
                i++;
            }
            if (down == false) {
                i--;
                j++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int q = 0; q < numRows; q++) {
            for (int w = 0; w < columns; w++) {
                stringBuilder.append(arr[q][w] == 0 ? "" : arr[q][w]);
            }
        }
        return stringBuilder.toString();
    }

    public static String convert2(String s, int numRows) {
        if (s == null || "".equals(s)) {
            return "";
        }
        if (numRows <= 1) {
            return s;
        }
        StringBuilder[] builders = new StringBuilder[numRows];
        boolean down = true;
        int i = 0;
        int step = 0;
        while (step < s.length()) {
            StringBuilder builder = builders[i];
            if (builder == null) {
                builders[i] = new StringBuilder();
                builder = builders[i];
            }
            builder.append(s.charAt(step));
            step++;
            if (i == 0) {
                down = true;
            } else if (i == numRows - 1) {
                down = false;
            }
            if (down == true) {
                i++;
                continue;
            }
            i--;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int q = 0; q < numRows; q++) {
            stringBuilder.append(builders[q].toString());
        }
        return stringBuilder.toString();
    }
}
