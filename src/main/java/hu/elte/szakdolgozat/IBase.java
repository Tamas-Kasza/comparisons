package hu.elte.szakdolgozat;

public interface IBase {
    void doWork() throws Exception;

    void doWorkAndBenchmarkTime() throws Exception;

    String  getName();

    CounterVo getcounters();

    Integer getMinListLength();

    Integer getMaxListLength();

    Integer getNumOfRuns();

    Integer getStepSize();

    Number[] getCounterAsArray(int i);
}
