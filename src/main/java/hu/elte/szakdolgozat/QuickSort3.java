package hu.elte.szakdolgozat;

public class QuickSort3 extends QuickSort{

    public QuickSort3(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        super.setRandomize(true);
        super.runTest();
    }
}