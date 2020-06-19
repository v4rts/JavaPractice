
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler  {

    static final String HREF_TAG_1 = "href=\"http://";
    static final String HREF_TAG_2 = "<a";
    static final int HREF_TAG_SHIFT = 9;

    static LinkedList<URLDepthPair> visitedLinks = new LinkedList<URLDepthPair>();
    static LinkedList<URLDepthPair> linksToVisit = new LinkedList<URLDepthPair>();
    static HashSet<String> seenURL = new HashSet<String>();

    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp:\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    public static void process(String url, int depth) throws MalformedURLException {
        URLDepthPair initialPair = new URLDepthPair(url, 0);
        linksToVisit.add(initialPair);
        seenURL.add(url);
        int currentDepth;

        while(!linksToVisit.isEmpty()) {
            URLDepthPair currentPair = linksToVisit.removeFirst();
            currentDepth = currentPair.getDepth();

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
                            if (currentDepth < depth && !seenURL.contains(newLink)){
                                URLDepthPair newPair = new URLDepthPair(newLink, currentDepth + 1);
                                linksToVisit.add(newPair);
                                seenURL.add(newLink);
                            }
                        }

                    currentLine = socketInput.readLine();
                }
                socket.close();
                visitedLinks.add(currentPair);

            } catch (Exception e) {
                System.out.println(e.getMessage() + "" + currentPair.getHost());
            }
        }

    }
    private static LinkedList<URLDepthPair> getSites() {
        return(visitedLinks);
    }
    public static void main(String[] args) throws MalformedURLException {
        if (args.length != 2) {
            System.out.println("usage: java Crawler <URL> <maximum_depth>");
            return;
        }
        String urlStart = args[0];
        int depth = Integer.parseInt(args[1]);
        process(urlStart, depth);
        for (URLDepthPair pair : getSites()) {
            System.out.println(pair);
        }
    }

}
