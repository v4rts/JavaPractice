import java.net.MalformedURLException;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws MalformedURLException {
        if (args.length != 3) {
            System.out.println("usage: java Crawler <URL> <maximum_depth> <num_threads>");
            return;
        }

        String urlStart = args[0];
        int depth = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);

        URLPool pool = new URLPool(depth);
        URLDepthPair initPair = new URLDepthPair(urlStart, 0);
        pool.addPair(initPair);

        for (int i = 0; i < threads; i++) {
            CrawlerThread c_thread = new CrawlerThread(pool);
            Thread thread = new Thread(c_thread);
            thread.start();
        }

        while (pool.getWaitCount() != threads) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
            }
        }

        LinkedList<URLDepthPair> allURL = pool.getSeenUrls();
        for (URLDepthPair pair : allURL) {
            System.out.println(pair.toString());
        }

        System.exit(0);
    }
}
