public class Task2_1 {
    public static void main(String[] args) {
        System.out.println(repeat("mice", 5));
        System.out.println(repeat("hello", 3));
        System.out.println(repeat("stop", 1));
    }

    public static String repeat(String str, int n) {
        String ans = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < n; j++) {
                ans += str.charAt(i);
            }
        }
        return ans;
    }
}