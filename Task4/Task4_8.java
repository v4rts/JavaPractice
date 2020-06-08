
public class Task4_8 {
    public static void main(String[] args) {
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM."));
        System.out.println(doesRhyme("You are off to the races", "a splendid day."));
        System.out.println(doesRhyme("and frequently do?", "you gotta move."));
	}
public static boolean doesRhyme(String str1, String str2) {
		str1 = str1.replaceAll("\\p{Punct}",""); 
		str2 = str2.replaceAll("\\p{Punct}","");
		char c1 =str1.charAt(str1.length()-1);
		char c2=str2.charAt(str2.length()-1);
		
		if (Character.toLowerCase(c2)==Character.toLowerCase(c1))
		return true;
		else
			return false;
  }
}