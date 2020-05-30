package hu.elte.szakdolgozat;

public class SelectionSort extends SortBase{

    public SelectionSort(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public SelectionSort() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        writeComparisons = true;
        writeMoves = true;

        for (int i = 0; i < arr.length; i++)
        {
            //find item
            int index = findPosOfMin(i);

            //move item
            if(i != index){
                swap(i,index);
            }
        }
    }

}