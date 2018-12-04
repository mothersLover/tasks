package datastructures.queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;

public class QueueUsingTwoStacks {

    @Test
    public void test() {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        Object[] objects = queue.toArray();
        Object[] compare = new Object[] {1 , 2, 3, 4};
        Assert.assertArrayEquals(compare, objects);
        Integer peek = queue.peek();
        Assert.assertEquals(new Integer(1), peek);
        Integer dequeue = queue.dequeue();
        Assert.assertEquals(new Integer(1), dequeue);
        objects = queue.toArray();
        compare = new Object[] {2, 3, 4};
        Assert.assertArrayEquals(compare, objects);
        peek = queue.peek();
        Assert.assertEquals(new Integer(2), peek);
        dequeue = queue.dequeue();
        Assert.assertEquals(new Integer(2), dequeue);
        objects = queue.toArray();
        compare = new Object[] {3, 4};
        Assert.assertArrayEquals(compare, objects);
        queue.enqueue(1);
        queue.enqueue(2);
        objects = queue.toArray();
        compare = new Object[] {3, 4, 1, 2};
        Assert.assertArrayEquals(compare, objects);
    }

    private static class MyQueue<T> {
        private final ArrayDeque<T> firstStack = new ArrayDeque<>();
        private final ArrayDeque<T> secondStack = new ArrayDeque<>();

        void enqueue(T element) {
            firstStack.addLast(element);
        }

        T dequeue() {
            if (!secondStack.isEmpty()) {
                return secondStack.pollLast();
            }
            if (firstStack.isEmpty()) {
                return null;
            }
            while(!firstStack.isEmpty()) {
                secondStack.addLast(firstStack.pollLast());
            }
            return secondStack.pollLast();
        }

        T peek() {
            if (!secondStack.isEmpty()) {
                return secondStack.peekLast();
            }
            if (firstStack.isEmpty()) {
                return null;
            }
            while(!firstStack.isEmpty()) {
                secondStack.addLast(firstStack.pollLast());
            }
            return secondStack.peekLast();
        }

        Object[] toArray() {
            if (!secondStack.isEmpty()) {
                Object[] objects = secondStack.toArray();
                Object[] objects1 = firstStack.toArray();
                int length = objects.length;
                int length1 = objects1.length;
                Object[] result = new Object[length + length1];
                for (int i = length - 1; i >= 0; i--) {
                    result[length - (i + 1)] = objects[i];
                }
                System.arraycopy(objects1, 0, result, length, length1);
                return result;
            }
            return firstStack.toArray();
        }
    }
}
