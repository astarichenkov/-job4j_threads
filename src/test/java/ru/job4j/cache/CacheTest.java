package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

public class CacheTest {

    @Test
    public void whenAddAndDelete() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.delete(base);
        Assert.assertTrue(cache.isEmpty());
    }

    @Test
    public void whenUpdateSimilarVersions() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        base = new Base(1, 0);
        cache.update(base);
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateDifferentVersions() {
        Cache cache = new Cache();
        Base base = new Base(1, 0);
        cache.add(base);
        cache.update(base);
        base = new Base(1, 7);
        cache.update(base);
    }
}
