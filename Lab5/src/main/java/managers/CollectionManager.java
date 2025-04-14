package managers;

import data.Ticket;
import utils.DateTimeUtils;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

public class CollectionManager {
    private final ArrayDeque<Ticket> collection;
    private final LocalDateTime initializationTime;
    private LocalDateTime lastUpdateTime;

    public CollectionManager() {
        this.collection = new ArrayDeque<>();
        this.initializationTime = DateTimeUtils.getStartTime();
        this.lastUpdateTime = DateTimeUtils.getCurrentTime();
    }

    public ArrayDeque<Ticket> getCollection() {
        return collection;
    }

    public LocalDateTime getInitializationTime() {
        return initializationTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void updateLastModifiedTime() {
        lastUpdateTime = DateTimeUtils.getCurrentTime();
    }

    public int getCollectionSize() {
        return collection.size();
    }

    public void clearCollection() {
        collection.clear();
        updateLastModifiedTime();
    }
}
