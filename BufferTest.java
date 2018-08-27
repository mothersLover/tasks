package test.concurrent.synchronizers.semaphore;

public class BufferTest {
    public static void main(String... args) {
        Buffer buffer = new Buffer();
        new Publisher(buffer);
        new Consumer(buffer);
    }

    private static class Publisher implements Runnable {

        private final Buffer buffer;

        public Publisher(Buffer buffer) {
            this.buffer = buffer;
            new Thread(this, "Publisher").start();
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                buffer.put(i);
            }
        }
    }

    private static class Consumer implements Runnable {

        private final Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
            new Thread(this, "Consumer").start();
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                Object o = buffer.get();
            }
        }
    }
}
