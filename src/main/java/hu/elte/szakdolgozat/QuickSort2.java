package hu.elte.szakdolgozat;

import java.util.ArrayList;
import java.util.Arrays;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.COMPARISONS;

public class QuickSort2 extends SortBase {
    public QuickSort2(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;

        quicksort(new ArrayList<>(Arrays.asList(arr)));
    }

    public ArrayList<Integer> quicksort(ArrayList<Integer> array) {

        if (array.size() <= 1){
            return array;
        }

        ArrayList<Integer>  less    = new ArrayList<>();
        ArrayList<Integer>  equal   = new ArrayList<>();
        ArrayList<Integer>  greater = new ArrayList<>();

        Integer pivot = array.get(SortHeleper.random.nextInt(array.size()));

        for (Integer x : array){
            if (x < pivot) {counterVo.addToCounter(COMPARISONS);less.add(x);}
            else if (x > pivot) {counterVo.addToCounter(COMPARISONS);greater.add(x);}
            else {equal.add(x);}
        }

        return concatenate(quicksort(less), equal, quicksort(greater));
    }
}
