package utils;

import java.util.concurrent.atomic.AtomicInteger;

public final class IdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1);

    public static int getAndIncrement() {
        return counter.getAndIncrement();
    }
}
