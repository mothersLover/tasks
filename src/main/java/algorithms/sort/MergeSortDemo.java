package algorithms.sort;

/**
 * Worst case Time Complexity - O(n*logn)
 * Best case Time Complexity - O(n*logn)
 * Average case - O(n*logn)
 * Space complexity - O(n)
 * Stable sorting - yes
 *
 * It requires the equal amount of additional space for tmp array (as the unsorted list). Hence, it is not ar all recommended for
 * sorting large unsorted lists
 *
 * It is the best Sorting technique for sorting Linked Lists
 */
public class MergeSortDemo {
    public static void main(String... args) {
        int[] arr = new int[]{9,2,5,7,6};
        mergeSort(arr, 0, arr.length -1);
        int middle = (arr.length - 1) >>> 1;
        merge(arr, 0, middle,arr.length -1);

        System.out.println();
    }

    public static void mergeSort(int[] arr, int from, int to) {
        if (from < to) {
            int middle = (to + from) >>> 1;
            mergeSort(arr, from, middle);
            mergeSort(arr, middle + 1, to);
            merge(arr, from, middle, to);
        }

    }

    private static void merge(int[] arr, int first, int middle, int last) {
        int[] tmp = new int[arr.length];
        int left1 = first;
        int left2 = middle + 1;

        int next = left1;
        while ((left1 <= middle) && (left2 <= last)) {
            if (arr[left1] < arr[left2]) {
                tmp[next] = arr[left1];
                left1++;
            } else {
                tmp[next] = arr[left2];
                left2++;
            }
            next++;
        }

        for (; left1 <= middle; next++, left1++) {
            tmp[next] = arr[left1];
        }

        for (; left2 <= last; next++, left2++) {
            tmp[next] = arr[left2];
        }

        System.arraycopy(tmp, first, arr, first, last - first + 1);
    }
}
