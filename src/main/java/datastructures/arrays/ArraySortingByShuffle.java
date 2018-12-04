package datastructures.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraySortingByShuffle {

    public static void main(String... args) {
        int[] i1 = new int[]{1, 6, 5, 2, 4, 3};
        int[] i2 = new int[]{4, 2};
        int[] i3 = new int[]{3, 1, 2};
        int[] i4 = new int[]{1, 5, 4 ,3 ,2, 6};
        String s ="4104 8529 49984 54956 63034 82534 84473 86411 92941 95929 108831 894947 125082 137123 137276 142534 149840 154703 174744 180537 207563 221088 223069 231982 249517 252211 255192 260283 261543 262406 270616 274600 274709 283838 289532 295589 310856 314991 322201 339198 343271 383392 385869 389367 403468 441925 444543 454300 455366 469896 478627 479055 484516 499114 512738 543943 552836 560153 578730 579688 591631 594436 606033 613146 621500 627475 631582 643754 658309 666435 667186 671190 674741 685292 702340 705383 722375 722776 726812 748441 790023 795574 797416 813164 813248 827778 839998 843708 851728 857147 860454 861956 864994 868755 116375 911042 912634 914500 920825 979477";
        String[] split = s.split(" ");
        List<String> strings = Arrays.asList(split);
        int[] a = new int[strings.size()];
        int i = 0;
        for (String s1 : strings) {
            a[i] = Integer.parseInt(s1);
            i++;
        }
//        String s = larrysArray(i2);
//        System.out.println("s = " + s);
        almostSorted(a);
    }

    // Complete the larrysArray function below.
    static String larrysArray(int[] A) {
        int currentValue = 1;
        for (int j = 0; currentValue <= A.length;) {
            for (int i = currentValue - 1; i < A.length; i++) {
                int d = A[i];
                if (d == currentValue && i == currentValue - 1) {
                    currentValue++;
                    break;
                }
                if (d == currentValue) {
                    boolean shift = shift(d, i, currentValue - 1, A);
                    if (!shift) {
                        return "NO";
                    }
                    currentValue++;
                    break;
                }
            }
        }
        return "YES";
    }

    private static boolean shift(int number, int indexFrom, int indexTo, int[] a) {
        int count = indexFrom - indexTo;
        if (count == 1 && number == a.length - 1) {
            return false;
        }
        int left, middle, right;
        if (indexFrom == indexTo) {
            return true;
        }
        if (indexFrom == a.length - 1) {
            left = a[indexFrom - 1];
            middle = a[indexFrom];
            right = a[indexFrom - 2];
            a[indexFrom - 2] = left;
            a[indexFrom - 1] = middle;
            a[indexFrom] = right;
        } else {
            left = a[indexFrom];
            middle = a[indexFrom + 1];
            right = a[indexFrom - 1];
            a[indexFrom - 1] = left;
            a[indexFrom] = middle;
            a[indexFrom + 1] = right;
        }
        return shift(number, indexFrom - 1, indexTo, a);
    }

    // Complete the almostSorted function below.
    static void almostSorted(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int pos = i;
            int current = arr[i];
            for (int j = i; j < arr.length; j++) {
                if (current > arr[j]) {
                    current = arr[j];
                    pos = j;
                }
            }
            if (pos != i) {
                move(arr, pos, i);
                return;
            }
        }

    }

    private static void move(int[] arr, int from, int to) {
        int length = from - to;
        if (length == 1 && arr.length == 2) {
            System.out.println("yes");
            System.out.println("swap " + (to + 1) + " " + (from + 1));
        } else if (length == 1 && arr.length > 2) {
            int tmp = arr[to];
            arr[to] = arr[from];
            arr[from] = tmp;
            if (checkArray(arr, to, from)) {
                System.out.println("yes");
                System.out.println("swap " + (to + 1) + " " + (from + 1));
            }else {
                System.out.println("no");
            }
        } else if (arr[from - 1] < arr[from - 2] && arr[to + 1] > arr[to + 2]) {
            int middle = (from + 1 - to) >>> 1;
            for (int i = 0; i < middle; i++) {
                int tmp = arr[to + i];
                arr[to + i] = arr[from - i];
                arr[from - i] = tmp;
            }
            if (checkArray(arr, to, from)) {
                System.out.println("yes");
                System.out.println("reverse " + (to + 1) + " " + (from + 1));
            }else {
                System.out.println("no");
            }
        } else {
            int tmp = arr[to];
            arr[to] = arr[from];
            arr[from] = tmp;
            if (checkArray(arr, to, from)) {
                System.out.println("yes");
                System.out.println("swap " + (to + 1) + " " + (from + 1));
            }else {
                System.out.println("no");
            }
        }
    }

    private static boolean checkArray(int[] arr, int to, int from) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
}
