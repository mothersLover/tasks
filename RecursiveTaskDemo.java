package test.concurrent.forkfoinpull;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class RecursiveTaskDemo {
    public static void main(String... args) {
        int arraySize = 10000000;
        double[] arr = new double[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            arr[i] = Math.abs(random.nextDouble());
            if ((i % (arraySize / 10)) == 0) {
                System.out.println("i = " + i + " doubleArray[i] = " + arr[i]);
            }
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumTask task = new SumTask(arr, 0, arraySize);

        long begin = System.currentTimeMillis();
        Double invoke = forkJoinPool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("Sum is - " + invoke);
        System.out.println("Elapsed time for fork join- " + (end - begin) + " milliseconds");

        begin = System.currentTimeMillis();
        double sum = 0.0;
        for (int i = 0; i < arraySize; i++) {
            sum += arr[i];
        }
        end = System.currentTimeMillis();
        System.out.println("Sum is - " + sum);
        System.out.println("Elapsed time for loop- " + (end - begin) + " milliseconds");
    }
}
