package hu.elte.szakdolgozat;

import java.util.List;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.*;

public class ResultWriter extends SortBase{
    String name;

    public ResultWriter(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public ResultWriter(List<Double> valueList, String name) throws Exception {
        super(1, valueList.size(), 1, 1);

        this.name = name;
        int i = 0;

        for(double value : valueList){
            counterVo.setCurrentRound(i);
            counterVo.setCounter(TIME, value);
            i++;
        }
    }

    @Override
    public void runTest(){
    }

    @Override
    public String  getName() {
        return name;
    }
}
