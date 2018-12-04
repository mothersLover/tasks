package algorithms.sort;

import java.util.Arrays;

/**
 * Worst case performance - O(n + k)
 * Average case performance - O(n + k)
 * Worst case Space Complexity - O(k)
 */
public class BucketSortDemo {

    public static void main(String... asd) {
        int[] arr = new int[] {28, 23,23,21,22,25,25,26,22,28,27,22,24,25,23,24,27,25,24,26,28,23,29,30};
        int[] sort = sort(arr, 20, 30);
        System.out.println(Arrays.toString(sort));

    }

    static int[] sort(int[] arr, int minValue, int maxValue) {
        int[] count = new int[maxValue - minValue + 1];
        for (int anArr : arr) {
            count[anArr - minValue]++;
        }
        int j = 0;
        for (int i = 0; i < count.length; i++) {
            int number = count[i];
            while (number > 0) {
                arr[j] = minValue + i;
                j++;
                number--;
            }
        }
        return arr;
    }
}
