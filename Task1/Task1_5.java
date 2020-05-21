public class Task1_5{   
	public static void main(String[] args) {
        System.out.println(operation(24,15,9));
        System.out.println(operation(24,26,2));
        System.out.println(operation(15,11,11));
    }

    static String operation(int N, int a, int b) {
        if (a+b == N)
            return "added";
        else if (a-b == N)
            return "subtracted";
        else if (a*b == N)
            return "multiplied";
        else if (a/b == N)
            return "divided";
        else
            return "none";
    }
}