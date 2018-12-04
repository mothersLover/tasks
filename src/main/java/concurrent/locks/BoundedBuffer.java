package concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    private final Lock lock = new ReentrantLock();
    private final Condition notFullCondition = lock.newCondition();
    private final Condition notEmptyCondition = lock.newCondition();
    private final Object[] objects = new Object[100];
    private int putIndex, takeIndex, count;

    public void put(Object o) {
        lock.lock();
        try {
            int objectLength = objects.length;
            while (count == objectLength) {
                notFullCondition.await();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " put " + o.toString());
            objects[putIndex] = o;
            if (++putIndex == objectLength) {
                putIndex = 0;
            }
            ++count;
            notEmptyCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public Object get() {
        lock.lock();
        try {
            while (count == 0) {
                notEmptyCondition.await();
            }
            Object object = objects[takeIndex];
            System.out.println("Thread " + Thread.currentThread().getName() + " get " + object.toString());
            if (++takeIndex == objects.length) {
                takeIndex = 0;
            }
            --count;
            notFullCondition.signal();
            return object;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        } finally {
            lock.unlock();
        }
    }
}
