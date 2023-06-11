package main.java.statistics;

import java.util.ArrayList;
import java.util.List;

import main.java.utils.MatrixTools;

public class MatrixStatistics {

    private MatrixStatistics() {

    }

    public static int getRowCount(double[][] matrix) {
        return matrix.length;
    }

    public static int getColumnCount(double[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        return matrix[0].length;
    }

    public static List<Integer> getNumberOfVertices(double[][] matrix) {
        List<Integer> vertices = new ArrayList<>();
        int size = matrix.length;

        for (int i = 0; i < size; i++) {
            vertices.add(i);
        }

        return vertices;
    }

    public static int[] getDegrees(double[][] matrix) {
        int size = matrix.length;
        int[] degrees = new int[size];

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] == 1.0) {
                    degrees[i]++;
                    degrees[j]++;
                }
            }
        }

        return degrees;
    }

    public static int getNumberOfNodes(double[][] matrix) {
        return matrix.length;
    }

    public static int getNumberOfEdges(double[][] matrix) {
        int size = matrix.length;
        int count = 0;

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] == 1.0) {
                    count++;
                }
            }
        }

        return count;
    }

    public static double getDensity(double[][] matrix) {
        int numberOfNodes = getNumberOfNodes(matrix);
        int numberOfEdges = getNumberOfEdges(matrix);

        int maxPossibleEdges = numberOfNodes * (numberOfNodes - 1) / 2;

        return (double) numberOfEdges / maxPossibleEdges;
    }

    public static double calculateClusteringCoefficient(double[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        int[] triangles = new int[numNodes];
        int numTriangles = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                for (int k = j + 1; k < numNodes; k++) {
                    if (adjMatrix[i][j] != 0 && adjMatrix[i][k] != 0 && adjMatrix[j][k] != 0) {
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
            return 0.0;
        }
    }

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
            return 0.0;
        }
    }

    public static String getSummaryStatistics(double[][] matrix) {
        int rowCount = getRowCount(matrix);
        int columnCount = getColumnCount(matrix);
        int numberOfNodes = getNumberOfNodes(matrix);
        int numberOfEdges = getNumberOfEdges(matrix);
        double density = getDensity(matrix);
        double clusteringCoefficient = calculateClusteringCoefficient(matrix);
        double[][] distances = MatrixTools.getFloydWarshallDistances(matrix);
        double shortestDistance = getShortestDistance(distances);
        double radius = getRadius(distances);
        double graphDiameter = getGraphDiameter(distances);
        double averagePathLength = getAveragePathLength(distances);

        return String.format("Summary Statistics:\n" +
        "Number of Rows: %d\n" +
        "Number of Columns: %d\n" +
        "Number of Nodes: %d\n" +
        "Number of Edges: %d\n" +
        "Density: %f\n" +
        "Clustering Coefficient: %f\n" +
        "Shortest Distance: %f\n" +
        "Radius: %f\n" +
        "Graph Diameter: %f\n" +
        "Average Path Length: %f",
        rowCount, columnCount, numberOfNodes, numberOfEdges, density, clusteringCoefficient,
        shortestDistance, radius, graphDiameter, averagePathLength);
    }

    public static String getCondensedSummaryStatistics(double[][] matrix) {
        int rowCount = getRowCount(matrix);
        int columnCount = getColumnCount(matrix);
        int numberOfNodes = getNumberOfNodes(matrix);
        int numberOfEdges = getNumberOfEdges(matrix);
        double density = getDensity(matrix);
        double clusteringCoefficient = calculateClusteringCoefficient(matrix);
        double[][] distances = MatrixTools.getFloydWarshallDistances(matrix);
        double shortestDistance = getShortestDistance(distances);
        double radius = getRadius(distances);
        double graphDiameter = getGraphDiameter(distances);
        double averagePathLength = getAveragePathLength(distances);

        return String.format("%d, %d, %d, %d, %f, %f, %f, %f, %f, %f",
        rowCount, columnCount, numberOfNodes, numberOfEdges, density, clusteringCoefficient,
        shortestDistance, radius, graphDiameter, averagePathLength);
    }

}
