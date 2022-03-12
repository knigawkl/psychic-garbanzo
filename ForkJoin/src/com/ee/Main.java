package com.ee;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static void main(String[] args) {

        int length = 10000;
        int[] nums = initializeNums(length);
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

    private static int[] initializeNums(int length) {

        Random random = new Random();

        int[] nums = new int[length];

        for (int i = 0; i < length; ++i)
            nums[i] = random.nextInt(100);

        return nums;
    }
}
