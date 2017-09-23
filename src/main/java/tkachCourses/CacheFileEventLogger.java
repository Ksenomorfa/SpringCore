package tkachCourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        cache = new ArrayList<>();
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) throws IOException {
        cache.add(event);
        if(cache.size() == cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() throws IOException {
        for(Event event : cache) {
            super.logEvent(event);
        }
    }

    public void destroy() throws IOException {
        if (!cache.isEmpty()) {
            writeEventFromCache();
            cache.clear();
        }
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public List<Event> getCache() {
        return cache;
    }

    public void setCache(List<Event> cache) {
        this.cache = cache;
    }
}
