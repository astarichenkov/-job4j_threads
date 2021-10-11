package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount() {
        this.count.set(0);
    }

    public CASCount(int value) {
        this.count.set(value);
    }

    public void increment() {
        Integer ref;
        int value;
        do {
            ref = count.get();
            value = ref + 1;
        } while (!count.compareAndSet(ref, value));
    }

    public int get() {
        return count.get();
    }
}