package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public int getColSum() {
            return colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int length = matrix.length;
        Sums[] rsl = new Sums[length];
        for (int i = 0; i < length; i++) {
            rsl[i] = new Sums();
            for (int j = 0; j < length; j++) {
                rsl[i].rowSum += matrix[i][j];
                rsl[i].colSum += matrix[j][i];
            }
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int length = matrix.length;
        Sums[] sums = new Sums[length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < length; i++) {
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int line) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sum = new Sums();
            for (int i = 0; i < data.length; i++) {
                sum.rowSum += data[line][i];
                sum.colSum += data[i][line];
            }
            return sum;
        });
    }
}