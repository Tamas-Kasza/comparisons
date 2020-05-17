package hu.elte.szakdolgozat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;
import static hu.elte.szakdolgozat.SortHeleper.CounterType.SELECTIONS;

public class SelectionSort2 extends SortBase{

    public SelectionSort2(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;
        writeSelection = true;

        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));
        Collections.shuffle(list);
        setArray(list);

        SortHeleper.SearchTarget target = SortHeleper.SearchTarget.MAXIMUM;

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