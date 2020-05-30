package hu.elte.szakdolgozat;

public class QuickSort extends SortBase{
    private boolean randomize = false;

    public QuickSort(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public QuickSort() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        quicksort(0, arr.length - 1);
    }

    public void quicksort(int lo, int hi) {
        if (lo < hi) {
            int p = partition(lo, hi,isRandomize());
            quicksort(lo, p - 1);
            quicksort(p + 1, hi);
        }
    }

    public boolean isRandomize() {
        return randomize;
    }

    public QuickSort setRandomize(boolean randomize) {
        this.randomize = randomize;
        return this;
    }
}