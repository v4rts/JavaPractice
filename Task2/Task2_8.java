
public class Task2_8 {

    public static void main(String[] args) {
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));
    }


    private static boolean isStrangePair(String s1, String s2) {
        if ((s1 == "") || (s2 == "")){
            return true;
        } else if (s1.charAt(0)== s2.charAt(s2.length()-1) && s2.charAt(0) == s1.charAt(s1.length()-1)) {
            return true; 
        } else {
            return false;
        }
    }
}