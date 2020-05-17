package hu.elte.szakdolgozat.support;

import java.lang.System;
import java.util.ArrayList;

import hu.elte.szakdolgozat.SortBase;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import static hu.elte.szakdolgozat.SortHeleper.fillArray;

public class Benchmark {
    private double timeit(SortBase fn, long number) {//hany masodperc x kor

        System.gc();
        long before = System.nanoTime();
        for (int i = 0; i < number; i++) {
            fn.shuffle();
            fn.runTest();
        }
        long after = System.nanoTime();
        return (after - before) / 1000000000f;
    }

    private double timeitfor(SortBase fn) {
        double time = 0;
        long iter = 1;
        while (true) {
            System.out.println(time);
            time = timeit(fn, iter);
            if (time > 1.0) {
                return time / iter;
            }
            iter *= 2;
        }
    }

    private int calcRuns(SortBase fn, double min_delta) {//min_delta ms hez hany kor kell
        double eps = min_delta * 0.01;
        int r = 1;
//
//        if (timeit(fn, 1) > min_delta) {
//            return 1;
//        }

        while (true) {
            double d = timeit(fn, r);
            System.out.printf("%f, %d\n", d, r);
            if (Math.abs(d - min_delta) > eps) {
                r = (int) (min_delta / (d / r));
            } else {
                return r;
            }
        }
    }

    private double calcMeanCI(SummaryStatistics stats, double level) {// t teszt konfidencia intervallum
        try {
            TDistribution tDist = new TDistribution(stats.getN() - 1);
            double critVal = tDist.inverseCumulativeProbability(1.0 - (1 - level) / 2);
            return critVal * stats.getStandardDeviation() / Math.sqrt(stats.getN());
        } catch (MathIllegalArgumentException e) {
            return Double.NaN;
        }
    }

    public double benchmark(SortBase fn, double error) {
        return timeitfor(fn);
        /*
        double quantile = 0.95;
        double min_delta = 1;//0.01 = 100 ms  1 = 1ms
        long min_samples = 10;//2
        int r = calcRuns(fn, min_delta);//min_delta ms futas hany kor
        SummaryStatistics m = new SummaryStatistics();

        for (long i = 0; i < min_samples; i++) {
            m.addValue(timeit(fn, r) / r);
        }

//        112 * 10^-12;
//        ------------;
//        1 21 * 10^-12;
//
//        1.10 +- 0.01
//        (l/M,    1,    h/M)


        while (true) {
            double ci = calcMeanCI(m, quantile);
            double lower = m.getMean() - ci;
            double upper = m.getMean() + ci;

            if ((upper - lower) / m.getMean() < error) {
                return m.getMean();//igazi atlag
            }
            // System.out.printf("[%g, (%g : %d), %g], %g > %g\n", lower, m.getMean(), (long) (1 / m.getMean()), upper, ((upper - lower) / m.getMean()), error);
            System.out.printf("[%d, %d, %d], %g <? %g\n", (long) (1 / upper), (long) (1 / m.getMean()), (long) (1 / lower), ((upper - lower) / m.getMean()), error);
            m.addValue(timeit(fn, r) / r);
        }*/
    }
}