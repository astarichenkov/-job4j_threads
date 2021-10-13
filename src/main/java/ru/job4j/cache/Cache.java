package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Integer id = model.getId();
        Base stored = memory.get(id);
        memory.computeIfPresent(id, (key, value) -> {
            if (model.getVersion() != stored.getVersion()) {
                throw new OptimisticException("Модель была ранее изменена");
            }
            model.incrementVersion();
            return model;
        });
        return true;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public boolean isEmpty() {
        return memory.isEmpty();
    }
}