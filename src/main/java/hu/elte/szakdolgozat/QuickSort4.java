package hu.elte.szakdolgozat;

public class QuickSort4 extends SortBase{
    private boolean randomize = false;

    public QuickSort4(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public QuickSort4() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        quicksort(0, arr.length - 1);
    }

    public void quicksort(int lo, int hi) {
        if (lo < hi) {
            int pivot = arr[hi];
            int p = lo;
            for (int j = lo ; j < hi; j++) {
                if (arr[j] < pivot) {
                    if(p != j){
                        int tmp = arr[j];
                        arr[j] = arr[p];
                        arr[p] = tmp;
                    }
                    p++;
                }
            }
            if(p != hi) {
                int tmp = arr[hi];
                arr[hi] = arr[p];
                arr[p] = tmp;
            }
            quicksort(lo, p - 1);
            quicksort(p + 1, hi);
        }
    }
}