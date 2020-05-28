
public class Task2_5 {
    public static void main(String[] args) {
        System.out.println(getDecimalPlaces("43.20"));
        System.out.println(getDecimalPlaces("400"));
        System.out.println(getDecimalPlaces("3.1"));
    }
    private static int getDecimalPlaces(String s) {
        String[] stringArr = s.split("\\.");
        if (stringArr.length == 1){
            return 0;
        }else{
            return stringArr[1].length();
        }
    }
}