package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.*;

public class PrintPredictions extends SortBase{
    private int mode;

    public PrintPredictions(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize, int mode) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
        this.mode = mode;
    }

    @Override
    protected void runTestLoop(int i) {
        counterVo.setCurrentRound(i);
        int n = i + 1;
        switch(mode) {
            case 0:
                predictSelections(n);
                break;
            case 1:
                predictComparisons(n);
                break;
            case 2:
                predictComparisons139(n);
                break;
            case 3:
                counterVo.setCounter(COMPARISONS, Double.valueOf(n));
                break;
            case 4:
                counterVo.setCounter(COMPARISONS, Double.valueOf(4*n));
                break;
            default:
        }
    }

    @Override
    public void runTest(){
        writeSelection = true;
    }
}
