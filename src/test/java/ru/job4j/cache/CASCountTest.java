package ru.job4j.cache;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CASCountTest {

    @Test
    public void when5IncrementAndGet() {
        CASCount casCount = new CASCount();
        for (int i = 0; i < 5; i++) {
            casCount.increment();
        }
        assertEquals(5, casCount.get());
    }

    @Test
    public void whenIncrementAnd2Get() {
        CASCount casCount = new CASCount();
        casCount.increment();
        casCount.get();
        assertEquals(1, casCount.get());
    }

    @Test
    public void whenExecute2Thread() throws InterruptedException {
        final CASCount count = new CASCount();
        Thread first = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                count.increment();
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                count.increment();
            }
        });

        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(count.get(), 10);
    }
}
