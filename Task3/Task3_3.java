
public class Task3_3 {
    public static void main(String[] args){
        System.out.println(checkPerfect(6));
    }
    public static boolean checkPerfect(int n) {
        int check = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                check += i;
            if (check > n)
                return false;
        }
        return (check == n);
    }
}