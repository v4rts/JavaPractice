
public class Task2_2 {
    public static void main(String[] args) {
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(differenceMaxMin(new int[] {44, 32, 86, 19}));
    }
    public static int differenceMaxMin(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int value:array){
            if (min>value){
                min = value;
            }
            if (max<value){
                max = value;
            }
        }
        return max-min;
    }
}