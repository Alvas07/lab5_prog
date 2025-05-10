package utils.generators;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final ArrayList<Integer> idList = new ArrayList<>();

    public static int getAndIncrement() {
        int nextId = counter.getAndIncrement();
        while (!idIsUnique(nextId)) {
            nextId = counter.getAndIncrement();
        }
        addId(nextId);
        return nextId;
    }

    public static boolean idIsUnique(int id) {
        return idList.contains(id);
    }

    public static void addId(int id) {
        idList.add(id);
    }

    public static void remove(int id) {
        idList.remove(id);
    }
}
