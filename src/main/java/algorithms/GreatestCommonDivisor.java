package algorithms;

public class GreatestCommonDivisor {

    public static void main(String... args) {

    }

    public static int gcd(int n, int m) {
        if (n < m) {
            return gcd(m, n);
        }
        if (n % m == 0) {
            return m;
        }
        return gcd(m, n % m);
    }
}
