package com.graphtools.summary;

import java.util.ArrayList;
import java.util.List;

import com.graphtools.metrics.BasicMeasures;
import com.graphtools.metrics.CentralityMeasures;
import com.graphtools.metrics.ConnectivityMeasures;
import com.graphtools.metrics.DegreeMeasures;
import com.graphtools.metrics.DistanceMeasures;
import com.graphtools.utils.MatrixOperations;

/**
 * The MetricsSummary class provides methods to calculate various metrics and
 * summary statistics
 * for a graph represented by an adjacency matrix.
 */
public class MetricsSummary {

    private MetricsSummary() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the basic measures of the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param shortAnswer     Flag indicating whether to return a short answer or
     *                        detailed answer.
     * @return The basic measures of the graph.
     */
    public static String getBasicMeasures(double[][] adjacencyMatrix, boolean shortAnswer) {
        int vertexCount = BasicMeasures.getVertexCount(adjacencyMatrix);
        int edgeCount = BasicMeasures.getEdgeCount(adjacencyMatrix);
        double density = BasicMeasures.calculateDensity(adjacencyMatrix);

        if (!shortAnswer) {
            return String.format("Basic Measures:%n" +
                    "Vertex Count: %d%n" +
                    "Edge Count: %d%n" +
                    "Graph Density: %f%n", vertexCount, edgeCount, density);
        } else {
            return String.format("%d, %d, %f%n", vertexCount, edgeCount, density);
        }
    }

    /**
     * Calculates the degree measures of the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param shortAnswer     Flag indicating whether to return a short answer or
     *                        detailed answer.
     * @return The degree measures of the graph.
     */
    public static String getDegreeMeasures(double[][] adjacencyMatrix, boolean shortAnswer) {
        List<Integer> vertexDegrees = DegreeMeasures.getVertexDegrees(adjacencyMatrix);
        double clusteringCoefficient = DegreeMeasures.calculateClusteringCoefficient(adjacencyMatrix);
        if (!shortAnswer) {
            return String.format("Degree Measures:%n" +
                    "Vertex Degrees: %s%n" +
                    "Clustering Coefficient: %f%n", vertexDegrees, clusteringCoefficient);
        } else {
            return String.format("%s, %d%n", vertexDegrees, clusteringCoefficient);
        }
    }

    /**
     * Calculates the connectivity measures of the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The connectivity measures of the graph.
     */
    public static String getDistanceMeasures(double[][] adjacencyMatrix) {
        double[][] distanceMatrix = DistanceMeasures.getDistanceMatrix(adjacencyMatrix);
        int graphRadius = DistanceMeasures.getGraphRadius(distanceMatrix);
        int graphDiameter = DistanceMeasures.getGraphDiameter(distanceMatrix);

        return String.format("%s, %s%n", graphRadius, graphDiameter);
    }

    /**
     * Calculates the connectivity measures of the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The connectivity measures of the graph.
     */
    public static String getConnectivityMeasures(double[][] adjacencyMatrix) {
        int vertexConnectivity = ConnectivityMeasures.getVertexConnectivity(adjacencyMatrix);
        int edgeConnectivity = ConnectivityMeasures.getEdgeConnectivity(adjacencyMatrix);

        return String.format("%s, %s%n", vertexConnectivity, edgeConnectivity);
    }

