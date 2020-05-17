package hu.elte.szakdolgozat;

public class BubbleSort3 extends SortBase{

    public BubbleSort3() throws Exception {
        super();
    }

    public BubbleSort3(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        for (int n = arr.length; n > 1; n--) {
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    int tmp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
    }
}
