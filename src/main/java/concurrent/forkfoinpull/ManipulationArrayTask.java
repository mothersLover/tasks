package concurrent.forkfoinpull;

import java.util.concurrent.RecursiveTask;

public class ManipulationArrayTask extends RecursiveTask<Long> {

    private final int[][] queries;
    private final int start;
    private final int end;
    private final static int THRESHOLD = 5;

    public ManipulationArrayTask(int[][] queries, int start, int end) {
        this.queries = queries;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long max = 0;
        if (end - start < THRESHOLD) {
            for (int j = start; j < end; j++) {
                long currentSum = 0;
                for (int[] query : queries) {
                    int element = j + 1;
                    if (element <= query[1] && element >= query[0]) {
                        currentSum += query[2];
                    }
                }
                if (currentSum > max) {
                    max = currentSum;
                }
            }
            System.out.println("Start to execute! Start: " + start + " end: " + end + " SUM = " + max);
        } else {
            int middle = (end + start) >>> 1;
            ManipulationArrayTask manipulationArrayTask1 = new ManipulationArrayTask(queries, start, middle);
            ManipulationArrayTask manipulationArrayTask2 = new ManipulationArrayTask(queries, middle, end);
            manipulationArrayTask1.fork();
            manipulationArrayTask2.fork();
            Long join1 = manipulationArrayTask1.join();
            System.out.println("received join1 = " + join1 + " start - " + start + " end - " + middle);
            Long join2 = manipulationArrayTask2.join();
            System.out.println("received join2 = " + join2 + " start - " + middle + " end - " + end);
            max = Math.max(join1, join2);
        }
        return max;
    }
}
