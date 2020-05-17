package hu.elte.szakdolgozat;

public class BubbleSort2 extends BubbleSort{

    public BubbleSort2(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;

        shuffle();
        bubblesort();
    }
}
