package test.datastructures.queue;

import java.util.*;

public class PriorityQueueTest {

    public static void main(String... aar) {
        PriorityQueue<Integer> as = new PriorityQueue<>(Collections.reverseOrder());
        int initialCapacity = 10;
        List<Integer> integers = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            Random random = new Random();
            integers.add(random.nextInt(10000));
        }
        int step = 1;
        for (int i : integers) {
            if (step >= 6) {
                Integer peek = as.peek();
                if (peek > i) {
                    as.poll();
                    as.add(i);
                }
            } else {
                as.add(i);
            }
            step++;
        }
        Collections.sort(integers);
        System.out.println();
    }
}
