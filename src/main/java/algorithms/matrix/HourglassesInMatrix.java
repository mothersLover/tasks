package algorithms.matrix;

import java.util.Collections;
import java.util.PriorityQueue;

public class HourglassesInMatrix {
    public static void main(String... args) {
        int[][] ar = new int[][] {{1, 1, 1, 0, 0, 0}, {0,1,0,0,0,0},{1,1,1,0,0,0},{0,0,2,4,4,0},{0,0,0,2,0,0},{0,0,1,2,4,0}};
        int[][] ar1 = new int[][] {{-9,-9,-9,1,1,1}, {0,-9,0,4,3,2},{-9,-9,-9,1,2,3},{0,0,8,6,6,0},{0,0,0,-2,0,0},{0,0,1,2,4,0}};
        int[][] ar2 = new int[][] {{-1,-1,0,-9,-2,-2}, {-2,-1,-6,-8,-2,-5},{-1,-1,-1,-2,-3,-4},{-1,-9,-2,-4,-4,-5},{-7,-3,-3,-2,-9,-9},{-1,-3,-1,-2,-4,-5}};
        int i = hourglassSum(ar2);
        System.out.println("i = " + i);
    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int rows = arr.length;
        int[] ints = arr[0];
        int columns = ints.length;
        int max = Integer.MIN_VALUE;
        if (rows < 3 || columns < 3) {
            return 0;
        }
        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < columns - 2; j++) {
                int u1 = arr[i][j];
                int u2 = arr[i][j + 1];
                int u3 = arr[i][j + 2];
                int m = arr[i + 1][j + 1];
                int d1 = arr[i + 2][j];
                int d2 = arr[i + 2][j + 1];
                int d3 = arr[i + 2][j + 2];
                int sum = u1 + u2 + u3 + m + d1 + d2 + d3;
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }
}
