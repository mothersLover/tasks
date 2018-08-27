package test.concurrent.phaser;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String... args) {
        Phaser phaser = new Phaser(1);

        new SimpleThread(1, phaser);
        new SimpleThread(2, phaser);
        new SimpleThread(3, phaser);

        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Main thread completed " + phase + " phase");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Main thread completed " + phase + " phase");

        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Main thread completed " + phase + " phase");

        phaser.arriveAndDeregister();
    }

    private static class SimpleThread implements Runnable {

        private final int id;
        private final Phaser phaser;

        SimpleThread(int id, Phaser phaser) {
            this.id = id;
            this.phaser = phaser;
            phaser.register();
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(10000);
                System.out.println("Thread " + id + " completed phase 0");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + id + " completed phase 1");
            phaser.arriveAndAwaitAdvance();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread " + id + " completed phase 2");
            phaser.arriveAndDeregister();
        }
    }
}
