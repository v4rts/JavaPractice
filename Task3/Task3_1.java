
public class Task3_1 {
    public static void main(String[] args) {
        System.out.println(solution(1,0,-1));
        System.out.println(solution(1,0,0));
        System.out.println(solution(1,0,1));
    }

    private static int solution(int a, int b, int c) {
        double d = b*b - 4*a*c;
        if (d == 0) {
            return 1;
        } else if (d < 0) {
            return 0;
        } else {
            return 2;
        }
    }
}