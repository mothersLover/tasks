package test.concurrent.forkfoinpull;

import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Double> {
    private final double[] array;
    private final int start;
    private final int end;
    private final int THRESHOLD = 250000;

    public SumTask(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    public SumTask(double[] array) {
        this(array, 0, array.length);
    }
    
    @Override
    protected Double compute() {
        double sum = 0.0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
        } else {
            int middle = (end + start) >>> 1;
            SumTask sumTask1 = new SumTask(array, start, middle);
            SumTask sumTask2 = new SumTask(array, middle, end);
            sumTask1.fork();
            sumTask2.fork();
            sum = sumTask1.join() + sumTask2.join();
        }
        return sum;
    }
}
