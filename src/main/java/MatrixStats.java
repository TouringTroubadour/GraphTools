package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class MatrixStats {

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T> List<T> getNumberOfVertices(T[][] matrix) {
        List<T> vertices = new ArrayList<>();
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            vertices.add((T) Integer.valueOf(i));
        }

        return vertices;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T> int[] getDegrees(T[][] matrix) {
        int size = matrix.length;
        int[] degrees = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] instanceof Number && ((Number) matrix[i][j]).doubleValue() == 1.0) {
                    degrees[i]++;
                    degrees[j]++;
                }
            }
        }

        return degrees;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    private static <T> int getNumberOfNodes(T[][] matrix) {
        return matrix.length;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    private static <T> int getNumberOfEdges(T[][] matrix) {
        int size = matrix.length;
        int count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] instanceof Number && ((Number) matrix[i][j]).doubleValue() == 1.0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T> double getDensity(T[][] matrix) {
        int numberOfNodes = getNumberOfNodes(matrix);
        int numberOfEdges = getNumberOfEdges(matrix);

        int maxPossibleEdges = numberOfNodes * (numberOfNodes - 1) / 2;

        return (double) numberOfEdges / maxPossibleEdges;
    }

}
