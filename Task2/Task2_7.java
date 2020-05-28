
public class Task2_7 {
    public static void main(String[] args) {
        System.out.println(isValid("59001"));
        System.out.println(isValid("853a7"));
        System.out.println(isValid("732 32"));
        System.out.println(isValid("393939"));
    }
    
    private static boolean isValid(String s) {
        return s.matches("[0-9]{5}");
    }
}