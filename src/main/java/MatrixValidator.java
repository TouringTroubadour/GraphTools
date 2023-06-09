package main.java;

public class MatrixValidator<T extends Number> {

    /**
     * 
     * @param matrix
     * @return
     */
    public boolean isSquare(T[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    /**
     * 
     * @param matrix
     * @return
     */
    public boolean isSymmetric(T[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (!matrix[i][j].equals(matrix[j][i])) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 
     * @param matrix
     * @return
     */
    public boolean isLeadingDiagonalAllZero(T[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            if (!matrix[i][i].equals(0)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * @param matrix
     * @return
     */
    public boolean isWeighted(T[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j].doubleValue() < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 
     * @param matrix
     * @return
     */
    public static boolean isNonNegative(double[][] matrix) {
        int n = matrix.length;
    
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) {
                    // Matrix element is negative
                    return false;
                }
            }
        }
    
        // All matrix elements are non-negative
        return true;
    }
}
