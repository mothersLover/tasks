package test.algorithms.interesting;

public class TowerOfHanoi {

    public static void main(String... asr) {
        int num = 4;
        System.out.println("Moves are:");
        towerOfHanoi(num, 'A', 'C', 'B');
    }

    public static void towerOfHanoi(int num, char src, char dst, char temp) {
        if (num < 1) {
            return;
        }
        towerOfHanoi(num - 1, src, temp, dst);
        System.out.println("Move " + num + " disk from peg " + src + " to peg " + dst);
        towerOfHanoi(num - 1, temp, dst, src);
    }
}
