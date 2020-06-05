
public class Task3_9 {
    public static void main(String[] args) {
        System.out.println(nextPrime(12));
        System.out.println(nextPrime(24));
        System.out.println(nextPrime(11));
    }

    private static int nextPrime(int x) {
        boolean flag = true;
        int n = x;
        while (true) {
            prime = true;
            for (int i = 2; i < n - 1; i++) {
                if (n % i == 0) prime = false;
            }
            if (flag) return n;
            n++;
        }
    }
}