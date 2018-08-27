package test.datastructures.arrays;

public class NewYearChaos {

    public static void main(String... arsa) {
        int[] i1 = new int[] {2,1,5,3,4};
        int[] i2 = new int[] {2,5,1,3,4};
        int[] i3 = new int[] {1,2,5,3,7,8,6,4};
        minBribesFromSolution(i3);
    }

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        for (int i = 0; i < q.length; i++) {
            int val = q[i];
            if (i + 1 < val && i + 1 < val - 2) {
                System.out.println("Too chaotic");
                return;
            }
        }

        int swaps = 0;

        for (int i = 1; i <= q.length; i++) {
            int pos = detectValue(q, i);
            swaps += Math.abs(pos - (i - 1));
            q = swapByCopying(q, pos, i - 1);
        }

        System.out.println(swaps);
    }

    static void minimumBribes2(int[] q) {
        int swaps = 0;
        for (int i = 0; i < q.length; i++) {
            int val = q[i];
            if (i + 1 < val && i + 1 < val - 2) {
                System.out.println("Too chaotic");
                return;
            }

            if (val < i + 1) {
                swaps += Math.abs(i - (val - 1));
                q = swapByCopying(q, i, val - 1);
            }
        }

        for (int i = 0; i < q.length; i++) {
            int val = q[i];
            if (val < i + 1) {
                swaps += Math.abs(i - (val - 1));
                q = swapByCopying(q, i, val - 1);
            }
        }

        System.out.println(swaps);
    }

    static void minBribesFromSolution(int[] q) {
        int bribe = 0;
        boolean chaotic = false;
        for(int i = 0; i < q.length; i++)
        {
            if(q[i]-(i+1) > 2)
            {
                chaotic = true;
                break;
            }
            for (int j = Math.max(0, q[i]-2); j < i; j++)
                if (q[j] > q[i])
                    bribe++;
        }
        if(chaotic)
            System.out.println("Too chaotic");
        else
            System.out.println(bribe);
    }

    static int swap(int[] q, int from, int to) {
        int res = Math.abs(to - from);
        if (to < from) {
            for (; to < from; from--) {
                int i1 = q[from - 1];
                q[from - 1] = q[from];
                q[from] = i1;
            }
        } else {
            for (int i = from; i < to; from++) {
                int i1 = q[from + 1];
                q[from + 1] = q[from];
                q[from] = i1;
                from++;
            }
        }
        return res;
    }

    static int[] swapByCopying(int[] q, int from, int to) {
        if (Math.abs(to - from) > 0) {
            int i = q[from];
            System.arraycopy(q, to, q, to + 1, from - to);
            q[to] = i;
            return q;
        } else {
            return q;
        }
    }

    static int detectValue(int[] q, int val) {
        int res = 0;
        for (int i = 0; i < q.length; i++) {
            if (q[i] == val) {
                res = i;
                break;
            }
        }
        return res;
    }
}
