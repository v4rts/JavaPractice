import java.util.Arrays;
import java.util.List;

public class Task3_5 {
	    public static void main(String[] args){
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(isValidHexCode("#eaecee"));
        System.out.println(isValidHexCode("#CD5C58C"));
        System.out.println(isValidHexCode("#CD5C5Z"));
        System.out.println(isValidHexCode("#CD5C&C"));
        System.out.println(isValidHexCode("#CD5C5C"));
    }
    public static boolean isValidHexCode(String s) {
        return s.matches("#[a-fA-F0-9]{6}");
    }
}