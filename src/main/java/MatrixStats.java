package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class MatrixStats {

    private MatrixStats() {

    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    @SuppressWarnings("unchecked")
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

    /**
     * 
     * @param <T>
     * @param adjMatrix
     * @return
     */
    public static <T extends Number> double calculateClusteringCoefficient(T[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        int[] triangles = new int[numNodes];
        int numTriangles = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                for (int k = j + 1; k < numNodes; k++) {
                    if (adjMatrix[i][j].doubleValue() != 0 && adjMatrix[i][k].doubleValue() != 0 && adjMatrix[j][k].doubleValue() != 0) {
                        triangles[i]++;
                        triangles[j]++;
                        triangles[k]++;
                        numTriangles++;
                    }
                }
            }
        }
        return numTriangles / (numNodes * (numNodes - 1) * (double) (numNodes - 2) / 6);
    }
    
    /**
     * Must provide Distances Matrix from MatrixTools.getFloydWarshallDistances
     * @param distances
     * @return
     */
    public static double getShortestDistance(double[][] distances) {
        double minDistance = Double.POSITIVE_INFINITY;
        int size = distances.length;
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (distances[i][j] < minDistance) {
                    minDistance = distances[i][j];
                }
            }
        }
    
        return minDistance;
    }
    
    /**
     * Must provide Distances Matrix from MatrixTools.getFloydWarshallDistances
     * @param distances
     * @return
     */
    public static double getRadius(double[][] distances) {
        double radius = Double.POSITIVE_INFINITY;
        int size = distances.length;
        boolean hasFiniteEccentricity = false;
    
        for (int i = 0; i < size; i++) {
            double eccentricity = 0.0;
            boolean hasInfiniteDistance = false;
    
            for (int j = 0; j < size; j++) {
                if (distances[i][j] == Double.POSITIVE_INFINITY) {
                    hasInfiniteDistance = true;
                } else if (distances[i][j] > eccentricity) {
                    eccentricity = distances[i][j];
                }
            }
    
            if (!hasInfiniteDistance && eccentricity < radius) {
                radius = eccentricity;
                hasFiniteEccentricity = true;
            }
        }
    
        if (hasFiniteEccentricity) {
            return radius;
        } else {
            return 0.0; // or any other value you choose for a graph with disconnected components
        }
    }
    
    /**
     * Must provide Distances Matrix from MatrixTools.getFloydWarshallDistances
     * @param distances
     * @return
     */
    public static double getGraphDiameter(double[][] distances) {
        double diameter = 0.0;
        int size = distances.length;
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (distances[i][j] > diameter && distances[i][j] != Double.POSITIVE_INFINITY) {
                    diameter = distances[i][j];
                }
            }
        }
    
        return diameter;
    }    
    
    /**
     * Must provide Distances Matrix from MatrixTools.getFloydWarshallDistances
     * @param distances
     * @return
     */
    public static double getAveragePathLength(double[][] distances) {
        int size = distances.length;
        double totalDistance = 0.0;
        int pairCount = 0;
    
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (distances[i][j] != Double.POSITIVE_INFINITY) {
                    totalDistance += distances[i][j];
                    pairCount++;
                }
            }
        }
    
        if (pairCount > 0) {
            return totalDistance / pairCount;
        } else {
            return 0.0; // or any other value you choose for an empty graph
        }
    }

}
