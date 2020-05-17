package hu.elte.szakdolgozat;

public class EulerConst extends SortBase{

    public EulerConst(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    @Override
    protected void runTestLoop(int i) {
        counterVo.setCurrentRound(i);
        counterVo.setCounter(SortHeleper.CounterType.SELECTIONS,EULER_MASCHERONI_CONSTANT);
    }

    @Override
    public void runTest(){
        writeSelection = true;
    }
}
