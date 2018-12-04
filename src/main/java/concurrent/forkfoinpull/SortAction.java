package concurrent.forkfoinpull;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class SortAction extends RecursiveAction {
    private final long[] array;
    private final int start;
    private final int end;
    private final int THRESHOLD = 250000;

    public SortAction(long[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public SortAction(long[] array) {
        this(array, 0, array.length);
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            sortSequentially(start, end);
        } else {
            int middle = (start + end) >>> 1;
            invokeAll(new SortAction(array, start, middle), new SortAction(array, middle, end));
            merge(start, middle, end);
        }
    }

    private void merge(int start, int middle, int end) {
        long[] buffer = Arrays.copyOfRange(array, start, middle);
        for (int i = 0, j = start, k = middle; i < buffer.length; j++) {
            array[j] = (k == end || buffer[i] < array[k]) ? buffer[i++] : array[k++];
        }
    }

    private void sortSequentially(int start, int end) {
        Arrays.sort(array, start, end);
    }
}
