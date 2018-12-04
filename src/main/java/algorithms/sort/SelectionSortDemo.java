package algorithms.sort;

import java.util.Arrays;

/**
 * Worst case Time Complexity - O(n*n)
 * Best case Time Complexity - O(n*n)
 * Average case - O(n*n)
 * Space complexity - O(1)
 * Stable sorting - no
 */
public class SelectionSortDemo {
    public static void main(String... arasr) {
        int[] arr = new int[] {2,5,7,3,6,3,8,5,9,8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] sort(int[] arr) {
        int position = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    position = j;
                }
            }
            int temp = arr[position];
            arr[position] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
