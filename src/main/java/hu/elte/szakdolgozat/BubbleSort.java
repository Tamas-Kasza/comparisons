package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;

public class BubbleSort extends SortBase{

    public BubbleSort() throws Exception {
        super();
    }

    public BubbleSort(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;

        bubblesort();
    }

    public void bubblesort(){
        for (int n = arr.length; n > 1; n--) {
            for (int i = 1; i < n; i++) {

                counterVo.addToCounter(COMPARISONS);
                if (arr[i - 1] > arr[i]) {
                    swap(i - 1, i);
                }

            }
        }
    }
}
