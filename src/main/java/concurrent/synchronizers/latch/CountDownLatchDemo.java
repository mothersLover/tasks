package concurrent.synchronizers.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    private static final int COUNT = 3;

    public static void main(String... args) {
        CountDownLatch countDownLatch = new CountDownLatch(COUNT);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new SimpleThread(countDownLatch, 1000));
        executorService.execute(new SimpleThread(countDownLatch, 3000));
        executorService.execute(new SimpleThread(countDownLatch, 5000));
        try {
            System.out.println("Main thread is waiting while all thread will do all");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
          executorService.shutdown();
        }
        System.out.println("Main thread done!");
    }

    private static class SimpleThread implements Runnable {

        private final CountDownLatch latch;
        private final int number;

        public SimpleThread(CountDownLatch latch, int number) {
            this.latch = latch;
            this.number = number;
        }

        public void run() {
            String name = Thread.currentThread().getName();
            System.out.println("Thread - " + name + " running");
            System.out.println("Thread - " + name + " sleeps for " + number + " milliseconds");
            try {
                Thread.sleep(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long count = latch.getCount();
            System.out.println("Thread " + name + " getting count = " + count);
            latch.countDown();
            System.out.println("Thread " + name + " done");
        }
    }
}
