package hu.elte.szakdolgozat;

import hu.elte.szakdolgozat.coordinates.PointVo;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static hu.elte.szakdolgozat.SortBase.EULER_MASCHERONI_CONSTANT;

public class SortHeleper {
    public static final Random random = new Random();
    public static final String OUTFOLDER = "out/results";

    enum CounterType {
        COMPARISONS,
        MOVES,
        SELECTIONS,
        TIME,
        ALL,
        MATRIX,
        DIFF,
        DIFF2
    }

    public enum  SearchTarget {
        MAXIMUM,
        MINIMUM
    }

    static void randomizePercent(ArrayList list, Integer size, Double percent) {
//        Random random = new Random();
        Integer rounds = (int) Math.ceil(size * percent);
        for (int i=0; i<rounds; ++i) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            if (a != b){
                Collections.swap(list,a,b);
            }
        }
    }

    public static ArrayList<Integer> fillArray(int length) {
        ArrayList<Integer> arr = new ArrayList<Integer>(length);
        for (int i = 0; i < length; ++i) {
            arr.add(i,length-i);
        }
        return arr;
    }

    public static int randomBetween(int lo, int hi){
        int x = hi-lo+1;

        if (lo == hi) {
            return lo;
        }

        assert  (x > 0);
        return random.nextInt(x) + lo;
    }

    static void exportToFile(ArrayList<IBase> resultList, String filename, String version, CounterType type) throws Exception{

        ArrayList<String> compiledList = new ArrayList<>();
        ArrayList<Integer> indexes = new ArrayList<>();

        Integer minListLength = resultList.get(0).getMinListLength();//should be uniform!!
        Integer maxListLength = resultList.get(0).getMaxListLength();
        Integer stepSize = resultList.get(0).getStepSize();

        compiledList.add("length ");
        for (int i = minListLength - 1; i < maxListLength; i+=stepSize) {
            compiledList.add((i+1)+" ");
            indexes.add(i);
        }

        Double[] diff = null;
        for (int i = 0; i < resultList.size(); i++){
            IBase result = resultList.get(i);
            String name = result.getName().toLowerCase();

            switch(type) {
                case COMPARISONS:
                    addOneColumn(compiledList, indexes, name, result.getcounters().getComparisons());
                    break;
                case MOVES:
                    addOneColumn(compiledList, indexes, name, result.getcounters().getMoves());
                    break;
                case SELECTIONS:
                    addOneColumn(compiledList, indexes, name, result.getcounters().getSelections());
                    break;
                case TIME:
                    addOneColumn(compiledList, indexes, name, result.getcounters().getElapsedTime());
                    break;
                case MATRIX:
                    addOneColumn(compiledList, indexes, "F", result.getCounterAsArray(0));
                    addOneColumn(compiledList, indexes, "M", result.getCounterAsArray(1));
                    break;
                case ALL:
//                    addOneColumn(compiledList, indexes, name+"cmp", result.getcounters().getComparisons());
//                    addOneColumn(compiledList, indexes, name+"mov", result.getcounters().getMoves());
//                    addOneColumn(compiledList, indexes, name+"sel", result.getcounters().getSelections());
                    addOneColumn(compiledList, indexes, name, result.getcounters().getCollectedResults());
                    break;
                case DIFF:
                    if(diff == null){
                        diff = result.getcounters().getSelections();
                    }else {
                        Double[] res = result.getcounters().getSelections();
                        for (int j = 0; j < maxListLength; j++){
                            diff[j] = diff[j] - res[j];
                        }
                    }
                    break;
                default:
            }
        }

        if(type.equals(CounterType.DIFF)){
            addOneColumn(compiledList, indexes, "DIFF", diff);

            Double[]  econst = new Double[diff.length];
            Arrays.fill(econst, EULER_MASCHERONI_CONSTANT);
            addOneColumn(compiledList, indexes, "EulerConst", econst);
        }

        if(type.equals(CounterType.DIFF2)){
            Double[]  clalculated0 = resultList.get(0).getcounters().getComparisons();
            Double[]  clalculated1 = resultList.get(1).getcounters().getComparisons();
            Double[]  calculated = new Double[clalculated0.length];
            for (int i = 0, len = calculated.length; i < len; i++){
                if(clalculated1[i].equals(0.0) || clalculated0[i].equals(0.0)){
                    calculated[i] = 0.0;
                }
                calculated[i] = clalculated1[i] / clalculated0[i];
            }
            addOneColumn(compiledList, indexes, "DIFF", calculated);
        }

        //add end of line
        for (int j = 0; j < compiledList.size(); j++) {
            listRowAppendText(compiledList,j,"\r\n");
        }

        writeToFile(compiledList, filename + "-" + version);
    }

    static void addOneColumn(ArrayList<String> compiledList, ArrayList<Integer> indexes, String name, Number[] values) throws IOException {
        listRowAppendText(compiledList,0,name+" ");
        for (int j = 1; j < compiledList.size(); j++) {
            listRowAppendText(compiledList,j,values[indexes.get(j-1)]+" ");
        }
    }


    static void writeToFile(ArrayList<String> compiledList,String filename) throws IOException {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(OUTFOLDER + "/"+filename+".dat");
            bw = new BufferedWriter(fw);
            for (int j = 0; j < compiledList.size(); j++) {
                bw.write(compiledList.get(j));
            }

        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }


    static void listRowAppendText(ArrayList<String> list, int index, String text) {
        list.add(index, list.get(index) + text);
        list.remove(index + 1);
    }

    /* A utility function to print array of size n*/
    static void printArray(Number arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");

        System.out.println();
    }

    static void printArrayVertical(Number arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.println( (i+1) + " - " + arr[i]);
    }

    static String verticalArrayToString(Number arr[])
    {
        int n = arr.length;
        String res = "";
        for (int i=0; i<n; ++i) {
            if (arr[i] == null) {continue;}
            res += ((i + 1) + " " + arr[i] + "\r\n");
        }
        return res;
    }

    static String verticalArrayToString(Double arr[],Double arr2[])
    {
        int n = arr.length;
        String res = "";
        for (int i=0; i<n; ++i) {
            if (arr[i] == null) {continue;}
            res += ((i + 1) + " " + arr[i] + " " +arr2[i]  + " " +(arr[i] - arr2[i]) +"\r\n");
        }
        return res;
    }

    static ArrayList<PointVo> toPointList(ArrayList<IBase> resultList) throws Exception {

        ArrayList<PointVo> pointList = new ArrayList<>();

        IBase result = resultList.get(0);
        Integer minListLength = result.getMinListLength();//should be uniform!!
        Integer maxListLength = result.getMaxListLength();
        Integer stepSize = result.getStepSize();

        Double[] row = result.getcounters().getCollectedResults();
        for (int i = minListLength - 1; i < maxListLength; i += stepSize) {
            pointList.add(new PointVo(Double.valueOf(i),row[i]));
        }
        return pointList;
    }

    static Double linearity(ArrayList<PointVo> pointVos) throws Exception {
        Double p = 0D;
        Integer size = pointVos.size();
        for (int i = 0; i < size-2; i +=3) {
//            p += linearity(pointVos.get(random.nextInt(size)),pointVos.get(random.nextInt(size)),pointVos.get(random.nextInt(size)));
            p += linearity(pointVos.get(i),pointVos.get(i+1),pointVos.get(i+2));
        }
        return p/(size/3);
    }

    static Double linearity(PointVo P1, PointVo P2, PointVo P3)//TP
    {
        ArrayList<Double> arrayList = new ArrayList<Double>();

        arrayList.add(Math.abs(Point2D.distance(P1.getX(), P1.getY(), P2.getX(), P2.getY())));
        arrayList.add(Math.abs(Point2D.distance(P2.getX(), P2.getY(), P3.getX(), P3.getY())));
        arrayList.add(Math.abs(Point2D.distance(P1.getX(), P1.getY(), P3.getX(), P3.getY())));
        arrayList.sort(Double::compareTo);

        Double a = arrayList.get(0);
        Double b = arrayList.get(1);
        Double c = arrayList.get(2);

        Double p = (2*c-a-b)/c;
        return (p-0.76)/0.24;
    }
}
