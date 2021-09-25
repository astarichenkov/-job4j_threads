package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload implements Runnable {
    private final String url;
    private final int speed;
    private final String file;

    public FileDownload(String url, int speed, String file) {
        this.url = url;
        this.speed = speed;
        this.file = file;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            byte[] dataBuffer = new byte[speed];
            int bytesRead;
            long readTime;
            long stop;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                stop = System.currentTimeMillis();
                readTime = stop - start;
                System.out.println(readTime);
                if (readTime < 1000) {
                    Thread.sleep(1000 - readTime);
                }
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                start = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Usage: "
                    + "java -jar FileDownload.jar "
                    + "URL "
                    + "DOWNLOAD_SPEED (bytes per second) "
                    + "DEST_FILE");
        }

        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String file = args[2];
        Thread wget = new Thread(new FileDownload(url, speed, file));
        wget.start();
        wget.join();
    }
}