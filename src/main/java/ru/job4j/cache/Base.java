package ru.job4j.cache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Base {
    private final int id;
    private final AtomicInteger version;
    private String name;

    public Base(int id, int version) {
        this.id = id;
        this.version = new AtomicInteger(version);
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version.get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void incrementVersion() {
        this.version.incrementAndGet();
    }
}