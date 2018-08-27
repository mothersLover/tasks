package test.concurrent.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class Buffer {
    private final Semaphore putSemaphore = new Semaphore(1);
    private final Semaphore getSemaphore = new Semaphore(0);
    private int buffer;

    public void put(int i) {
        try {
            putSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " put " + i);
        buffer = i;
        getSemaphore.release();
    }

    public int get() {
        try {
            getSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " get " + buffer);
        putSemaphore.release();
        return buffer;
    }
}
