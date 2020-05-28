
public class Task2_3 {
    public static void main(String[] args) {
        System.out.println(isAvgWhole(new int[] {1, 3}));
        System.out.println(isAvgWhole(new int[] {1, 2, 3, 4}));
        System.out.println(isAvgWhole(new int[] {1, 5, 6}));
        System.out.println(isAvgWhole(new int[] {1, 1, 1}));
        System.out.println(isAvgWhole(new int[] {9, 2, 2, 5}));
    }
    private static boolean isAvgWhole(int[] array) {
        int sum = 0;
        for (int value:array){
            sum += value;
        }
        return sum % array.length == 0;
    }
}