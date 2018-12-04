package datastructures.arrays;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class MinimumSwaps2 {

    @Test
    public void test1() {
        int[] ints1 = new int[] {2,3,4,1,5};
        Assert.assertEquals(3, minimumSwaps(ints1));
    }

    @Test
    public void test2() {
        int[] ints2 = new int[] {1,3,5,2,4,6,8};
        Assert.assertEquals(3, minimumSwaps(ints2));
    }

    @Test
    public void test3() {
        int[] ints3 = new int[] {2, 4, 1, 3, 5};
        Assert.assertEquals(3, minimumSwaps(ints3));
    }

    @Test
    public void test4() {
        int[] ints4 = new int[] {7, 1, 3, 2, 4, 5, 6};
        Assert.assertEquals(5, minimumSwaps(ints4));
    }

    @Test
    public void test5() {
        int[] ints5 = new int[] {3, 7, 6, 9, 1, 8, 10, 4, 2, 5};
        Assert.assertEquals(9, minimumSwaps(ints5));
    }

    static int minimumSwaps(int[] arr) {
//        return quickSort2(arr, 0, arr.length - 1);
        int[] p = new int[arr.length];
        System.arraycopy(arr, 0, p, 0, arr.length);
        Arrays.sort(p);
        return followSwaps(arr, 0, 0, p);
    }

    static int followSwaps(int[] arr, int cur, int inversions, int[] sortArray) {
        int swaps = inversions;
        if (cur >= arr.length) {
            return swaps;
        }
        int curVal = arr[cur];
        if (curVal != sortArray[cur] && curVal != cur + 1) {
            swaps++;
            swap(arr, cur, curVal - 1);
        } else {
            cur++;
        }
        return followSwaps(arr, cur, swaps, sortArray);
    }

    static int mergeSort(int[] a, int begin, int end) {
        if (begin >= end) {
            return 0;
        }
        int middle = begin + (end - begin) / 2;
        int swaps = 0;
        swaps += mergeSort(a, begin, middle);
        swaps += mergeSort(a, middle + 1, end);
        swaps += mergeArrays(a, begin, middle, end);
        return swaps;
    }

    static int mergeArrays(int[] a, int begin, int middle, int end) {
        int i = begin;
        int j = middle + 1;
        int k = begin;
        int inversions = 0;
        int[] temp = new int[a.length];
        while (i <= middle && j <= end) {
            if (a[i] <= a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
                inversions += (middle + 1 - i);
            }
        }

        while (i <= middle) {
            temp[k++] = a[i++];
        }

        while (j <= end) {
            temp[k++] = a[j++];
        }

        System.arraycopy(temp, begin, a, begin, end - begin + 1);

        return inversions;
    }

    static int quickSort(int[] a, int begin, int end) {
        int inversions = 0;
        int[] res = findMiddle(a, begin, end);
        int position = res[0];
        inversions += res[1];
        if (begin < position - 1) {
            inversions += quickSort(a, begin, position - 1);
        }
        if (position < end) {
            inversions += quickSort(a, position, end);
        }
        return inversions;
    }

    private static int[] findMiddle(int[] a, int begin, int end) {
        int i = begin, j = end;
        int tmp;
        int inversions = 0;
        int pivot = a[begin + (end - begin) / 2];
        while(i <=j) {
            while(a[i] < pivot) {
                i++;
            }
            while (a[j] > pivot) {
                j--;
            }
            if (i <= j) {
                if (a[i] > a[j]) {
                    inversions++;
                }
                swap(a, i, j);
                i++;
                j--;
            }
        }
        return new int[] {i, inversions};
    }

    static int quickSort2(int[] a, int begin, int end) {
        int inversions = 0;
        if (end <= begin) {
            return inversions;
        }
        int pivot = a[begin];
        int start = begin;
        int stop = end;
        while (begin < end) {
            while(a[begin] <= pivot && begin < end) {
                begin++;
            }

            while (a[end] > pivot && begin <= end) {
                end--;
            }

            if (begin < end) {
                if (a[begin] > a[end]) {
                    inversions++;
                }
                swap(a, end, begin);
            }
        }

        if (a[start] > a[end]) {
            inversions++;
        }
        swap(a, end, start);

        inversions += quickSort2(a, start, end - 1);
        inversions += quickSort2(a, end + 1, stop);

        return inversions;
    }

    private static void swap(int[] a, int end, int start) {
        int tmp = a[start];
        a[start] = a[end];
        a[end] = tmp;
    }
}
