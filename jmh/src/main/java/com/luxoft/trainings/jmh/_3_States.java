package com.luxoft.trainings.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class _3_States {

    @State(Scope.Benchmark)
    public static class BenchmarkState {

        // Many threads will try to read / write this field
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    @Benchmark
    @Threads(4)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureUnshared(ThreadState state) {
        state.x++;
    }

    @Benchmark
    @Threads(4)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    @Fork(1)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void measureShared(BenchmarkState state) {
        state.x++;
    }
}
