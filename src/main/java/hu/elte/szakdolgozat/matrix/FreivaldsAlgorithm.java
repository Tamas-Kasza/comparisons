package hu.elte.szakdolgozat.matrix;

/**
 * MIT License
 *
 * Copyright (c) 2017 William Fiset
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * Freivalds' algorithm is a probabilistic randomized algorithm used to verify
 * matrix multiplication. Given three n x n matrices, Freivalds' algorithm
 * determines in O(kn^2) whether the matrices are equal for a chosen k value
 * with a probability of failure less than 2^-k.
 *
 *
 * Time Complexity: O(kn^2)
 *
 * @author William Fiset, william.alexandre.fiset@gmail.com
 **/

public class FreivaldsAlgorithm {
    private Long counter = 0L;


    // Randomly sets the values in the vector to either 0 or 1
    private static void randomizeVector(int[] vector) {
        for(int i = 0; i < vector.length; i++) {
            vector[i] = (Math.random() < 0.5) ? 0 : 1;
        }
    }

    // Compute the product of a vector with a matrix.
    private int[] product(int[] v, int[][] matrix) {

        int N = matrix.length;
        int[] vector = new int[N];

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++) {
                vector[i] += v[j] * matrix[i][j];
                counter += 2;
            }
            counter -=1;
        }

        return vector;
    }

    // Freivalds' algorithm is a probabilistic randomized algorithm used to verify
    // matrix multiplication. Given three n x n matrices, Freivalds' algorithm
    // determines in O(kn^2) whether the matrices are equal for a chosen k value
    // with a probability of failure less than 2^-k.
    public boolean freivalds(int[][] A, int [][] B, int[][] C, int k) {

        final int n = A.length;
        counter = 0L;//count steps taken
        if (A[0].length != n || B.length != n || B[0].length != n || C.length != n || C[0].length != n)
            throw new IllegalArgumentException("Input must be three nxn matrices");

        int[] v = new int[n];
        do {

            randomizeVector(v);

            int[] expected = product(v, C);
            int[] result = product(product(v, B), A);

            if (!java.util.Arrays.equals(expected, result)) return false;

        } while(--k > 0);

        return true;

    }

    public Long getCounter() {
        return counter;
    }
}