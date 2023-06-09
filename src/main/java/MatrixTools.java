package main.java;

import java.util.Arrays;

/**
 * Matrix Tools
 * A collection of useful methods related to Graphs
 */
public class MatrixTools {

    private MatrixTools() {

    }

    /**
     * Ouput Matrix in Console.
     * Warning, doesn't work very well with large matrices.
     * 
     * @param matrix
     */
    public static void printMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 
     * @param matrix
     * @return
     */
    public static double[][] deepClone(double[][] matrix) {
        if (matrix == null) {
            return null;
        }

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        double[][] clone = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            clone[i] = Arrays.copyOf(matrix[i], numCols);
        }

        return clone;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T> T[][] deepClone(T[][] matrix) {
        if (matrix == null) {
            return null;
        }

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        @SuppressWarnings("unchecked")
        T[][] clone = (T[][]) new Object[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            clone[i] = Arrays.copyOf(matrix[i], numCols);
        }

        return clone;
    }
}
