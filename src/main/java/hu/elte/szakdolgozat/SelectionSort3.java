package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.SELECTIONS;

public class SelectionSort3 extends SortBase{

    public SelectionSort3(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;
        writeSelection = true;

        SortHeleper.SearchTarget target = SortHeleper.SearchTarget.MAXIMUM;

//        Integer arr[] = {1,2,3,4,5,6,8,7};

//        for (int i = 0; i < arr.length - 1; i++)
        for (int i = arr.length - 1 ; i > -1; i--)
        {
            //find item
            boolean sorted = true;
            int index = 0;
            for (int j=1; j<i+1; j++)
            {
                counterVo.addToCounter(COMPARISONS);
                if ((arr[j] < arr[index] && SortHeleper.SearchTarget.MAXIMUM.equals(target)) ||
                        (arr[j] > arr[index] && SortHeleper.SearchTarget.MINIMUM.equals(target))){
                    sorted = false;
                }else {
                    index = j;
                    counterVo.addToCounter(SELECTIONS);
                }
            }

            if (sorted && index == i) {
                break;
            }

            //move item
            swap(index,i);
        }

    }

}