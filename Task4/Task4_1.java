import java.util.*;
public class Task4_1 {
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("n: ");
		int n = Integer.parseInt(input.nextLine());
		System.out.print("k: ");
		int k = Integer.parseInt(input.nextLine());
		System.out.printf("String: ");
		String text = input.nextLine();
		System.out.println(function(n, k, text));
	}

	public static String function(int n, int k, String text) {
		String[] words = text.split(" ");
		String string = "";
		String ans = "";
		if (n > words.length)
		 return "Error";
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (string.replace(" ", "").length() + word.length() <= k) 
				string += word + " ";
			else {
				ans += string + "\n";
				string = word + " ";
			}
		}
		if (string.length() > 0) {
			ans += string + "\n";
		}
		return ans;
	}
}