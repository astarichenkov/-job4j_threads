package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

public class ParallelIndexSearchTest {
    @Test
    public void whenSearchElementIsPresent() {
        Integer[] array = new Integer[150];
        for (int i = 0; i < 150; i++) {
            array[i] = i;
        }
        int value = 222;
        array[4] = value;
        int rsl = ParallelIndexSearch.findIndex(array, value);
        Assert.assertEquals(rsl, 4);
    }

    @Test
    public void whenSearchElementIsNotPresent() {
        Integer[] array = new Integer[150];
        for (int i = 0; i < 150; i++) {
            array[i] = i;
        }
        int value = 777;
        int rsl = ParallelIndexSearch.findIndex(array, value);
        Assert.assertEquals(rsl, -1);
    }
}
