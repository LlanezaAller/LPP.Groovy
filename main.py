#!/usr/bin/python
# -*- coding: utf-8 -*-

from __future__ import print_function, division, absolute_import

import bench

# 0 => MonteCarlo
# 1 => BinaryTrees
# 2 => Fibonacci
mode = 2

# 0 => Java
# 1 => Groovy
language = 1

# Only for groovy
# 0 => Dynamic
# 1 => Static
isStatic = 1

def show(interval, mean, sdev, error_percentage):
    print("\tInterval: {}.".format(interval))
    print("\tMean: {}.".format(mean))
    print("\tStandard deviation: {}.".format(sdev))
    print("\tError percentage: {:.2f}%.".format(error_percentage))

if __name__ == "__main__":
    # Parameters
    print("------STARTUP------")
    if language == 0:
        interval, mean, sdev, error_percentage = bench.startup("java Benchmark", 0.95, 30, 0.02, mode, -1)
    else:
        if isStatic:
            interval, mean, sdev, error_percentage = bench.startup("groovy BenchmarkStatic", 0.95, 30, 0.02, mode, isStatic)
        else:
            interval, mean, sdev, error_percentage = bench.startup("groovy Benchmark.groovy", 0.95, 30, 0.02, mode, isStatic)
    print("Results Startup:")
    show(interval, mean, sdev, error_percentage)

    print("------STEADY-STATE------")
    if language == 0:
        interval, mean, sdev, error_percentage = bench.steady("java Benchmark", 0.95, 10, 0, 30, 10, 0.02, mode, -1)
    else:
        if isStatic:
            interval, mean, sdev, error_percentage = bench.steady("groovy BenchmarkStatic", 0.95, 30, 0, 30, 10, 0.02, mode, isStatic)
        else:
            interval, mean, sdev, error_percentage = bench.steady("groovy Benchmark.groovy", 0.95, 30, 0, 30, 10, 0.02, mode, isStatic)
    print("Results Steady-state:")
    show(interval, mean, sdev, error_percentage)

