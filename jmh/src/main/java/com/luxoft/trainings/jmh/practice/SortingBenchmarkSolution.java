/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.luxoft.trainings.jmh.practice;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@State(Scope.Thread)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 3, time = 1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class SortingBenchmarkSolution {

    @Param({"100", "1000", "10000"})
    public int length;

    @Param({"asc", "desc", "rand"})
    public String generator;

    private Integer[] arr;

    @Setup(Level.Invocation)
    public void init() {
        switch (generator) {
            case "asc":
                arr = IntStream.range(0, length).boxed().toArray(Integer[]::new);
                break;

            case "desc":
                arr = IntStream.iterate(length - 1, i -> i - 1).limit(length).boxed().toArray(Integer[]::new);
                break;

            case "rand":
                arr = ThreadLocalRandom.current().ints(length).boxed().toArray(Integer[]::new);
        }
    }

    @Fork(1)
    @Benchmark
    public Integer[] bubbleSort() {
        SortingAlgorithm.bubbleSort(arr);
        return arr;
    }

    @Fork(value = 1, jvmArgs = "-Djava.util.Arrays.useLegacyMergeSort=true")
    @Benchmark
    public Integer[] mergeSort() {
        Arrays.sort(arr);
        return arr;
    }

    @Fork(1)
    @Benchmark
    public Integer[] timSort() {
        Arrays.sort(arr);
        return arr;
    }

    @Fork(1)
    @Benchmark
    public Integer[] quickSort() {
        SortingAlgorithm.quickSort(arr);
        return arr;
    }
}
