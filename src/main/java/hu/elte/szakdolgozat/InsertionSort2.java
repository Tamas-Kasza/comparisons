package hu.elte.szakdolgozat;

public class InsertionSort2 extends SortBase{

    public InsertionSort2(Integer minListLength, Integer maxListLength, Integer numOfRuns, Integer stepSize) throws Exception {
        super(minListLength, maxListLength, numOfRuns, stepSize);
    }

    public InsertionSort2() throws Exception {
        super();
    }

    @Override
    public void runTest(){
      int i = 1;
      while (i < arr.length) {
          int j = i;
          while (j > 0 && arr[j - 1] > arr[j]) {
              int tmp = arr[j];
              arr[j] = arr[j - 1];
              arr[j - 1] = tmp;
              j = j - 1;
          }
          i = i + 1;
      }
    }
}