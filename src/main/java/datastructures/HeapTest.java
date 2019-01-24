package test.datastructures.heap;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void test() {
        PriorityQueue<Integer> integers = new PriorityQueue<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        Object[] objects = integers.toArray();
    }

    @Test
    public void peekAndCreationTest() {
        int[] ints = new int[] {1,3,2,5,4,7,1,0,9,8,6};
        MaxHeap maxHeap = new MaxHeap(ints);
        int peek = maxHeap.peek();
        Assert.assertEquals(9, peek);
    }

    @Test
    public void createEmptyHeapAndAdd() {
        MaxHeap maxHeap = new MaxHeap();
        maxHeap.add(1);
        maxHeap.add(3);
        maxHeap.add(2);
        maxHeap.add(5);
        maxHeap.add(4);
        maxHeap.add(7);
        maxHeap.add(1);
        maxHeap.add(0);
        maxHeap.add(9);
        maxHeap.add(8);
        maxHeap.add(6);
        int peek = maxHeap.peek();
        Assert.assertEquals(9, peek);
    }

    @Test
    public void dequeueTest() {
        int[] ints = new int[] {1,3,2,5,4,7,1,0,9,8,6};
        MaxHeap maxHeap = new MaxHeap(ints);
        int dequeue = maxHeap.dequeue();
        Assert.assertEquals(9, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(8, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(7, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(6, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(5, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(4, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(3, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(2, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(1, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(1, dequeue);
        dequeue = maxHeap.dequeue();
        Assert.assertEquals(0, dequeue);
        exception.expect(IllegalStateException.class);
        maxHeap.dequeue();
    }

    @Test
    public void heapSortTest() {
        int[] ints = new int[] {1,3,2,5,4,7,1,0,9,8,6};
        MaxHeap.heapSort(ints);
        int[] properArray = new int[] {9,8,7,6,5,4,3,2,1,1,0};
        boolean equals = Arrays.equals(ints, properArray);
        Assert.assertTrue(equals);
    }
}
