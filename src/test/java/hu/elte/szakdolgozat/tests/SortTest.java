package hu.elte.szakdolgozat.tests;

import hu.elte.szakdolgozat.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static hu.elte.szakdolgozat.SortHeleper.fillArray;
import static org.testng.Assert.*;

public class SortTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testBubblesort() throws Exception {

        BubbleSort test = new BubbleSort();
        try {
            test.setArray(new Integer[]{3,2,9,4,7,5});
            test.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(test.getcounters().getComparisons()[0],15D);
        Assert.assertEquals(test.getcounters().getMoves()[0],15D);
        Assert.assertTrue(test.isArrayinAscOrder());
    }

    @Test
    public void testInsertionSort() throws Exception {

        InsertionSort test = new InsertionSort();
        try {
            test.setArray(new Integer[]{3,2,9,4,7,5});
            test.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(test.getcounters().getComparisons()[0],9D);
        Assert.assertEquals(test.getcounters().getMoves()[0],13D);
        Assert.assertTrue(test.isArrayinAscOrder());
    }

    @Test
    public void testSelectionSort() throws Exception {

        SelectionSort test = new SelectionSort();
        try {
            test.setArray(new Integer[]{3,2,9,4,7,5});
            test.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(test.getcounters().getComparisons()[0],15D);
        Assert.assertEquals(test.getcounters().getMoves()[0],9D);
        Assert.assertEquals(test.getcounters().getSelections()[0],10D);
        Assert.assertTrue(test.isArrayinAscOrder());
    }

    @Test
    public void testSelectionSort2() throws Exception {

        SelectionSort test = new SelectionSort();
        try {
            test.setArray(new Integer[]{8,5,2,6,9,3,1,4,0,7});
            test.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(test.getcounters().getSelections()[0],23D);
        Assert.assertTrue(test.isArrayinAscOrder());
    }

    @Test
    public void testQuickSort() throws Exception {

        QuickSort test = new QuickSort();
        try {
            test.setArray(new Integer[]{3,2,9,4,7,5});
            test.runTest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(test.getcounters().getComparisons()[0],9D);
        Assert.assertEquals(test.getcounters().getMoves()[0],9D);
        Assert.assertTrue(test.isArrayinAscOrder());
    }

    @Test
    public void ascOrderTest() throws Exception {

        ArrayList<SortBase> tests = new ArrayList<>();
        tests.add(new BubbleSort());
        tests.add(new InsertionSort());
        tests.add(new SelectionSort());
        tests.add(new QuickSort());

        for (int i = 1; i < 512; i++) {
            ArrayList<Integer> arr = fillArray(i);
            for (int j = 0; j < tests.size(); ++j) {
                SortBase sortBase = tests.get(j);
                sortBase.setArray(arr);
                for (int k = 0; k < 100; ++k) {
                    sortBase.shuffle();
                    try {
                        sortBase.runTest();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Assert.assertTrue(sortBase.isArrayinAscOrder());
                }
            }
        }
    }
}