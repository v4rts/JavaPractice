public class Task5_9 {
    public static void main(String[] args){
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
    }
    public static String correctTitle(String str) {
        String[] words = str.split(" ");
        String ans = "";
        for (int i = 0; i < words.length; i++) {
            if (i>0) {
                ans += " ";
            }
            String[] extrawords = words[i].split("-");
            for (int j = 0; j < extrawords.length; j++) {
                if(j>0) {
                    ans += "-";
                }
                if (extrawords[j].equalsIgnoreCase("and")
                     || extrawords[j].equalsIgnoreCase("the")
                     || extrawords[j].equalsIgnoreCase("of")
                     || extrawords[j].equalsIgnoreCase("in")) {
                    ans += extrawords[j].toLowerCase();
                }
                else {
                    ans += extrawords[j].substring(0,1).toUpperCase();
                    asn += extrawords[j].substring(1).toLowerCase();
                }
            }
        }  
        return ans;
    }
}