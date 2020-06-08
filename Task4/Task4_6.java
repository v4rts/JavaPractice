
public class Task4_6 {
    public static void main(String[] args){
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        }
    public static int bugger(int n) {
            int k = 0;
    
            while(Integer.valueOf(n).toString().toCharArray().length > 1){
                int n2 = 1;
                for(char c : Integer.valueOf(n).toString().toCharArray()){
                    n2 *= Integer.valueOf(String.valueOf(c));
                }
                n = n2;
                k += 1;
            }
            return k;
        }
}