    /**
     * Calculates the centrality measures of the graph.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The centrality measures of the graph.
     */
    public static String getCentralityMeasures(double[][] adjacencyMatrix) {
        // Calculate the closeness centrality
        List<Double> closenessCentralities = CentralityMeasures.calculateClosenessCentrality(adjacencyMatrix);
        // Calculate the betweenness centrality
        List<Double> betweennessCentralities = CentralityMeasures.getBetweennessCentralities(adjacencyMatrix);
        // Calculate the degree centrality
        List<Double> degreeCentralities = CentralityMeasures.getDegreeCentralities(adjacencyMatrix);
        // Calculate the Katz centrality
        List<Double> katzCentralities = new ArrayList<>();
        if (MatrixOperations.determinant(adjacencyMatrix) != 0 && MatrixOperations.inverse(adjacencyMatrix) != null) {
            double alpha = 0.5;
            katzCentralities = CentralityMeasures.getKatzCentralities(adjacencyMatrix, alpha);
        }

        // Calculate the PageRank centrality
        double dampingFactor = 0.85;
        int iterations = 100;
        List<Double> pagerankCentralities = CentralityMeasures.getPageRankCentralities(adjacencyMatrix, dampingFactor,
                iterations);

        return String.format("Connectivity Measures:%n" +
                "Closeness Centralities: %s%n" +
                "Betweenness Centralities: %s%n" +
                "Degree Centralities:  %s%n" +
                "Katz Centralities: %s%n" +
                "Page Rank Centralities: %s%n",
                closenessCentralities, betweennessCentralities, degreeCentralities, katzCentralities,
                pagerankCentralities);

    }

    /**
     * Calculates the summary statistics of the graph.
     *
     * @param matrix The adjacency matrix representing the graph.
     * @return The summary statistics of the graph.
     */
    public static String getSummaryStatistics(double[][] matrix) {
        // Basic Measures
        int vertexCount = BasicMeasures.getVertexCount(matrix);
        int edgeCount = BasicMeasures.getEdgeCount(matrix);
        double density = BasicMeasures.calculateDensity(matrix);
        // Degree Measures
        double clusteringCoefficient = DegreeMeasures.calculateClusteringCoefficient(matrix);
        // Distance Measures
        double[][] distanceMatrix = DistanceMeasures.getDistanceMatrix(matrix);
        int graphRadius = DistanceMeasures.getGraphRadius(distanceMatrix);
        int graphDiameter = DistanceMeasures.getGraphDiameter(distanceMatrix);
        // Connectivity Measures
        int vertexConnectivity = ConnectivityMeasures.getVertexConnectivity(matrix);
        int edgeConnectivity = ConnectivityMeasures.getEdgeConnectivity(matrix);

        return String.format("Summary Statistics:%n" +
                "Number of Vertices: %d%n" +
                "Number of Edges: %d%n" +
                "Density: %f%n" +
                "Clustering Coefficient: %f%n" +
                "Radius: %d%n" +
                "Graph Diameter: %d%n" +
                "Vertex Connectivity: %d%n" +
                "Edge Connectivity: %d%n",
                vertexCount, edgeCount, density, clusteringCoefficient, graphRadius, graphDiameter, vertexConnectivity,
                edgeConnectivity);
    }

    /**
     * Example usage in a main method.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Create an example adjacency matrix representing the graph
        double[][] adjacencyMatrix = {
                { 0, 1, 1, 0 },
                { 1, 0, 1, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 0, 0 }
        };

        // Calculate and print basic measures
        String basicMeasures = MetricsSummary.getBasicMeasures(adjacencyMatrix, false);
        System.out.println(basicMeasures);

        // Calculate and print degree measures
        String degreeMeasures = MetricsSummary.getDegreeMeasures(adjacencyMatrix, false);
        System.out.println(degreeMeasures);

        // Calculate and print distance measures
        String distanceMeasures = MetricsSummary.getDistanceMeasures(adjacencyMatrix);
        System.out.println("Distance Measures:");
        System.out.println(distanceMeasures);

        // Calculate and print connectivity measures
        String connectivityMeasures = MetricsSummary.getConnectivityMeasures(adjacencyMatrix);
        System.out.println("Connectivity Measures:");
        System.out.println(connectivityMeasures);

        // Calculate and print centrality measures
        String centralityMeasures = MetricsSummary.getCentralityMeasures(adjacencyMatrix);
        System.out.println("Centrality Measures:");
        System.out.println(centralityMeasures);

        // Calculate and print summary statistics
        String summaryStatistics = MetricsSummary.getSummaryStatistics(adjacencyMatrix);
        System.out.println(summaryStatistics);
    }

}
