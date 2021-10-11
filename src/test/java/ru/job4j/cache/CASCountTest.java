package ru.job4j.cache;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
}
