package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        String email = user.getEmail();
        String username = user.getUsername();
        String subject = "Notification " + username + " to email" + email;
        String body = " Add a new event to " + username;

        pool.submit(new Runnable() {
            @Override
            public void run() {
                send(subject, body, email);
            }
        });

    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void send(String subject, String body, String email) {
    }
}
