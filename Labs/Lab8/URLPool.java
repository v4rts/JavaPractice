import java.util.HashSet;
import java.util.LinkedList;

public class URLPool {
    LinkedList<URLDepthPair> urlToCrawl;
    LinkedList<URLDepthPair> urlCrawled;
    int maxDepth;
    int waitCount;
    HashSet<String> urlSeen;

    public URLPool(int depth) {
        this.maxDepth = depth;
        urlToCrawl = new LinkedList<URLDepthPair>();
        urlCrawled = new LinkedList<URLDepthPair>();
        waitCount = 0;
        urlSeen = new HashSet<String>();
    }
    public synchronized URLDepthPair getNextPair() {
        while (urlToCrawl.size() == 0) {
            try {
                waitCount++;
                wait();
                waitCount--;
            } catch (InterruptedException e) { System.out.println("Получено исключение"); }
        }
        URLDepthPair nextPair = urlToCrawl.removeFirst();
        return nextPair;
    }
    public synchronized void addPair(URLDepthPair pair) {
        if (urlSeen.contains(pair.getURLString())) {
            return;
        }
        urlCrawled.add(pair);
        if (pair.getDepth() < maxDepth) {
            urlToCrawl.add(pair);
            notify();
        }
        urlSeen.add(pair.getURLString());
    }
    public synchronized int getWaitCount() {
        return waitCount;
    }

    public LinkedList<URLDepthPair> getSeenUrls() {
        return urlCrawled;
    }
}
