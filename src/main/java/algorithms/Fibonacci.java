package algorithms;

public class Fibonacci {

    public static void main(String... args) {
        int fibonacci = fibonacci(6);
        System.out.println("fibonacci = " + fibonacci);
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
