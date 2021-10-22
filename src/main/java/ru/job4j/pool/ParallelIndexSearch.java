package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T value;

    public ParallelIndexSearch(T[] array, int from, int to, T value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, from, mid, value);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, mid + 1, to, value);
        leftSearch.fork();
        rightSearch.fork();
        int left = leftSearch.join();
        int right = leftSearch.join();

        return Math.max(left, right);
    }

    private int linearSearch() {
        for (int i = from; i <= to; i++) {
            if (value.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findIndex(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<>(array, 0, array.length - 1, value));
    }
}
