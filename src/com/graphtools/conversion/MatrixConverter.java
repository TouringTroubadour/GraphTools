package com.graphtools.conversion;

public class MatrixConverter {

    private MatrixConverter() {
        // Private constructor to prevent instantiation of the class
    }

    /**
     * Converts an adjacency matrix to a Laplacian matrix.
     *
     * @param adjacencyMatrix The adjacency matrix.
     * @return The Laplacian matrix.
     */
    public static double[][] toLaplacianMatrix(double[][] adjacencyMatrix) {
        int size = adjacencyMatrix.length;
        double[][] laplacianMatrix = new double[size][size];

        // Calculate the degree matrix
        double[] degree = calculateDegrees(adjacencyMatrix);

        // Calculate the Laplacian matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double value = adjacencyMatrix[i][j];
                if (i == j) {
                    laplacianMatrix[i][j] = degree[i];
                } else {
                    laplacianMatrix[i][j] = -value;
                }
            }
        }

        return laplacianMatrix;
    }

    /**
     * Calculates the degrees of the vertices in an adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix.
     * @return The array of degrees.
     */
    private static double[] calculateDegrees(double[][] adjacencyMatrix) {
        int size = adjacencyMatrix.length;
        double[] degrees = new double[size];

        for (int i = 0; i < size; i++) {
            double degree = 0;
            for (int j = 0; j < size; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    degree++;
                }
            }
            degrees[i] = degree;
        }

        return degrees;
    }

    /**
     * Converts a matrix to a Hessenberg matrix.
     *
     * @param matrix The matrix.
     * @return The Hessenberg matrix.
     */
    public static double[][] toHessenbergMatrix(double[][] matrix) {
        int size = matrix.length;
        double[][] hessenbergMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j <= i + 1) {
                    hessenbergMatrix[i][j] = matrix[i][j];
                } else {
                    hessenbergMatrix[i][j] = 0;
                }
            }
        }

        return hessenbergMatrix;
    }

    /**
     * Example usage of the MatrixTransform class.
     */
    public static void main(String[] args) {
        // Example usage of the MatrixTransform class
        double[][] adjacencyMatrix = {
                { 0, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 0 }
        };

        System.out.println("Original Adjacency Matrix:");
        printMatrix(adjacencyMatrix);

        double[][] laplacianMatrix = toLaplacianMatrix(adjacencyMatrix);
        System.out.println("Laplacian Matrix:");
        printMatrix(laplacianMatrix);

        double[][] hessenbergMatrix = toHessenbergMatrix(adjacencyMatrix);
        System.out.println("Hessenberg Matrix:");
        printMatrix(hessenbergMatrix);
    }

    /**
     * Prints a matrix to the console.
     *
     * @param matrix The matrix to be printed.
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
