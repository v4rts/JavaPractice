
public class Task3_4 {
    public static void main(String[] args){
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(flipEndChars("ada"));
        System.out.println(flipEndChars("Ada"));
    }

    public static String flipEndChars(String s) {
        if(s.length() < 2)
            return "Incompatible.";

        if(s.charAt(0) == s.charAt(s.length()-1))
            return "Two's a pair.";

        char[] chars = s.toCharArray();
        char a = chars[0];
        chars[0] = chars[s.length()-1];
        chars[s.length()-1] = a;
        return String.valueOf(chars);
    }
}