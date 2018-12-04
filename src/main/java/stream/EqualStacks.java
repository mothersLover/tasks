package stream;

import java.util.Arrays;

public class EqualStacks {

    public static void main(String... asd) {
        int[] h1 = new int[] {3,2,1,1,1};
        int[] h2 = new int[] {4,3,2};
        int[] h3 = new int[] {1,1,4,1};
        equalStacks(h1,h2,h3);
    }

    /*
     * Complete the equalStacks function below.
     */
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * Write your code here.
         */
        int firstSum = Arrays.stream(h1).sum();
        int secondSum = Arrays.stream(h2).sum();
        int thirdSum = Arrays.stream(h3).sum();

        int firstIndex = 0;
        int secondIndex = 0;
        int thirdIndex = 0;
        while (firstSum > 0 && secondSum > 0 && thirdSum > 0) {
            if (firstSum == secondSum && firstSum == thirdSum) {
                return firstSum;
            }
            if (firstSum > secondSum || firstSum > thirdSum) {
                int val = h1[firstIndex];
                firstSum -= val;
                firstIndex++;
                continue;
            }
            if (secondSum > firstSum || secondSum > thirdSum) {
                int val = h2[secondIndex];
                secondSum -= val;
                secondIndex++;
                continue;
            }
            if (thirdSum > firstSum || thirdSum > secondSum) {
                int val = h3[thirdIndex];
                thirdSum -= val;
                thirdIndex++;
            }
        }
        return 0;
    }
}
