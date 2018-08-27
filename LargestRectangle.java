package test.datastructures.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class LargestRectangle {

    public static void main(String... asda) {
        int[] h = new int[] {11,11,10,10,10};
        int[] h1 = new int[] {1,2,3,4,5};
        int[] h2 = new int[] {6,5,4,3,2,1};
        int[] h3 = new int[] {6,5,4,3,3,1,5,3,2};
        int[] h4 = new int[] {1,2,3,4,3,2,1};
        long l1 = largestRectangleStack(h3);
        System.out.println("stack solution = " + l1);
        long l2 = divideAndConquer(h3, 0, h3.length - 1);
        System.out.println("divide and conquer solution = " + l2);
    }

    static long largestRectangle(int[] h) {
        long maxSum = 0;
        long curSum;
        long minSum;
        for (int i = 1; i < h.length; i++) {
            minSum = h[i];
            for (int j = i - 1; j >=0; j--) {
                minSum = Math.min(minSum, h[j]);
                curSum = minSum * (i - j + 1);
                maxSum = Math.max(maxSum, curSum);
            }
        }
        return maxSum;
    }

    static long largestRectangleStack(int[] h) {
        int size = h.length;
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        int maxArea = 0;
        int top = 0;
        int topArea = 0;
        int i = 0;
        while (i < size) {
            while ((i < size) && (stk.isEmpty() || h[stk.peekLast()] <= h[i])) {
                stk.addLast(i);
                i++;
            }
            while (!stk.isEmpty() && (i == size || h[stk.peekLast()] > h[i])) {
                top = stk.pollLast();
                topArea = h[top] * (stk.isEmpty() ? i : i - stk.peekLast() - 1);
                maxArea = Math.max(maxArea, topArea);
            }
        }
        return maxArea;
    }

    static long largestRectangle2(int[] h) {
        Arrays.sort(h);
        long maxSum = 0;
        long curSum;
        long minSum;
        minSum = h[h.length - 1];
        for (int j = h.length - 1; j >= 0; j--) {
            minSum = Math.min(minSum, h[j]);
            curSum = minSum * (h.length - j);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }

    static long divideAndConquer(int[] h, int start, int end) {
        if (end - start <= 1) {
            int min = Math.min(h[start],h[end]);
            return min * (end - start + 1);
        }
        int pivot = findMinElement(h, start, end);
        long allSum = h[pivot] * (end - start + 1);
        long leftSum = 0;
        if (pivot - 1 >= start) {
            leftSum = divideAndConquer(h, start, pivot - 1);
        }
        long rightSum = 0;
        if (pivot + 1 <= end) {
            rightSum = divideAndConquer(h, pivot + 1, end);
        }
        return Math.max(allSum, Math.max(leftSum, rightSum));
    }

    private static int findMinElement(int[] h, int start, int end) {
        int min = Integer.MAX_VALUE;
        int pos = start;
        for (int i = start; i <= end; i++) {
            if (h[i] < min) {
                min = h[i];
                pos = i;
            }
        }
        return pos;
    }
}
