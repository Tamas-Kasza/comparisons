package hu.elte.szakdolgozat;

import hu.elte.szakdolgozat.support.Benchmark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static hu.elte.szakdolgozat.SortHeleper.CounterType.*;
import static hu.elte.szakdolgozat.SortHeleper.*;
import static hu.elte.szakdolgozat.math.MathHelper.log2;
import static java.lang.Math.log;

public abstract class SortBase implements IBase {
    protected static Integer arr[];

    private Integer minListLength;
    private Integer maxListLength;
    private Integer numOfRuns;
    private Integer stepSize;

    protected CounterVo counterVo;

    protected Boolean writeComparisons = false;
    protected Boolean writeMoves = false;
    protected Boolean writeSelection = false;

    public static Double EULER_MASCHERONI_CONSTANT = 0.57721566490153286060651209008240243104215933593992;

    public SortBase() throws Exception{counterVo = new CounterVo(1);}

    public SortBase(Integer minListLength,Integer maxListLength,Integer numOfRuns,Integer stepSize) throws Exception{

        counterVo = new CounterVo(maxListLength);

        this.minListLength = minListLength;
        this.maxListLength = maxListLength;
        this.numOfRuns = numOfRuns;
        this.stepSize = stepSize;
    }

    @Override
    public String toString() {
        String ret = this.getClass().toString()+" ";
        for (int i=0; i<arr.length; ++i) {
            ret += (arr[i] + " ");
        }
//        ret += ("comparisons :" + comparisons + " ");
//        ret += ("moves :" + moves + " ");
        ret +="\n";
        return ret;
    }

    public abstract void runTest();

    public void shuffle(){
        ArrayList<Integer> tmpList = new ArrayList<Integer>(Arrays.asList(arr));
        Collections.shuffle(tmpList);
        setArray(tmpList);
    }

    public ArrayList<Integer[]> arrShuffles(int number){
        ArrayList<Integer> orig = new ArrayList<Integer>(Arrays.asList(arr));
        ArrayList<Integer[]> result = new ArrayList<>();

        for(int i=0;i<number;i++){
            Collections.shuffle(orig);
            result.add(orig.toArray(new Integer[orig.size()]));
        }

        return result;
    }

    public Integer[] getShuffledArr(){
        ArrayList<Integer> orig = new ArrayList<Integer>(Arrays.asList(arr));

        Collections.shuffle(orig);
        return orig.toArray(new Integer[orig.size()]);
    }

    public void swap(Integer a, Integer b){
        Integer tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        counterVo.addToCounter(MOVES, 3);
    }


    public int partition(int lo, int hi,Boolean randomize) {
        if(randomize){
            int rndIndx = randomBetween(lo,hi);
            swap(rndIndx,hi);
        }

        Integer pivot = arr[hi];
        int i = lo;
        for (int j = lo ; j < hi; j++) {
            counterVo.addToCounter(COMPARISONS);
            if (arr[j] < pivot) {
                if(i != j){
                    swap(j,i);
                }
                i++;
            }
        }
        if(i != hi) {
            swap(hi, i);
        }
        return i;
    }

