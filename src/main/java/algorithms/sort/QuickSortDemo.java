package algorithms.sort;

import java.util.Random;

/**
 * Worst case Time Complexity - O(n*n)
 * Best case Time Complexity - O(n*logn)
 * Average case - O(n*logn)
 * Space complexity - O(n*logn) -?
 * Stable sorting - no
 */
public class QuickSortDemo {

    public static void main(String... args) {
        Random random = new Random();
        int count = 10;
        int[] a = new int[count];
        for (int i = 0; i < count; i++) {
            int n = random.nextInt(count);
            a[i] = n;
        }
        int[] a1 = new int[]{1,6,5,2,4,3};
        int[] ints1 = new int[] {2,3,4,1,5};
        quickSort(ints1, 0, ints1.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        int pivot = partition(arr, left, right);
        if (left < pivot - 1) {
            quickSort(arr, left, pivot - 1);
        }
        if (pivot < right) {
            quickSort(arr, pivot, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int i = left, j = right;
        int tmp;
        int pivot = arr[(left + right) >>> 1];
        while(i <=j) {
            while(arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }
}
