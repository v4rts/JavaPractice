 public class Task1_3{
    public static void main(String[] args) {
        System.out.println(animals(2, 3, 5));
    }

    static int animals(int chickens, int cows, int pigs) {
        return chickens*2 + cows*4 + pigs*4;
    }
}