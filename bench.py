#!/usr/bin/python
# -*- coding: utf-8 -*-

from __future__ import print_function, division, absolute_import

import scipy
import scipy.stats.distributions as distributions
import math
import os
import time
from subprocess import PIPE, Popen

# 0 => MonteCarlo
# 1 => BinaryTrees
# 2 => Fibonacci

#mode = 0

def confidence(samples, confidence_level):
    """This function determines the confidence interval for a given set of samples,
    as well as the mean, the standard deviation, and the size of the confidence
    interval as a percentage of the mean.
    """
    mean = scipy.mean(samples)
    sdev = scipy.std(samples)
    n = len(samples)
    df = n - 1
    t = distributions.t.ppf((1+confidence_level)/2.0, df)
    # t = distributions.norm.ppf((1+confidence_level)/2.0, df)
    interval = (interval_low, interval_high) = mean - t * sdev / math.sqrt(n) , mean + t * sdev / math.sqrt(n)
    interval_size = interval_high - interval_low
    error_percentage = interval_size / mean * 100.0
    values = [value for value in samples if value >= interval_low and value <= interval_high] if len(samples) > 1 else samples
    return interval, scipy.mean(values), scipy.std(values), error_percentage

def startup(command, confidence_level, p_iterations, break_if_error_percentage_is, mode, static):
    execution_times = []
    command += " " + str(mode) 
    if static > -1:
        command += " " + str(static)
    for i in range(1, p_iterations+1):
        before = time.time()
        os.system(command)
        after = time.time()
        execution_time = (after-before)*1000
        print("Iteration %s. Times in millis %s." % (i, execution_time))
        execution_times.append(execution_time)
        # ??????
        interval, mean, sdev, error_percentage = confidence(execution_times, confidence_level)
        if error_percentage <= break_if_error_percentage_is:
            break
    return interval, mean, sdev, error_percentage

def steady(command, confidence_level, p_iterations, break_if_error_percentage_is, max_bench_invocations, k, CoV, mode, static):
    params = " " + str(mode) 
    if static > -1:
        params += " " + str(static)
    command += params + " " + str(max_bench_invocations) + " " + str(k) + " " + str(CoV)
    execution_times = []
    for i in range(1, p_iterations+1):
        process = Popen(args=command, stdout=PIPE, shell=True)
        process_std_output = process.communicate()[0]
        execution_time = int(process_std_output.splitlines()[-1])
        print("Iteration %s. Times in millis %s." % (i, execution_time))
        execution_times.append(execution_time)
        interval, mean, sdev, error_percentage = confidence(execution_times, confidence_level)
        if error_percentage <= break_if_error_percentage_is:
            break
    return interval, mean, sdev, error_percentage
