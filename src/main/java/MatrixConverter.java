package main.java;

import java.lang.reflect.Array;

/**
 * 
 */
public class MatrixConverter {

    /**
     * 
     * @param <T>
     * @param primitiveMatrix
     * @param wrapperClass
     * @return
     */
    public static <T> T[][] toWrapperMatrix(Object primitiveMatrix, Class<T> wrapperClass) {
        int numRows = Array.getLength(primitiveMatrix);
        int numCols = Array.getLength(Array.get(primitiveMatrix, 0));
    
        try {
            T[][] wrapperMatrix = (T[][]) Array.newInstance(wrapperClass, numRows, numCols);
    
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    Array.set(wrapperMatrix[i], j, Array.get(Array.get(primitiveMatrix, i), j));
                }
            }
    
            return wrapperMatrix;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Unable to cast primitive matrix to wrapper matrix", e);
        }
    }
    

    /**
     * 
     * @param <T>
     * @param wrapperMatrix
     * @return
     */
    public static <T> Object toPrimitiveMatrix(T[][] wrapperMatrix) {
        int numRows = wrapperMatrix.length;
        int numCols = wrapperMatrix[0].length;

        Object primitiveMatrix = Array.newInstance(wrapperMatrix.getClass().getComponentType().getComponentType(),
                numRows, numCols);

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Array.set(Array.get(primitiveMatrix, i), j, wrapperMatrix[i][j]);
            }
        }

        return primitiveMatrix;
    }

    /**
     * 
     * @param <T>
     * @param adjacencyMatrix
     * @return
     */
    public static <T extends Number> Integer[][] getLaplacianMatrix(T[][] adjacencyMatrix) {
        int size = adjacencyMatrix.length;
        Integer[][] laplacianMatrix = new Integer[size][size];

        // Calculate the degree matrix
        Integer[] degree = calculateDegrees(adjacencyMatrix);

        // Calculate the Laplacian matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double value = adjacencyMatrix[i][j].doubleValue();
                if (i == j) {
                    laplacianMatrix[i][j] = degree[i];
                } else {
                    laplacianMatrix[i][j] = (int) Math.round(-value);
                }
            }
        }

        return laplacianMatrix;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    private static <T extends Number> Integer[] calculateDegrees(T[][] matrix) {
        int size = matrix.length;
        Integer[] degrees = new Integer[size];

        for (int i = 0; i < size; i++) {
            int degree = 0;
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].doubleValue() != 0) {
                    degree++;
                }
            }
            degrees[i] = degree;
        }

        return degrees;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T extends Number> Integer[][] toHessenbergMatrix(T[][] matrix) {
        int n = matrix.length;
        Integer[][] hessenbergMatrix = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j <= i + 1) {
                    hessenbergMatrix[i][j] = matrix[i][j].intValue();
                } else {
                    hessenbergMatrix[i][j] = 0;
                }
            }
        }

        return hessenbergMatrix;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     */
    public static <T> void convertToLowerTriangleZeros(T[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < i; j++) {
                matrix[i][j] = null; // Set lower triangle elements to null or 0 depending on T type
            }
        }
    }

}
