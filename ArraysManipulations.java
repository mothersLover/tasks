package test.datastructures.arrays;

import test.concurrent.forkfoinpull.ManipulationArrayTask;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ArraysManipulations {

    public static void main(String... args) {
        int[][] ar1 = new int[][]{{1, 2, 100}, {2, 5, 100}, {3, 4, 100}};
        int[][] ar2 = new int[][]{{1, 5, 3}, {4, 8, 7}, {6, 9, 1}};
        arrayManipulation(10, ar2);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ManipulationArrayTask task = new ManipulationArrayTask(ar2, 0, 10);
        Long invoke = forkJoinPool.invoke(task);
        System.out.println("invoke = " + invoke);
        ArrayDeque a = new ArrayDeque();
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long max = 0L;
        for (int j = 0; j < n; j++) {
            long currentSum = 0;
            for (int[] query : queries) {
                int element = j + 1;
                if (element <= query[1] && element >= query[0]) {
                    currentSum += query[2];
                }
            }
            if (currentSum > max) {
                max = currentSum;
            }
        }
        return max;
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation2(int n, int[][] queries) {
        long max = 0L;
        long[] longs = new long[n];
        int minIndex = Integer.MAX_VALUE, maxIndex = 0;

        for (int[] query : queries) {
            if (query[0] < minIndex) {
                minIndex = query[0];
            }
            if (query[1] > maxIndex) {
                maxIndex = query[1];
            }
        }

        for (int j = minIndex; j < maxIndex; j++) {
            long currentSum = 0;
            for (int[] query : queries) {
                int element = j + 1;
                if (element <= query[1] && element >= query[0]) {
                    currentSum += query[2];
                }
            }
            if (currentSum > max) {
                max = currentSum;
            }
        }
        return max;
    }

    // Complete the arrayManipulation function below.
    static long arrayManipulation3(int n, int[][] queries) {
        long max = 0L;
        int m2 = queries.length * 2;
        List<IntPair> pairs = new ArrayList<>(m2);
        for (int[] query : queries) {
            pairs.add(new IntPair(query[0], query[2]));
            pairs.add(new IntPair(query[1] + 1, -1 * query[2]));
        }

        Collections.sort(pairs);
        long sum =0L;
        for (int i = 0; i < m2; i++) {
            sum+=pairs.get(i).value;
            max = Math.max(max, sum);
        }
        return max;
    }

    static class IntPair implements Comparable<IntPair>{
        int position;
        int value;

        IntPair(int f, int s) {
            position = f;
            value = s;
        }


        @Override
        public int compareTo(IntPair o) {
            return Integer.compare(this.position, o.position);
        }
    }
}
