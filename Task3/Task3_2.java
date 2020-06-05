
public class Task3_2 {
    public static void main(String[] args) {
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(findZip("all zip files are compressed"));
    }
    private static int findZip(String s) {
        if (s.lastIndexOf("zip") != s.indexOf("zip") && s.indexOf("zip") != -1)
            return s.lastIndexOf("zip");
        return -1;
    }
}