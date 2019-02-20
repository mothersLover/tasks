package test.concurrent.wait;

import org.junit.Test;

public class WaitNotifyDemo {

    @Test
    public void test() {
        final MyCollection myCollection = new MyCollection(1);
        for (int i = 1; i < 11; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    myCollection.add(finalI);
                    Thread.sleep(1000);
                    myCollection.removeLast();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class MyCollection {
        private final int[] arr;
        private int currentIndex = 0;

        private MyCollection(int size) {
            this.arr = new int[size];
        }

        public void add(int i) throws InterruptedException {
            synchronized (this) {
                System.out.println("Thread - " + Thread.currentThread().getName() + " is trying to insert - " + i);
                while (currentIndex >= arr.length) {
                    System.out.println("Thread - " + Thread.currentThread().getName() + " is waiting!");
                    wait();
                    System.out.println("Thread - " + Thread.currentThread().getName() + " woke up!");
                }
                arr[currentIndex++] = i;
                System.out.println("Thread - " + Thread.currentThread().getName() + " added - " + i);
                System.out.println("Thread - " + Thread.currentThread().getName() + " is notifying");
                notifyAll();

            }
        }

        public synchronized int removeLast() throws InterruptedException {
            System.out.println("Thread - " + Thread.currentThread().getName() + " is trying to remove ");
            while (currentIndex <= 0) {
                System.out.println("Thread - " + Thread.currentThread().getName() + " is waiting");
                wait();
                System.out.println("Thread - " + Thread.currentThread().getName() + " woke up!");
            }
            int element = arr[--currentIndex];
            System.out.println("Thread - " + Thread.currentThread().getName() + " deleted - " + element);
            System.out.println("Thread - " + Thread.currentThread().getName() + " is notifying");
            notifyAll();
            return element;
        }
    }
}