    public void printPartition(int lo, int hi) {
        System.out.print(lo + " : " + hi +" - ");
        for (int j = lo ; j <= hi; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }

    public int[] concatenate(Integer[] ...arrays ) {
        int length = 0;
        for ( Integer[] a: arrays )
            length += a.length;

        int[] result = new int[length];

        int destPos = 0;
        for ( Integer[] a: arrays ){
            System.arraycopy(a, 0, result, destPos, a.length);
            destPos += a.length;
        }

        return result;
    }

    public ArrayList<Integer> concatenate(ArrayList<Integer> ...arrays ) {

        ArrayList<Integer> result = new ArrayList<>();

        for (ArrayList<Integer> a: arrays ){
            result.addAll(a);
        }

        return result;
    }

    public Integer findPositionInInternalArray(Integer startIndex, SortHeleper.SearchTarget target){
        int index = startIndex;
        for (int j=startIndex + 1; j<arr.length; j++)
        {
            counterVo.addToCounter(COMPARISONS);
            if ((arr[j] > arr[index] && SortHeleper.SearchTarget.MAXIMUM.equals(target)) ||
                (arr[j] < arr[index] && SortHeleper.SearchTarget.MINIMUM.equals(target)))
            {
                index = j;
                counterVo.addToCounter(SELECTIONS);
            }
        }
        return index;
    }

    public Integer findPosOfMin(Integer index_){
        Integer index = index_;
        counterVo.addToCounter(SELECTIONS);
        for (int i=index+1; i<arr.length; i++)
        {
            counterVo.addToCounter(COMPARISONS);
            if (arr[i] < arr[index])
            {
                index = i;
                counterVo.addToCounter(SELECTIONS);
            }
        }
        return index;
    }

    public Integer findPosOfMax(int index_){
        Integer index = index_;
        counterVo.addToCounter(SELECTIONS);
        for (int j=index+1; j<arr.length; j++)
        {
            counterVo.addToCounter(COMPARISONS);
            if (arr[j] > arr[index])
            {
                index = j;
                counterVo.addToCounter(SELECTIONS);
            }
        }
        return index;
    }

    public boolean isArrayinAscOrder(){
        for (int i=0; i<(arr.length-1); i++)
        {
            if (!(arr[i] < arr[i+1])){
                return false;
            }
        }
        return true;
    }

    @Override
    public void doWork() throws Exception{
        counterVo.resetArrays();
        for (int i = minListLength - 1; i < maxListLength; i+=stepSize) {
            System.out.println("calculating round: "+ (i+1));
            runTestLoop(i);
        }
    }

    @Override
    public void doWorkAndBenchmarkTime() throws Exception{
        counterVo.resetArrays();
        for (int i = minListLength - 1; i < maxListLength; i+=stepSize) {
            System.out.println("calculating round: "+ (i+1));

            //only prints time
            benchmarkTime(i);
        }
    }

    protected void runTestLoop(int i) {
        counterVo.setCurrentRound(i);
        ArrayList<Integer> arr;

        arr = fillArray(i+1);
        setArray(arr);
        for (int j = 0; j < numOfRuns; ++j) {
            shuffle();
            runTest();
        }
        counterVo.divideBy(numOfRuns);
    }

    private void benchmarkTime(int i) {
        ArrayList<Integer> arr = fillArray(i + 1);
        setArray(arr);

        Benchmark bench = new Benchmark();
        double benchmarkTime = bench.benchmark(this, 0.001);
        System.out.println("round: " + (i+1) + " for " + this.getClass().getName() + " time value: "+ benchmarkTime);

        counterVo.setCurrentRound(i);
        counterVo.setCounter(TIME,benchmarkTime);
    }

    public void predictSelections(int i) {
//        counterVo.setCounter(SELECTIONS,i*log(i) - (i*EULER_MASCHERONI_CONSTANT));
        counterVo.setCounter(SELECTIONS,log(i));
    }

    public void predictComparisons(int i) {
        counterVo.setCounter(COMPARISONS,i*log2(i));
    }

    public void predictComparisons139(int i) {
        counterVo.setCounter(COMPARISONS,1.39*i*log2(i));
    }


    @Override
    public CounterVo getcounters() {
        return counterVo;
    }

    public void setArray(Integer[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length);
    }

    public void setArray(ArrayList<Integer> orig) {
        arr = orig.toArray(new Integer[orig.size()]);
    }

    @Override
    public String  getName() {
        String name = getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1).trim();
    }

    //getter setter


    @Override
    public Integer getMinListLength() {
        return minListLength;
    }

    @Override
    public Integer getMaxListLength() {
        return maxListLength;
    }

    @Override
    public Integer getNumOfRuns() {
        return numOfRuns;
    }

    @Override
    public Integer getStepSize() {
        return stepSize;
    }

    @Override
    public Number[] getCounterAsArray(int i) {
        switch(i) {
            case 0:
                return getcounters().getComparisons();
            case 1:
                return getcounters().getMoves();
            case 2:
                return getcounters().getSelections();
            default:
                return null;
        }
    }
}
