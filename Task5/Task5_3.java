public class Task5_3 {
    public static void main(String[] args) {
        System.out.println(canComplete("butl", "beautiful"));
    }
private static int oneSymbolAmoun(String a,char c){
        int amount = 0;
        for(int i=0;i<a.length();i++){
            if(a.charAt(i) == c) amount++;
        }
        return amount;
    }
    private static boolean canComplete(String a,String b){
        if(a.charAt(0) != b.charAt(0)) return false;
        int k = 0;
        for(char c = 'a';c <= 'z';c++){
            if(oneSymbolAmoun(a,c) > oneSymbolAmoun(b,c)) return false;
        }
        return true;
    }
}