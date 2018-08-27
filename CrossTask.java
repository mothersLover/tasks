package test.tasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrossTask {

    public static void main(String... args) {
        String[] strings = new String[]{"BGBBGB", "GGGGGG", "BGBBGB", "GGGGGG", "BGBBGB", "BGBBGB"};
        String[] strings1 = new String[]{"GBGBGGB", "GBGBGGB", "GBGBGGB", "GGGGGGG", "GGGGGGG", "GBGBGGB", "GBGBGGB"};
        String[] strings2 = new String[]{"GGGGGGGG", "GBGBGGBG", "GBGBGGBG", "GGGGGGGG", "GBGBGGBG", "GGGGGGGG", "GBGBGGBG", "GGGGGGGG"};
        String[] strings3 = new String[]{"GGGGGGGGG", "GBBBGGBGG", "GBBBGGBGG", "GBBBGGBGG", "GBBBGGBGG", "GBBBGGBGG", "GBBBGGBGG", "GGGGGGGGG"};
        String[] strings4 = new String[]{"GGGGGGGGGGGG", "GBGGBBBBBBBG", "GBGGBBBBBBBG", "GGGGGGGGGGGG", "GGGGGGGGGGGG", "GGGGGGGGGGGG", "GGGGGGGGGGGG", "GBGGBBBBBBBG", "GBGGBBBBBBBG", "GBGGBBBBBBBG", "GGGGGGGGGGGG", "GBGGBBBBBBBG"};
        String[] strings5 = new String[]{"GGGGGGGGGGGGGG", "GGGGGGGGGGGGGG", "GGBGBGGGBGBGBG", "GGBGBGGGBGBGBG", "GGGGGGGGGGGGGG", "GGGGGGGGGGGGGG", "GGGGGGGGGGGGGG", "GGGGGGGGGGGGGG", "GGBGBGGGBGBGBG", "GGBGBGGGBGBGBG", "GGBGBGGGBGBGBG", "GGBGBGGGBGBGBG"};
        twoPluses(strings5);
    }

    private static class Cross implements Comparable<Cross>{
        int units;
        int i;
        int j;

        public Cross(int units, int i, int j) {
            this.units = units;
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Cross o) {
            return -1 * Integer.compare(this.units, o.units);
        }
    }

    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {
        int columns = grid[0].length();
        int rows = grid.length;
        char good = 'G';
        char[][] chars = new char[rows][columns];
        for (int i = 0; i < rows; i++) {
            String s1 = grid[i];
            chars[i] = s1.toCharArray();;
        }

        List<Cross> crosses = new ArrayList<>();
        List<Integer> results = new ArrayList<>();


        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < columns - 1; j++) {
                char c = chars[i][j];
                char u = chars[i - 1][j];
                char d = chars[i + 1][j];
                char l = chars[i][j - 1];
                char r = chars[i][j + 1];
                if (c == good && u == good && d == good && l == good && r == good) {
                    int units = 5;
                    Cross cross = new Cross(units, i, j);
                    crosses.add(cross);
                    boolean flag = true;
                    int k = 2;
                    while (flag) {
                        int result = checkMore2(i, j, k, units, rows, columns, chars);
                        if (result > units) {
                            units = result;
                            Cross cross1 = new Cross(units, i, j);
                            crosses.add(cross1);
                            k++;
                        } else {
                            flag = false;
                        }
                    }
                }
            }
        }

        Collections.sort(crosses);

        if (crosses.isEmpty()) {
            return 1;
        }

        if (crosses.size() == 1) {
            return crosses.get(0).units;
        }

        results.add(crosses.get(0).units);
        for(int i = 0; i < crosses.size() - 1; i++) {
            Cross first = crosses.get(i);

            for (int j = i + 1; j < crosses.size(); j++) {
                Cross second = crosses.get(j);


                boolean checkCross = checkCross(first, second);
                if (!checkCross) {
                    int result = crosses.get(i).units * crosses.get(j).units;
                    results.add(result);
                }
            }
        }

        Collections.sort(results);
        return results.get(results.size() - 1);
    }

    private static boolean checkCross(Cross first, Cross second) {
        int l1 = (first.units - 1) / 4;
        int l2 = (second.units - 1) / 4;

        int[][] i1 = new int[l1 * 2 + 1][2];
        int[][] j1 = new int[l1 * 2 + 1][2];
        int[][] i2 = new int[l2 * 2 + 1][2];
        int[][] j2 = new int[l2 * 2 + 1][2];
        for (int i = first.i - l1, j = 0; i <= first.i + l1; i++, j++) {
            i1[j][0] = i;
            i1[j][1] = first.j;
        }
        for (int i = second.i - l2, j = 0; i <= second.i + l2; i++, j++) {
            i2[j][0] = i;
            i2[j][1] = second.j;
        }
        for (int j = first.j - l1, i = 0; j <= first.j + l1; i++, j++) {
            j1[i][0] = first.i;
            j1[i][1] = j;
        }
        for (int j = second.j - l2, i = 0; j <= second.j + l2; i++, j++) {
            j2[i][0] = second.i;
            j2[i][1] = j;
        }
        boolean c1 = crossArrays(i1, i2);
        boolean c2 = crossArrays(j1, j2);
        boolean c3 = crossArrays(i1, j2);
        boolean c4 = crossArrays(j1, i2);
        return c1 || c2 || c3 || c4;
    }

    private static boolean crossArrays2(int[][] i1, int[][] i2) {
        for (int i = 0, j = 0, k = 0; k < i1.length + i2.length; k++) {
            if (j > i2.length -1 || i > i1.length - 1) {
                return false;
            }
            int fi = i1[i][0];
            int fj = i1[i][1];
            int si = i2[j][0];
            int sj = i2[j][1];
            if (si == fi && sj == fj) {
                return true;
            }
            if (si == fi) {
                i++;
            }
            if (si > fi) {
                i++;
            }
            if (si < fi) {
                j++;
            }
        }
        return false;
    }

    private static boolean crossArrays(int[][] i1, int[][] i2) {
        for (int[] anI1 : i1) {
            for (int[] anI2 : i2) {
                int fi = anI1[0];
                int fj = anI1[1];
                int si = anI2[0];
                int sj = anI2[1];
                if (si == fi && sj == fj) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int checkMore2(int i, int j, int k, int units, int rows, int columns, char[][] chars) {
        char good = 'G';
        if (i + k < rows && i - k >= 0 && j + k < columns && j - k >= 0) {
            char u = chars[i - k][j];
            char d = chars[i + k][j];
            char l = chars[i][j - k];
            char r = chars[i][j + k];
            if (u == good && d == good && l == good && r == good) {
                units += 4;
                return units;
            }
        }
        return units;
    }

    private static int checkMore(int i, int j, int k, int units, int rows, int columns, char[][] chars) {
        char good = 'G';
        if (i + k < rows && i - k >= 0 && j + k < columns && j - k >= 0) {
            char u = chars[i - k][j];
            char d = chars[i + k][j];
            char l = chars[i][j - k];
            char r = chars[i][j + k];
            if (u == good && d == good && l == good && r == good) {
                units += 4;
                return checkMore(i, j, ++k, units, rows, columns, chars);
            }
        }
        return units;
    }
}
