import java.net.MalformedURLException;
import java.net.URL;

public class URLDepthPair {
    private URL url;

    private int depth;

    public static final String URL_PREFIX = "http://";

    public URLDepthPair(String url, int depth) throws MalformedURLException {
        this.url = new URL(url);
        this.depth = depth;
    }
    public String toString() {
        return(this.url + " глубина: "+depth);
    }
    public String getHost() {
        return(this.url.getHost());
    }
    public String getPath() {
        return (this.url.getPath());
    }

    public int getDepth() {
        return(depth);
    }
    public String getURLString() {
        return url.toString();
    }
}
