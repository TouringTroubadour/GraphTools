package main.java.utils;

import java.util.Arrays;

/**
 * MatrixTools
 * A collection of useful methods related to matrices.
 */
public class MatrixTools {

    private MatrixTools() {
        // Private constructor to prevent instantiation
    }

    /**
     * Prints the matrix to the console.
     *
     * @param matrix The matrix to be printed.
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
     * Creates a deep clone of the matrix.
     *
     * @param matrix The matrix to be cloned.
     * @return A deep clone of the input matrix.
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
     * Calculates the shortest distances between all pairs of vertices using the
     * Floyd-Warshall algorithm.
     *
     * @param matrix The adjacency matrix representing the graph.
     * @return The matrix of shortest distances.
     */
    public static double[][] getFloydWarshallDistances(double[][] matrix) {
        int size = matrix.length;
        double[][] distances = initializeDistances(matrix);
        applyFloydWarshallAlgorithm(distances, size);
        return distances;
    }

    /**
     * Initializes the distances matrix with appropriate initial values.
     *
     * @param matrix The adjacency matrix representing the graph.
     * @return The initialized distances matrix.
     */
    private static double[][] initializeDistances(double[][] matrix) {
        int size = matrix.length;
        double[][] distances = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                distances[i][j] = getDistanceValue(matrix[i][j]);
            }
        }

        return distances;
    }

    /**
     * Converts the matrix value to the appropriate distance value.
     *
     * @param value The value to be converted.
     * @return The distance value.
     */
    private static double getDistanceValue(double value) {
        return (value == 0) ? Double.POSITIVE_INFINITY : value;
    }

    /**
     * Applies the Floyd-Warshall algorithm to calculate the shortest distances.
     *
     * @param distances The distances matrix.
     * @param size      The size of the matrix.
     */
    private static void applyFloydWarshallAlgorithm(double[][] distances, int size) {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }
    }

    /**
     * Example usage of the MatrixTools class.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        double[][] matrix = {
                { 0, 1, 3, 0 },
                { 1, 0, 0, 2 },
                { 3, 0, 0, 4 },
                { 0, 2, 4, 0 }
        };

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        double[][] clonedMatrix = deepClone(matrix);
        clonedMatrix[0][1] = 5;

        System.out.println("\nCloned Matrix:");
        printMatrix(clonedMatrix);

        double[][] distances = getFloydWarshallDistances(matrix);

        System.out.println("\nShortest Distances Matrix:");
        printMatrix(distances);
    }
}
