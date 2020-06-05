import java.util.Arrays;
public class Task3_8 {
public static void main(String[] args) {
        System.out.println(longestZero("01100001011000"));
        System.out.println(longestZero("100100100"));
        System.out.println(longestZero("11111"));
    }

    private static String longestZero(String str) {
        try {
            String[] splitStr = str.split("1+");
            Arrays.sort(splitStr);
            return splitStr[split.length-1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}