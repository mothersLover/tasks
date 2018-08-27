package test.concurrent.locks;

public class BoundedBufferTest {
    public static void main(String ... args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        new Publisher(boundedBuffer);
        new Consumer(boundedBuffer);
    }

    private static class Publisher implements Runnable {

        private final BoundedBuffer boundedBuffer;

        public Publisher(BoundedBuffer boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
            new Thread(this, "Publisher").start();
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                boundedBuffer.put(i);
            }
        }
    }

    private static class Consumer implements Runnable {

        private final BoundedBuffer boundedBuffer;

        public Consumer(BoundedBuffer boundedBuffer) {
            this.boundedBuffer = boundedBuffer;
            new Thread(this, "Consumer").start();
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                Object o = boundedBuffer.get();
            }
        }
    }
}
