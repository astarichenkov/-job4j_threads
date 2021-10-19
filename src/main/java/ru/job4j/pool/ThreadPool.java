package ru.job4j.pool;

import ru.job4j.wait.queue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(new JobWorker()));
            threads.forEach(Thread::start);
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private final class JobWorker implements Runnable {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable nextTask = null;
                try {
                    nextTask = tasks.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (nextTask != null) {
                    nextTask.run();
                }
            }
        }
    }
}