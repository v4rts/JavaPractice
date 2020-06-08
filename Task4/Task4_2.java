import java.util.*;

public class Task4_2 {
	public static void main(String[] args){
        System.out.println(Arrays.toString(split("((())())(()(()()))")));
        }
        public static String[] split(String str) {
            List<String> strlist = new ArrayList<>();
            int k = 0;
            int lastIndex = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '(') {
                    k++;
                }
                else {
                    k--;
                }
                if (k == 0) {
                    strlist.add(str.substring(lastIndex, i + 1));
                    lastIndex = i + 1;
                }
            }
            String[] ans = new String[strlist.size()];
            return ans = strlist.toArray(ans);
        }
}