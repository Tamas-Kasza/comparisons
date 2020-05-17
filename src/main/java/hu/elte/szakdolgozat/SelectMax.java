package hu.elte.szakdolgozat;

public class SelectMax extends SortBase{

    public SelectMax(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public SelectMax() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;

        findPosOfMax(0);

    }

}