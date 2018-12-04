package algorithms.sort;

import java.util.Arrays;

/**
 * Worst case Time Complexity - O(n*n)
 * Best case Time Complexity - O(n)
 * Average case - O(n*n)
 * Space complexity - O(1)
 * Stable sorting - yes
 */
public class InsertionSortDemo {
    public static void main(String... asr) {
        int[] arr = new int[] {2,5,7,3,6,3,8,5,9,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && (arr[j] < arr[j - 1]); j--) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
            }
        }
        return arr;
    }
}
