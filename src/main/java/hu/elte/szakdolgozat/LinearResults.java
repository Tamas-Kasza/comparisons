package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.MOVES;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.SELECTIONS;

public class LinearResults extends SortBase{

    public LinearResults(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;
        writeSelection = true;
        int n = arr.length;
        counterVo.addToCounter(COMPARISONS, n);
        counterVo.addToCounter(MOVES, n);
        counterVo.addToCounter(SELECTIONS, n);
    }
}
