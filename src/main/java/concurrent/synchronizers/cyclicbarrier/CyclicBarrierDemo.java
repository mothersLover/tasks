package concurrent.synchronizers.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CyclicBarrierDemo {
    private final static int THREAD_COUNT = 3;

    public static void main(String... args) {
        final AtomicInteger count = new AtomicInteger(0);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierReleaseAction(count));
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < 3; i++) {
            String name = "Thread" + i;
            executorService.execute(new SimpleThread(cyclicBarrier, name, count));
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    private static class CyclicBarrierReleaseAction implements Runnable {

        private final AtomicInteger atomicInteger;

        public CyclicBarrierReleaseAction(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        public void run() {
            System.out.println("CyclicBarrier Action!!!");
            atomicInteger.set(0);
            int i = atomicInteger.get();
            System.out.println("Count = " + i);
        }
    }

    private static class SimpleThread implements Runnable {

        private final CyclicBarrier cyclicBarrier;
        private final String name;
        private final AtomicInteger atomicInteger;

        public SimpleThread(CyclicBarrier cyclicBarrier, String name, AtomicInteger atomicInteger) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            this.atomicInteger = atomicInteger;
        }

        public void run() {
            String threadName = Thread.currentThread().getName();
            try {
                System.out.println("Thread " + threadName + " number isWaiting " + cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            int countValue = atomicInteger.getAndIncrement();
            System.out.println("Thread " + threadName + " counter value - " + countValue);
        }
    }
}
