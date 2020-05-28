
public class Task2_6 {
    public static void main(String[] args) {
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(7));
        System.out.println(fibonacci(12));
    }
    private static int fibonacci(int val) {
        int f0 = 1;
        int f1 = 1;
        int f2 = 0;
        for (int i = 3; i <= val+1; i++) {
            f2 = f0 + f1;
            f0 = f1;
            f1 = f2;
        }
        return f2;
    }
}