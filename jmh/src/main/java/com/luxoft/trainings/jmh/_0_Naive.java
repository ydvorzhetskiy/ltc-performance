package com.luxoft.trainings.jmh;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class _0_Naive {

    public static void main(String[] args) {
        long start = System.nanoTime();
        Integer[] arr = ThreadLocalRandom.current()
                                         .ints(10_000)
                                         .boxed()
                                         .toArray(Integer[]::new);

        Arrays.sort(arr);
        long operationInNanos = System.nanoTime() - start;
        System.out.println("One operation takes " + operationInNanos + " ns");
    }
}
