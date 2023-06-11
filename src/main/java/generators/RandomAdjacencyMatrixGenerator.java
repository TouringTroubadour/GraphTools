package main.java.generators;

import java.util.Random;

public class RandomAdjacencyMatrixGenerator {

    private static final Random random = new Random();

    private RandomAdjacencyMatrixGenerator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a random adjacency matrix with the specified size, connectivity
     * scale, and weight type.
     *
     * @param size              The size of the adjacency matrix.
     * @param connectivityScale The scale of connectivity, ranging from 0.0 to 1.0.
     * @param unweighted        Specifies whether the adjacency matrix is unweighted
     *                          (true) or weighted (false).
     * @return The generated adjacency matrix.
     */
    public static double[][] generateRandomAdjacencyMatrix(int size, double connectivityScale, boolean unweighted) {
        double[][] adjacencyMatrix = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    double connectivity = random.nextDouble();
                    if (connectivity <= connectivityScale) {
                        double weight = 1.0;
                        if (!unweighted) {
                            weight = random.nextDouble() * 10; // Generate a random weight between 0 and 10
                        }
                        adjacencyMatrix[i][j] = weight;
                        adjacencyMatrix[j][i] = weight;
                    }
                }
            }
        }

        return adjacencyMatrix;
    }

    /**
     * Example usage to generate and print a random adjacency matrix.
     *
     * @param args The command-line arguments (ignored).
     */
    public static void main(String[] args) {
        int size = 5;
        double connectivityScale = 0.5;
        boolean unweighted = true;

        double[][] adjacencyMatrix = generateRandomAdjacencyMatrix(size, connectivityScale, unweighted);

        // Print the adjacency matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
