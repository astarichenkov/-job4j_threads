package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> userMap;

    public UserStorage(Map<Integer, User> userMap) {
        this.userMap = userMap;
    }

    public synchronized boolean add(User user) {
        return userMap.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return userMap.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return userMap.remove(user.getId()) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User one = userMap.get(fromId);
        User two = userMap.get(toId);
        if (one != null && two != null && one.getAmount() >= amount) {
            one.setAmount(one.getAmount() - amount);
            two.setAmount(two.getAmount() + amount);
            return true;
        }
        return false;
    }
}

