package test.concurrent.exchanger;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String... args) {
        Exchanger<Integer> exchanger = new Exchanger<Integer>();
        new ExchangeThread(exchanger, new Random().nextInt(999));
        new ExchangeThread(exchanger, new Random().nextInt(999));
        new ExchangeThread(exchanger, new Random().nextInt(999));
        new ExchangeThread(exchanger, new Random().nextInt(999));
    }

    private static final class ExchangeThread implements Runnable {

        private final Exchanger<Integer> integerExchanger;
        private final Integer integer;

        public ExchangeThread(Exchanger<Integer> integerExchanger, Integer integer) {
            this.integerExchanger = integerExchanger;
            this.integer = integer;
            new Thread(this, "thread" + integer).start();
        }

        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " send: " + integer);
            try {
                Integer exchange = integerExchanger.exchange(integer);
                System.out.println("Thread " + Thread.currentThread().getName() + " receive: " + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
