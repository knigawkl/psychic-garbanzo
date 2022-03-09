package com.ee;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        int[] nums = initializeNums();
        MergeSort mergesort = new MergeSort(nums);

        long start = System.currentTimeMillis();
        mergesort.sort(nums);
        System.out.println("Time taken with sequential sort: " + (System.currentTimeMillis() - start) + "ms");

        MergeSortTask rootTask = new MergeSortTask(nums);
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        start = System.currentTimeMillis();
        pool.invoke(rootTask);
        System.out.println("Time taken with concurrent sort: " + (System.currentTimeMillis() - start) + "ms");
    }

    private static int[] initializeNums() {

        Random random = new Random();

        int[] nums = new int[100000000];

        for (int i = 0; i < 100000000; ++i)
            nums[i] = random.nextInt(100);

        return nums;
    }
}
