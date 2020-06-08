
public class Task4_9 {
	public static void main(String[] args) {
        System.out.println(trouble("451999277","41177722899"));
        System.out.println(trouble("666789","12345667"));
        System.out.println(trouble("1222345", "12345"));
	}
public static boolean trouble(String num1, String num2) {
		int k1 = 0;
		int k2 = 0;
		String currentChar = "";
		for (int i = 0; i < num1.length() - 1; i++) {
			currentChar = String.valueOf(num1.charAt(i));
			if (currentChar.equals(String.valueOf(num1.charAt(i + 1)))) 
			k1++;
			else k1 = 0;
			if (k1 == 2) {
				k2 = 0;
				for (int j = 0; j < num2.length(); j++) {
					if (currentChar.equals(String.valueOf(num2.charAt(j)))) 
					k2++;
					else k2 = 0;
					if (k2 == 2) return true;
				}
			}
		}
		return false;
	}
}