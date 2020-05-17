package hu.elte.szakdolgozat;

import static hu.elte.szakdolgozat.SortHeleper.randomBetween;

public class SelectNthElement extends SortBase{

    public SelectNthElement(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;

        int rndIndx = randomBetween(0, arr.length - 1);
        rndSelection(0, arr.length - 1,rndIndx);
    }

    public void rndSelection(int lo, int hi,int k) {
        if(lo == hi){return;}

        Integer p = partition(lo,hi,true);
        if(p > k){
            rndSelection(lo, p-1,k);
        }
        else if(p < k){
            rndSelection(p+1,hi,k);
        }
    }
}
