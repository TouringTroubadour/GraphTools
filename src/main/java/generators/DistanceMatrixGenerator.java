package main.java.generators;

import java.util.Random;

public class DistanceMatrixGenerator {

    private static final Random random = new Random();

    private DistanceMatrixGenerator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a random distance matrix with specified size, minimum and maximum
     * values, and number of decimal places.
     *
     * @param size             The size of the distance matrix.
     * @param min              The minimum value for the distances.
     * @param max              The maximum value for the distances.
     * @param numberOfDecimals The number of decimal places to round the distances
     *                         to.
     * @return The generated distance matrix.
     */
    public static double[][] generateRandomDistances(int size, double min, double max, int numberOfDecimals) {
        double[][] distances = new double[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double distance = min + (random.nextDouble() * (max - min));
                distances[i][j] = round(distance, numberOfDecimals);
            }
        }

        return distances;
    }

    /**
     * Rounds a given value to the specified number of decimal places.
     *
     * @param value            The value to round.
     * @param numberOfDecimals The number of decimal places to round to.
     * @return The rounded value.
     */
    private static double round(double value, int numberOfDecimals) {
        double scale = Math.pow(10, numberOfDecimals);
        return Math.round(value * scale) / scale;
    }

    /**
     * Example usage to generate and print a random distance matrix.
     *
     * @param args The command-line arguments (ignored).
     */
    public static void main(String[] args) {
        int size = 4;
        double min = 0.0;
        double max = 10.0;
        int numberOfDecimals = 2;

        double[][] distances = generateRandomDistances(size, min, max, numberOfDecimals);

        // Print the generated distance matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(distances[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
