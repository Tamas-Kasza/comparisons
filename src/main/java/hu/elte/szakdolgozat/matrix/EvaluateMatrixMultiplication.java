package hu.elte.szakdolgozat.matrix;

import hu.elte.szakdolgozat.CounterVo;
import hu.elte.szakdolgozat.IBase;
import hu.elte.szakdolgozat.SortHeleper;

public class EvaluateMatrixMultiplication implements IBase {
    private Integer minMatrixSize;
    private Integer maxMatrixSize;
    private Integer numOfRuns;
    private Integer stepSize;

    Long[] fRuns;
    Long[] mRuns;

    public EvaluateMatrixMultiplication(Integer minMatrixSize, Integer maxMatrixSize, Integer numOfRuns, Integer stepSize) {
        this.minMatrixSize = minMatrixSize;
        this.maxMatrixSize = maxMatrixSize;
        this.numOfRuns = numOfRuns;
        this.stepSize = stepSize;

        fRuns = new Long[maxMatrixSize];
        mRuns = new Long[maxMatrixSize];
    }

    public void run(int size) {
        FreivaldsAlgorithm f = new FreivaldsAlgorithm();
        MultiplyMatrices m = new MultiplyMatrices();

        int[][] firstMatrix = new int[size][size];
        int[][] secondMatrix = new int[size][size];

        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++){
                firstMatrix[i][j] = SortHeleper.random.nextInt(10);
                secondMatrix[i][j] = SortHeleper.random.nextInt(10);
            }
        }

        int[][] product = m.multiplyMatrices(firstMatrix, secondMatrix, size, size, size);
        mRuns[size-1] = m.getCounter();

        f.freivalds(firstMatrix,secondMatrix,product,numOfRuns);
        fRuns[size-1] = f.getCounter();
    }

    @Override
    public void doWork() {
        for (int i = minMatrixSize ; i < maxMatrixSize + 1; i+=stepSize) {
            System.out.println("calculating round: "+i);
            run(i);
        }
    }

    @Override
    public void doWorkAndBenchmarkTime() {}

    @Override
    public String getName() {
        return "EvalMul";
    }

    @Override
    public CounterVo getcounters() {
        return null;
    }

    @Override
    public Integer getMinListLength() {
        return minMatrixSize;
    }

    @Override
    public Integer getMaxListLength() {
        return maxMatrixSize;
    }

    @Override
    public Integer getNumOfRuns() {
        return numOfRuns;
    }

    @Override
    public Integer getStepSize() {
        return stepSize;
    }

    @Override
    public Number[] getCounterAsArray(int i) {
        switch(i) {
            case 0:
                return fRuns;
            case 1:
                return mRuns;
            default:
                return null;
        }

    }
}
