package datastructures.arrays;

import java.util.LinkedHashMap;
import java.util.Map;

public class LeftRotation {

    public static void main(String... args) {
//        int[] ints = leftRotate(a, 4);
//        String s = Arrays.toString(ints);
//        System.out.println("s = " + s);
        String s = "41 73 89 7 10 1 59 58 84 77 77 97 58 1 86 58 26 10 86 51";
        String[] split = s.split(" ");
        int[] a = new int[split.length];
        int i = 0;
        for (String n : split) {
            a[i] = Integer.parseInt(n);
            i++;
        }
//        leftRotateWithoutRepeats(a, 10);
        leftRotate(a, 10);
    }

    static void leftRotate(int[] a, int count) {
        int length = a.length;
        int[] newA = new int[length];
        for (int i = 0; i < count; i++) {
            System.arraycopy(a, 1, newA, 0, length - 1);
            newA[length - 1] = a[0];
            System.arraycopy(newA, 0, a, 0, length);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int n : a) {
            stringBuilder.append(n);
            stringBuilder.append(" ");
        }

        System.out.println(stringBuilder.toString());
    }

    static void leftRotateWithoutRepeats(int[] a, int count) {
        Map<Integer, Integer> map = new LinkedHashMap<>(a.length, 1.0f, true);
        for (int i : a) {
            map.put(i, i);
        }
        if (count != a.length) {

            for (int i : a) {
                if (count == 0) {
                    break;
                }
                map.get(i);
                count--;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            stringBuilder.append(entry.getValue());
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

}
