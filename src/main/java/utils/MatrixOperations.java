package main.java.utils;

/**
 * This class provides matrix operations for mathematical computations.
 */
public class MatrixOperations {

    private MatrixOperations() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates an identity matrix of the specified size.
     *
     * @param size The size of the identity matrix.
     * @return The identity matrix as a 2D double array.
     */
    public static double[][] createIdentityMatrix(int size) {
        double[][] identityMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    identityMatrix[i][j] = 1.0;
                } else {
                    identityMatrix[i][j] = 0.0;
                }
            }
        }

        return identityMatrix;
    }

    /**
     * Multiplies a matrix by a scalar.
     *
     * @param matrix The matrix to multiply.
     * @param scalar The scalar value.
     * @return The resulting matrix after scalar multiplication.
     */
    public static double[][] scalarMultiply(double[][] matrix, double scalar) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        double[][] result = new double[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }

        return result;
    }

    /**
     * Subtracts one matrix from another.
     *
     * @param matrix1 The matrix to subtract from.
     * @param matrix2 The matrix to subtract.
     * @return The resulting matrix after subtraction.
     */
    public static double[][] subtract(double[][] matrix1, double[][] matrix2) {
        int rowCount = matrix1.length;
        int colCount = matrix1[0].length;
        double[][] result = new double[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }

    /**
     * Computes the inverse of a matrix.
     *
     * @param matrix The matrix to invert.
     * @return The inverse of the matrix, or null if the matrix is not invertible.
     */
    public static double[][] inverse(double[][] matrix) {
        int n = matrix.length;
        double[][] augmentedMatrix = createAugmentedMatrix(matrix, n);

        double det = determinant(matrix);
        if (det == 0) {
            // Matrix is not invertible
            return null;
        }

        if (!performGaussJordanElimination(augmentedMatrix, n)) {
            // Matrix is not invertible
            return null;
        }

        return extractInvertedMatrix(augmentedMatrix, n);
    }

    /**
     * Computes the determinant of a square matrix.
     *
     * @param matrix The matrix for which to calculate the determinant.
     * @return The determinant of the matrix.
     */
    public static double determinant(double[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return matrix[0][0];
        } else {
            double det = 0.0;
            int sign = 1;
            for (int i = 0; i < n; i++) {
                double[][] subMatrix = createSubMatrix(matrix, n, 0, i);
                det += sign * matrix[0][i] * determinant(subMatrix);
                sign *= -1;
            }
            return det;
        }
    }

    /**
     * Creates a submatrix of a given matrix by removing the specified row and
     * column.
     *
     * @param matrix The matrix.
     * @param n      The size of the matrix.
     * @param row    The row to remove.
     * @param col    The column to remove.
     * @return The submatrix.
     */
    private static double[][] createSubMatrix(double[][] matrix, int n, int row, int col) {
        double[][] subMatrix = new double[n - 1][n - 1];
        int rowIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }

            int colIndex = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    continue;
                }

                subMatrix[rowIndex][colIndex] = matrix[i][j];
                colIndex++;
            }

            rowIndex++;
        }

        return subMatrix;
    }

    /**
     * Creates the augmented matrix [matrix | identity matrix].
     *
     * @param matrix The matrix.
     * @param n      The size of the matrix.
     * @return The augmented matrix.
     */
    private static double[][] createAugmentedMatrix(double[][] matrix, int n) {
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            augmentedMatrix[i][i + n] = 1.0;
        }
        return augmentedMatrix;
    }

    /**
     * Performs Gauss-Jordan elimination on the augmented matrix.
     *
     * @param augmentedMatrix The augmented matrix.
     * @param n               The size of the matrix.
     * @return True if the elimination was successful, false otherwise.
     */
    private static boolean performGaussJordanElimination(double[][] augmentedMatrix, int n) {
        for (int pivot = 0; pivot < n; pivot++) {
            if (augmentedMatrix[pivot][pivot] == 0.0) {
                // Matrix is not invertible
                return false;
            }

            normalizePivotRow(augmentedMatrix, pivot, n);

            for (int row = 0; row < n; row++) {
                if (row != pivot) {
                    eliminateRow(augmentedMatrix, pivot, row, n);
                }
            }
        }

        return true;
    }

    /**
     * Normalizes the pivot row by dividing all elements by the pivot value.
     *
     * @param augmentedMatrix The augmented matrix.
     * @param pivot           The pivot index.
     * @param n               The size of the matrix.
     */
    private static void normalizePivotRow(double[][] augmentedMatrix, int pivot, int n) {
        double pivotValue = augmentedMatrix[pivot][pivot];
        for (int col = 0; col < 2 * n; col++) {
            augmentedMatrix[pivot][col] /= pivotValue;
        }
    }

    /**
     * Eliminates the row using the pivot row.
     *
     * @param augmentedMatrix The augmented matrix.
     * @param pivot           The pivot index.
     * @param row             The row to eliminate.
     * @param n               The size of the matrix.
     */
    private static void eliminateRow(double[][] augmentedMatrix, int pivot, int row, int n) {
        double factor = augmentedMatrix[row][pivot];
        for (int col = 0; col < 2 * n; col++) {
            augmentedMatrix[row][col] -= factor * augmentedMatrix[pivot][col];
        }
    }

    /**
     * Extracts the inverted matrix from the augmented matrix.
     *
     * @param augmentedMatrix The augmented matrix.
     * @param n               The size of the matrix.
     * @return The inverted matrix.
     */
    private static double[][] extractInvertedMatrix(double[][] augmentedMatrix, int n) {
        double[][] invertedMatrix = new double[n][n];
        for (int row = 0; row < n; row++) {
            System.arraycopy(augmentedMatrix[row], n, invertedMatrix[row], 0, n);
        }
        return invertedMatrix;
    }

    public static void main(String[] args) {
        // Create a sample matrix
        double[][] matrix = {
                { 1, 2 },
                { 3, 4 }
        };

        // Create an identity matrix of size 2
        double[][] identityMatrix = MatrixOperations.createIdentityMatrix(2);

        // Multiply the sample matrix by a scalar value of 2
        double[][] scalarMultipliedMatrix = MatrixOperations.scalarMultiply(matrix, 2);

        // Subtract the sample matrix from the identity matrix
        double[][] subtractedMatrix = MatrixOperations.subtract(identityMatrix, matrix);

        // Compute the inverse of the sample matrix
        double[][] invertedMatrix = MatrixOperations.inverse(matrix);

        // Print the results
        System.out.println("Identity Matrix:");
        MatrixTools.printMatrix(identityMatrix);

        System.out.println("Scalar Multiplied Matrix:");
        MatrixTools.printMatrix(scalarMultipliedMatrix);

        System.out.println("Subtracted Matrix:");
        MatrixTools.printMatrix(subtractedMatrix);

        System.out.println("Inverted Matrix:");
        MatrixTools.printMatrix(invertedMatrix);
    }

}
