package test.datastructures.heap;

public class MaxHeap {
    private int[] arr;
    private final static int CAPACITY = 16;
    private int size;

    public MaxHeap() {
        arr = new int[CAPACITY];
        size = 0;
    }

    public MaxHeap(int[] arr) {
        size = arr.length;
        this.arr = new int[size];
        System.arraycopy(arr, 0, this.arr, 0, arr.length);
        for (int i = size / 2; i >= 0; i--) {
            percolateDown(i);
        }
    }

    public int peek() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        return arr[0];
    }

    private void percolateDown(int index) {
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;
        int indexToSwap = -1;
        int indexValue = arr[index];
        if (leftChildIndex <= size - 1) {
            indexToSwap = leftChildIndex;
        }
        if (rightChildIndex <= (size - 1) && arr[rightChildIndex] > arr[leftChildIndex]) {
            indexToSwap = rightChildIndex;
        }
        if (indexToSwap != -1 && indexValue < arr[indexToSwap]) {
            int temp = arr[indexToSwap];
            arr[indexToSwap] = arr[index];
            arr[index] = temp;
            percolateDown(indexToSwap);
        }
    }

    private void percolateUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (parentIndex < 0) {
            return;
        }
        if (arr[parentIndex] < arr[index]) {
            int temp = arr[parentIndex];
            arr[parentIndex] = arr[index];
            arr[index] = temp;
            percolateUp(parentIndex);
        }
    }

    public void add(int element) {
        if (size == arr.length) {
            doubleSize();
        }
        arr[++size] = element;
        percolateUp(size - 1);
    }

    private void doubleSize() {
        int [] newArr = new int[size * 2];
        System.arraycopy(arr, 0, newArr, 0, size);
        this.arr = newArr;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int max = arr[0];
        int last = arr[--size];
        arr[0] = last;
        percolateDown(0);
        return max;
    }

    public static void heapSort(int[] arr) {
        MaxHeap maxHeap = new MaxHeap(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxHeap.dequeue();
        }
    }
}
