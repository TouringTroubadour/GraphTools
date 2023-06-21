package com.graphtools.metrics;

import java.util.ArrayList;
import java.util.List;

import com.graphtools.ranking.PageRank;
import com.graphtools.traversal.Dijkstra;
import com.graphtools.utils.MatrixOperations;

public class CentralityMeasures {

    private CentralityMeasures() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the closeness centrality for each vertex in the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return A list of closeness centrality values for each vertex in the graph.
     */
    public static List<Double> calculateClosenessCentrality(double[][] adjacencyMatrix) {
        int n = adjacencyMatrix.length;
        List<Double> centralityList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            double closenessCentrality = calculateCloseness(adjacencyMatrix, i);
            centralityList.add(closenessCentrality);
        }

        return centralityList;
    }

    /**
     * Calculates the closeness centrality for a specific vertex in the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param vertex          The index of the vertex for which to calculate the
     *                        closeness centrality.
     * @return The closeness centrality value for the specified vertex.
     */
    private static double calculateCloseness(double[][] adjacencyMatrix, int vertex) {
        int n = adjacencyMatrix.length;
        double totalDistance = 0;

        for (int i = 0; i < n; i++) {
            if (i == vertex)
                continue;

            double[] currentShortestPaths = Dijkstra.shortestPath(adjacencyMatrix, vertex, i);
            if (isValidShortestPath(currentShortestPaths)) {
                for (double distance : currentShortestPaths) {
                    totalDistance += distance;
                }
            }
        }

        if (totalDistance == 0) {
            // Handle the case where totalDistance is 0 to avoid division by zero
            return 0;
        }

        return (n - 1) / totalDistance;
    }

    /**
     * Computes the betweenness centrality of all vertices in a graph represented by
     * an adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The list of betweenness centralities for all vertices.
     */
    public static List<Double> getBetweennessCentralities(double[][] adjacencyMatrix) {
        int vertexCount = adjacencyMatrix.length;
        List<Double> centralities = new ArrayList<>();

        for (int vertex = 0; vertex < vertexCount; vertex++) {
            double centrality = computeBetweennessCentrality(adjacencyMatrix, vertex);
            centralities.add(centrality);
        }

        return centralities;
    }

    /**
     * Computes the betweenness centrality of a specific vertex in a graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param vertex          The vertex for which to compute the betweenness
     *                        centrality.
     * @return The betweenness centrality of the vertex.
     */
    private static double computeBetweennessCentrality(double[][] adjacencyMatrix, int vertex) {
        int vertexCount = adjacencyMatrix.length;
        double centrality = 0.0;

        // Iterate over all pairs of vertices (i, j) except the given vertex
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (i != j && i != vertex && j != vertex) {
                    double[] shortestPath = Dijkstra.shortestPath(adjacencyMatrix, i, j);
                    if (isValidShortestPath(shortestPath)) {
                        int shortestPathsCount = countShortestPathsPassingThroughVertex(
                                adjacencyMatrix, i, j, vertex);
                        double contribution = computeContribution(shortestPathsCount, shortestPath);
                        centrality += contribution;
                    }
                }
            }
        }

        return centrality;
    }

    /**
     * Checks if the shortest path array is valid and contains a non-infinite
     * shortest path.
     *
     * @param shortestPath The array representing the shortest path.
     * @return True if the shortest path is valid, false otherwise.
     */
    private static boolean isValidShortestPath(double[] shortestPath) {
        return shortestPath.length > 0 && shortestPath[0] != Double.MAX_VALUE && shortestPath[0] > 0;
    }

    /**
     * Counts the number of shortest paths passing through a specific vertex using
     * Dijkstra's algorithm.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param source          The source vertex.
     * @param destination     The destination vertex.
     * @param vertex          The vertex to be included in the path.
     * @return The number of shortest paths passing through the specified vertex.
     */
    private static int countShortestPathsPassingThroughVertex(double[][] adjacencyMatrix, int source, int destination,
            int vertex) {
        int vertexCount = adjacencyMatrix.length;
        int count = 0;

        for (int i = 0; i < vertexCount; i++) {
            if (i != source && i != destination && i != vertex) {
                double[] shortestPathSourceToVertex = Dijkstra.shortestPath(adjacencyMatrix, source, i);
                double[] shortestPathVertexToDestination = Dijkstra.shortestPath(adjacencyMatrix, i, destination);

                if (isValidShortestPath(shortestPathSourceToVertex)
                        && isValidShortestPath(shortestPathVertexToDestination)
                        && (shortestPathSourceToVertex[0] + shortestPathVertexToDestination[0] == Dijkstra.shortestPath(
                                adjacencyMatrix, source, destination)[0])) {
                    count++;

                }
            }
        }

        return count;
    }

    /**
     * Computes the contribution of a specific pair (i, j) to the centrality.
     *
     * @param shortestPathsCount The number of shortest paths passing through the
     *                           vertex.
     * @param shortestPath       The shortest path distances array.
     * @return The contribution to the centrality.
     */
    private static double computeContribution(int shortestPathsCount, double[] shortestPath) {
        double shortestPathLength = shortestPath[0];
        if (shortestPathLength != 0) {
            return shortestPathsCount / shortestPathLength;
        } else {
            return 0.0;
        }
    }

    /**
     * Computes the degree centrality of all vertices in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The list of degree centralities for all vertices.
     */
    public static List<Double> getDegreeCentralities(double[][] adjacencyMatrix) {
        List<Double> centralities = new ArrayList<>();
        int vertexCount = adjacencyMatrix.length;

        for (int i = 0; i < vertexCount; i++) {
            int degree = DegreeMeasures.getVertexDegree(adjacencyMatrix, i);
            double centrality = (double) degree / (vertexCount - 1);
            centralities.add(centrality);
        }

        return centralities;
    }

    /**
     * Computes the Katz centrality of all vertices in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param alpha           The attenuation factor.
     * @return The list of Katz centralities for all vertices.
     */
    public static List<Double> getKatzCentralities(double[][] adjacencyMatrix, double alpha) {
        int vertexCount = adjacencyMatrix.length;
        double[][] identityMatrix = MatrixOperations.createIdentityMatrix(vertexCount);
        double[][] katzMatrix = MatrixOperations.subtract(identityMatrix,
                MatrixOperations.scalarMultiply(adjacencyMatrix, alpha));
        double[][] inverseKatzMatrix = MatrixOperations.inverse(katzMatrix);

        if (inverseKatzMatrix == null) {
            throw new IllegalArgumentException(
                    "The inverse matrix is null. The adjacency matrix may not be invertible.");
        }

        List<Double> centralities = new ArrayList<>();

        for (int vertex = 0; vertex < vertexCount; vertex++) {
            double katzCentrality = 0.0;
            for (int j = 0; j < vertexCount; j++) {
                katzCentrality += inverseKatzMatrix[vertex][j];
            }
            centralities.add(katzCentrality);
        }

        return centralities;
    }

    /**
     * Computes the PageRank centrality of all vertices in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param dampingFactor   The damping factor (usually set to 0.85).
     * @param iterations      The number of iterations for the PageRank algorithm.
     * @return The list of PageRank centralities for all vertices.
     */
    public static List<Double> getPageRankCentralities(double[][] adjacencyMatrix, double dampingFactor,
            int iterations) {
        int vertexCount = adjacencyMatrix.length;
        double[] pagerank = PageRank.calculate(adjacencyMatrix, dampingFactor, iterations);
        List<Double> centralities = new ArrayList<>();

        for (int vertex = 0; vertex < vertexCount; vertex++) {
            centralities.add(pagerank[vertex]);
        }

        return centralities;
    }

    // Usage Example
    public static void main(String[] args) {
        // Create an adjacency matrix representing the graph
        double[][] adjacencyMatrix = {
                { 0, 1, 1, 0 },
                { 1, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 0, 1, 1, 0 }
        };

        // Calculate the closeness centrality
        List<Double> closenessCentralities = CentralityMeasures.calculateClosenessCentrality(adjacencyMatrix);
        System.out.println("Closeness Centralities:");
        for (int i = 0; i < closenessCentralities.size(); i++) {
            System.out.println("Vertex " + i + ": " + closenessCentralities.get(i));
        }

        // Calculate the betweenness centrality
        List<Double> betweennessCentralities = CentralityMeasures.getBetweennessCentralities(adjacencyMatrix);
        System.out.println("Betweenness Centralities:");
        for (int i = 0; i < betweennessCentralities.size(); i++) {
            System.out.println("Vertex " + i + ": " + betweennessCentralities.get(i));
        }

        // Calculate the degree centrality
        List<Double> degreeCentralities = CentralityMeasures.getDegreeCentralities(adjacencyMatrix);
        System.out.println("Degree Centralities:");
        for (int i = 0; i < degreeCentralities.size(); i++) {
            System.out.println("Vertex " + i + ": " + degreeCentralities.get(i));
        }

        // Calculate the Katz centrality
        // Only calculate Katz Centralities if the matrix determinant is non-zero and
        // matrix can be inversed.
        if (MatrixOperations.determinant(adjacencyMatrix) != 0 && MatrixOperations.inverse(adjacencyMatrix) != null) {
            double alpha = 0.5;
            List<Double> katzCentralities = CentralityMeasures.getKatzCentralities(adjacencyMatrix, alpha);
            System.out.println("Katz Centralities:");
            for (int i = 0; i < katzCentralities.size(); i++) {
                System.out.println("Vertex " + i + ": " + katzCentralities.get(i));
            }
        }

        // Calculate the PageRank centrality
        double dampingFactor = 0.85;
        int iterations = 100;
        List<Double> pagerankCentralities = CentralityMeasures.getPageRankCentralities(adjacencyMatrix,
                dampingFactor, iterations);
        System.out.println("PageRank Centralities:");
        for (int i = 0; i < pagerankCentralities.size(); i++) {
            System.out.println("Vertex " + i + ": " + pagerankCentralities.get(i));
        }
    }

}
