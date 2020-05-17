package hu.elte.szakdolgozat;

public class MFProblema extends SortBase{

    public MFProblema(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    public void runTest(){
        writeSelection = true;

        findPositionInInternalArray(0,SortHeleper.SearchTarget.MAXIMUM);
    }
}
