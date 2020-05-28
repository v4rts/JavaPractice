import java.util.*;
public class Task2_4 {
    public static void main(String[] args) {
        System.out.println(cumulativeSum(new int[]{1, 2, 3}));
        System.out.println(cumulativeSum(new int[]{1, -2, 3}));
        System.out.println(cumulativeSum(new int[]{3, 3, -2, 408, 3, 3}));
    }

    public static String cumulativeSum(int[] array) {
        int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i+1; j++) {
                array2[i] += array[j];
            }
        }
        return Arrays.toString(array2);
    }
}