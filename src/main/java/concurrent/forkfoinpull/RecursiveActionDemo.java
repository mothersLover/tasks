package concurrent.forkfoinpull;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class RecursiveActionDemo {
    public static void main(String... args) {
        int arraySize = 10000000;
        long[] arr = new long[arraySize];
        Random random = new Random();
        for (int i = 0; i < arraySize; i++) {
            arr[i] = Math.abs(random.nextLong());
            if ((i % (arraySize / 10)) == 0) {
                System.out.println("i = " + i + " longArray[i] = " + arr[i]);
            }
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SortAction task = new SortAction(arr, 0, arraySize);

        long begin = System.currentTimeMillis();
        forkJoinPool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed time - " + (end - begin) + " milliseconds");
        //int randomNum = rand.nextInt((max - min) + 1) + min;
        int randomNum = random.nextInt((arraySize - 100) + 1);
        System.out.println("Random portion of array from " + randomNum + " :");
        for (int i = 0; i < 10; i++) {
            System.out.println("array[" + randomNum++ + "] = " + arr[randomNum]);
        }
    }
}
