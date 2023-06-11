package test.java.utils;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.utils.MatrixTools;

public class MatrixToolsTest {

    @Test
    void testPrintMatrix() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // No assertion is made since it's a console output method
        MatrixTools.printMatrix(matrix);
    }

    @Test
    void testDeepClone() {
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        double[][] clone = MatrixTools.deepClone(matrix);

        // Assert that the cloned matrix is equal to the original matrix
        Assertions.assertTrue(Arrays.deepEquals(matrix, clone));

        // Assert that modifying the cloned matrix doesn't affect the original matrix
        clone[0][0] = 0;
        Assertions.assertNotEquals(matrix[0][0], clone[0][0]);
    }

    @Test
    void testGetFloydWarshallDistances() {
        double[][] matrix = {
            {0, 1, 3, 0},
            {1, 0, 0, 2},
            {3, 0, 0, 4},
            {0, 2, 4, 0}
        };

        double[][] expectedDistances = {
            {0, 1, 3, 3},
            {1, 0, 4, 2},
            {3, 4, 0, 2},
            {3, 2, 2, 0}
        };

        double[][] distances = MatrixTools.getFloydWarshallDistances(matrix);

        // Assert that the computed distances matrix is equal to the expected distances matrix
        Assertions.assertTrue(Arrays.deepEquals(expectedDistances, distances));
    }

    public static void main(String[] args) {
        // Run the test methods
        MatrixToolsTest test = new MatrixToolsTest();
        test.testPrintMatrix();
        test.testDeepClone();
        test.testGetFloydWarshallDistances();

        // Print the test results
        System.out.println("All test cases passed!");
    }
}
