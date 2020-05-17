package hu.elte.szakdolgozat;

public class SelectionSort4 extends SortBase{

    public SelectionSort4(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public SelectionSort4() throws Exception {
        super();
    }

    @Override
    public void runTest(){
        for (int i = 0; i < arr.length; i++)
        {
            //find item
            int index = i;
            for (int j=index+1; j<arr.length; j++)
            {
                if (arr[j] < arr[index])
                {
                    index = j;
                }
            }

            //move item
            if(i != index){
                int tmp = arr[i];
                arr[i] = arr[index];
                arr[index] = tmp;
            }
        }
    }

}