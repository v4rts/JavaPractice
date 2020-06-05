
public class Task3_7 {
    public static void main(String[] args) {
        System.out.println(isKaprekar(5));
    }

    private static boolean isKaprekar(int x) {
        String str = String.valueOf((int) Math.pow(x, 2));
        int n1, n2 = 0;
        if (str.length() == 1)
            n1 = 0;
        else {
            n1 = Integer.parseInt(str.substring(0, (int) (str.length() / 2)));
            n2 = Integer.parseInt(str.substring((int) (str.length() / 2)));
        }
        return (n1 + n2 == x);
    }
}