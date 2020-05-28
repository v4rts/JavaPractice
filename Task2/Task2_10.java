
public class Task2_10 {
    public static void main(String[] args) {
        System.out.println(boxSeq(0));
        System.out.println(boxSeq(1));
        System.out.println(boxSeq(2));
    }

    private static int boxSeq(int s) {
        int res = 0;
        for (int i = 1; i <= s; i++) {
            if (i % 2 != 0)
                res += 3;
            else
                res -= 1;
        }
        return res;
    }
}