package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

public class RowColSumTest {

    @Test
    public void whenMatrixRow1Sums() {
        int[][] matrix = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        int rsl = sums[1].getRowSum();
        Assert.assertEquals(rsl, 8);
    }

    @Test
    public void whenAsyncMatrixRow1Sums() throws ExecutionException, InterruptedException {
        int[][] matrix = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        int rsl = RolColSum.asyncSum(matrix)[1].getRowSum();
        Assert.assertEquals(rsl, 8);
    }

    @Test
    public void whenMatrixCol0Sums() {
        int[][] matrix = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        int rsl = sums[0].getColSum();
        Assert.assertEquals(rsl, 20);
    }

    @Test
    public void whenAsyncMatrixCol0Sums() throws ExecutionException, InterruptedException {
        int[][] matrix = {{5, 7, 3}, {7, 0, 1}, {8, 1, 2}};
        int rsl = RolColSum.asyncSum(matrix)[0].getColSum();
        Assert.assertEquals(rsl, 20);
    }
}
