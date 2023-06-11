package main.java.utils;

public class MatrixValidator {

    /**
     * Checks if a matrix is square (number of rows = number of columns).
     *
     * @param matrix The matrix to check.
     * @return True if the matrix is square, false otherwise.
     */
    public static boolean isSquare(double[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    /**
     * Checks if a matrix is symmetric (values are equal across the main diagonal).
     *
     * @param matrix The matrix to check.
     * @return True if the matrix is symmetric, false otherwise.
     */
    public static boolean isSymmetric(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if all leading diagonal elements of a matrix are zero.
     *
     * @param matrix The matrix to check.
     * @return True if all leading diagonal elements are zero, false otherwise.
     */
    public static boolean isLeadingDiagonalAllZero(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            if (matrix[i][i] != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a matrix is weighted (all values are non-negative).
     *
     * @param matrix The matrix to check.
     * @return True if the matrix is weighted, false otherwise.
     */
    public static boolean isWeighted(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            return false;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if all elements of a matrix are non-negative.
     *
     * @param matrix The matrix to check.
     * @return True if all elements are non-negative, false otherwise.
     */
    public static boolean isNonNegative(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Validates a matrix by running all the validation methods and returns a string
     * representation of the results.
     *
     * @param matrix The matrix to validate.
     * @return A string representation of the validation results.
     */
    public static String validateMatrix(double[][] matrix) {
        boolean[] results = new boolean[5];
        results[0] = isSquare(matrix);
        results[1] = isSymmetric(matrix);
        results[2] = isLeadingDiagonalAllZero(matrix);
        results[3] = isWeighted(matrix);
        results[4] = isNonNegative(matrix);

        StringBuilder sb = new StringBuilder();
        sb.append("Matrix Validation Results:\n");
        sb.append("Is Square: ").append(results[0]).append("\n");
        sb.append("Is Symmetric: ").append(results[1]).append("\n");
        sb.append("Is Leading Diagonal All Zero: ").append(results[2]).append("\n");
        sb.append("Is Weighted: ").append(results[3]).append("\n");
        sb.append("Is Non-Negative: ").append(results[4]).append("\n");

        return sb.toString();
    }

    /**
     * Example usage to validate a matrix.
     *
     * @param args The command-line arguments (ignored).
     */
    public static void main(String[] args) {
        double[][] matrix = {
                { 0, 1, 2 },
                { 1, 0, 3 },
                { 2, 3, 0 }
        };

        System.out.println("Original Matrix:");
        MatrixTools.printMatrix(matrix);

        String validationResults = validateMatrix(matrix);
        System.out.println("\n" + validationResults);
    }
}
