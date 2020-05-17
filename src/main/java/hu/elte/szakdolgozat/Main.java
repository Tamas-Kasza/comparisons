package hu.elte.szakdolgozat;

import hu.elte.szakdolgozat.coordinates.PointVo;
import hu.elte.szakdolgozat.matrix.EvaluateMatrixMultiplication;
import hu.elte.szakdolgozat.tests.SortTest;
import org.testng.TestNG;

import java.io.File;
import java.util.ArrayList;

import static hu.elte.szakdolgozat.SortHeleper.*;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.*;

public class Main {

    final static String VERSION = "v1.3";

    public static void main(String[] args) throws Exception {

        File directory = new File(OUTFOLDER+ "/testNg");
        directory.mkdirs();

        if(args.length != 5){
            System.out.printf("[ver: %s] usage: java -jar sort.jar [minListLength] [maxListLength] [numOfRuns] [stepsize] [mode]", VERSION);
            System.out.printf("1:mmul, 2:sorting, 3:sorting time, 4:mfproblema, 5:qsort, 6:knn, 7:selftest");
            return;
        }

        try{
            Integer minListLength = Integer.parseInt(args[0]);
            Integer maxListLength = Integer.parseInt(args[1]);
            Integer numOfRuns     = Integer.parseInt(args[2]);
            Integer stepSize     = Integer.parseInt(args[3]);

            Integer mode = Integer.parseInt(args[4]);
            ArrayList<IBase> tests = new ArrayList<>();

            switch(mode) {
                case 1:
                    // matrix multiplication
                    tests.add(new EvaluateMatrixMultiplication(minListLength,maxListLength,numOfRuns,stepSize));
                    runCalculations(tests);
                    exportToFile(tests,"mmul", VERSION, MATRIX);
                    break;
                case 2:
                    // sorting cmp and moves
                    tests.add(new BubbleSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new InsertionSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new SelectionSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new QuickSort(minListLength,maxListLength,numOfRuns,stepSize));
                    runCalculations(tests);
                    exportToFile(tests,"moves", VERSION, MOVES);
                    exportToFile(tests,"comparisons", VERSION, COMPARISONS);
                    break;
                case 3:
                    // sorting time
                    tests.add(new BubbleSort3(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new InsertionSort2(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new SelectionSort4(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new QuickSort4(minListLength,maxListLength,numOfRuns,stepSize));
                    benchmarkTime(tests);
                    exportToFile(tests,"time",VERSION, TIME);
                    break;
                case 4:
                    //munkatarsfelvetel
                    tests.add(new SelectMax(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new PrintPredictions(minListLength,maxListLength,numOfRuns,stepSize,0));
                    runCalculations(tests);
                    exportToFile(tests,"selections", VERSION, SELECTIONS);
                    exportToFile(tests,"diff", VERSION, DIFF);
                    break;
                case 5:
                    //quicksort abra
                    tests.add(new PrintPredictions(minListLength,maxListLength,numOfRuns,stepSize,1));
                    tests.add(new QuickSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new PrintPredictions(minListLength,maxListLength,numOfRuns,stepSize,2));
                    runCalculations(tests);
                    exportToFile(tests,"comparisons", VERSION, COMPARISONS);
                    exportToFile(tests,"diff", VERSION, DIFF2);
                    break;
                case 6:
                    //knn abra
                    tests.add(new PrintPredictions(minListLength,maxListLength,numOfRuns,stepSize,3));
                    tests.add(new SelectNthElement(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new PrintPredictions(minListLength,maxListLength,numOfRuns,stepSize,4));
                    runCalculations(tests);
                    exportToFile(tests,"comparisons", VERSION, COMPARISONS);
                    exportToFile(tests,"diff", VERSION, DIFF2);
                    break;
                case 7:
                    TestNG testNG = new TestNG();
                    testNG.setTestClasses(new Class[] { SortTest.class });
                    testNG.setDefaultSuiteName("sort test suite");
                    testNG.setDefaultTestName("sort test");
                    testNG.setOutputDirectory(OUTFOLDER+ "/testNg");
                    testNG.run();
                    break;
                default:
                    tests.add(new BubbleSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new SelectionSort2(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new SelectionSort3(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new QuickSort(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new QuickSort2(minListLength,maxListLength,numOfRuns,stepSize));
                    //randomizalt
                    tests.add(new QuickSort3(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new MFProblema(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new SelectNthElement(minListLength,maxListLength,numOfRuns,stepSize));
                    tests.add(new LinearResults(minListLength,maxListLength,numOfRuns,stepSize));

                    runCalculations(tests);
                    benchmarkTime(tests);

                    ArrayList<PointVo> pointList = SortHeleper.toPointList(tests);

                    System.out.println("linearity " + linearity(pointList));

                    exportToFile(tests,"moves1", VERSION, MOVES);
                    exportToFile(tests,"time",VERSION, TIME);
                    exportToFile(tests,"comparisons0301", VERSION, COMPARISONS);
                    exportToFile(tests,"diff0216", VERSION, DIFF);
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument must be an integer.");
            System.exit(1);
        }
    }

    static void runCalculations(ArrayList<IBase> tests) throws Exception{
        for(IBase test : tests){
            test.doWork();
        }
    }

    static void benchmarkTime(ArrayList<IBase> tests) throws Exception{
        for(IBase test : tests){
            test.doWorkAndBenchmarkTime();
        }
    }
}

