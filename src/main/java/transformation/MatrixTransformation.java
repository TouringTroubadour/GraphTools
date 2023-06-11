package main.java.transformation;

public class MatrixTransformation {
    private MatrixTransformation() {
        // Private constructor to prevent instantiation of the class
    }

    /**
     * Converts the lower triangle elements of a matrix to zeros and returns the modified matrix.
     *
     * @param matrix The matrix.
     * @return The matrix with lower triangle elements set to zero.
     */
    public static double[][] convertToLowerTriangleZeros(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        double[][] modifiedMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j <= i) {
                    modifiedMatrix[i][j] = matrix[i][j];
                } else {
                    modifiedMatrix[i][j] = 0;
                }
            }
        }

        return modifiedMatrix;
    }

    /**
     * Converts the upper triangle elements of a matrix to zeros and returns the modified matrix.
     *
     * @param matrix The matrix.
     * @return The matrix with upper triangle elements set to zero.
     */
    public static double[][] convertToUpperTriangleZeros(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (numRows != numCols) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        double[][] modifiedMatrix = new double[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (j >= i) {
                    modifiedMatrix[i][j] = matrix[i][j];
                } else {
                    modifiedMatrix[i][j] = 0;
                }
            }
        }

        return modifiedMatrix;
    }

    public static void main(String[] args) {
        // Example usage
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        System.out.println("Matrix with Lower Triangle Zeros:");
        double[][] lowerTriangleMatrix = MatrixTransformation.convertToLowerTriangleZeros(matrix);
        printMatrix(lowerTriangleMatrix);

        System.out.println("Matrix with Upper Triangle Zeros:");
        double[][] upperTriangleMatrix = MatrixTransformation.convertToUpperTriangleZeros(matrix);
        printMatrix(upperTriangleMatrix);
    }

    /**
     * Prints the given matrix.
     *
     * @param matrix The matrix to print.
     */
    private static void printMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

