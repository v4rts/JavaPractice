
public class Task2_9 {
    public static void main(String[] args) {
        System.out.println(isPrefix("automation","auto-"));
        System.out.println(isSuffix("arachnophobia","-phobia"));
        System.out.println(isPrefix("retrospect","sub-"));
        System.out.println(isSuffix("vacation","-logy"));
    }

    private static boolean isPrefix(String s1, String s2) {
        return s1.startsWith(s2.substring(0, s2.length()-2));
    }
    
    private static boolean isSuffix(String s1, String s2) {
        return s1.endsWith(s2.substring(1));
    }
}