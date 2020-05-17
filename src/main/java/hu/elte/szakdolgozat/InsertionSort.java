package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.MOVES;

public class InsertionSort extends SortBase{

    public InsertionSort(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public InsertionSort() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;

        for (int i=1; i<arr.length; ++i)
        {
            if(arr[i-1] > arr[i])
                counterVo.addToCounter(MOVES,2);

            int key = arr[i];
            int j = i-1;

            while (j>=0 && compare(arr[j],key))
            {
                arr[j+1] = arr[j];
                counterVo.addToCounter(MOVES);

                j = j-1;
            }

            arr[j+1] = key;
        }
    }

    public boolean compare(int A, int B){
        counterVo.addToCounter(COMPARISONS);
        return A > B;
    }
}