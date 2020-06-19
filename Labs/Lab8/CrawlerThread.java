import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerThread implements Runnable{
    URLPool pool;
    static final String HREF_TAG_1 = "href=\"http://";
    static final String HREF_TAG_2 = "<a";

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp:\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public CrawlerThread(URLPool pool) {
        this.pool = pool;
    }

    public void run() {
        while (true) {
            URLDepthPair currentPair = pool.getNextPair();
            int currentDepth = currentPair.getDepth();
            try {
                Socket socket= new Socket();
                socket.connect(new InetSocketAddress(currentPair.getHost(), 80));
                socket.setSoTimeout(500);
                PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                socketOutput.println("GET " + currentPair.getPath() + " HTTP/1.1");
                socketOutput.println("Host: " + currentPair.getHost());
                socketOutput.println("Connection: close");
                socketOutput.println();
                socketOutput.flush();

                String currentLine = socketInput.readLine();
                while (currentLine != null) {
                    int indexFound_1 = currentLine.indexOf(HREF_TAG_1);
                    int indexFound_2 = currentLine.indexOf(HREF_TAG_2);
                    if (indexFound_1 != -1 && indexFound_2 != -1) {
                        Matcher getLink = urlPattern.matcher(currentLine);
                        getLink.find();
                        int linkStart = getLink.start() + 1;
                        int linkEnd = getLink.end();
                        String newLink = currentLine.substring(linkStart, linkEnd);

                        URLDepthPair newPair = new URLDepthPair(newLink, currentDepth + 1);
                        pool.addPair(newPair);

                    }

                    currentLine = socketInput.readLine();
                }
                socket.close();
            } catch (Exception e) {
            }
        }
    }
}
