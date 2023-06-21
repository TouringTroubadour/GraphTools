package com.graphtools.generators;

public class PerfectMatrixGenerator {

    private PerfectMatrixGenerator() {
        // Private constructor to prevent instantiation
    }

    /**
     * Generates a perfect adjacency matrix with the specified size, number of
     * graphs, and connectivity options.
     *
     * @param size            The size of the matrix.
     * @param numGraphs       The number of smaller graphs.
     * @param connectLastNode Determines whether to connect the last node of each
     *                        smaller graph.
     * @return The generated perfect adjacency matrix.
     */
    public static double[][] generatePerfectAdjacencyMatrix(int size, int numGraphs, boolean connectLastNode) {
        double[][] smallGraph = generatePerfectSubMatrix(size / numGraphs);
        return generatePerfectParentMatrix(size, smallGraph, numGraphs, connectLastNode);
    }

    /**
     * Generates a perfect sub-matrix with the specified size.
     *
     * @param size The size of the sub-matrix.
     * @return The generated perfect sub-matrix.
     */
    private static double[][] generatePerfectSubMatrix(int size) {
        double[][] graph = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 1;
                }
            }
        }
        return graph;
    }

    /**
     * Generates the perfect parent matrix by combining the small graph matrices.
     *
     * @param size            The size of the parent matrix.
     * @param smallGraph      The small graph matrix.
     * @param numGraphs       The number of smaller graphs.
     * @param connectLastNode Determines whether to connect the last node of each
     *                        smaller graph.
     * @return The generated perfect parent matrix.
     */
    private static double[][] generatePerfectParentMatrix(int size, double[][] smallGraph, int numGraphs,
            boolean connectLastNode) {
        validateParameters(size, smallGraph.length, numGraphs);

        double[][] graph = initializeGraph(size);

        appendSmallGraphs(graph, smallGraph);
        connectSmallGraphs(graph, smallGraph);
        if (connectLastNode) {
            connectLastNodeOfEachGraph(graph, smallGraph);
        }
        addAdditionalGraphs(graph, smallGraph, numGraphs);

        return graph;
    }

    /**
     * Connects the last node of each smaller graph to the next graph inside the
     * larger graph.
     *
     * @param graph      The graph matrix.
     * @param smallGraph The small graph matrix.
     */
    private static void connectLastNodeOfEachGraph(double[][] graph, double[][] smallGraph) {
        int smallGraphSize = smallGraph.length;
        int numGraphs = graph.length / smallGraphSize;

        for (int i = 0; i < numGraphs - 1; i++) {
            graph[i * smallGraphSize + smallGraphSize - 1][(i + 1) * smallGraphSize] = 1;
            graph[(i + 1) * smallGraphSize][(i * smallGraphSize) + smallGraphSize - 1] = 1;
        }
    }

    /**
     * Validates the parameters for generating the perfect adjacency matrix.
     *
     * @param size           The size of the parent matrix.
     * @param smallGraphSize The size of the small graph.
     * @param numGraphs      The number of smaller graphs.
     */
    private static void validateParameters(int size, int smallGraphSize, int numGraphs) {
        if (size % smallGraphSize != 0 || numGraphs > size / smallGraphSize) {
            throw new IllegalArgumentException("Invalid size or number of graphs");
        }
    }

    /**
     * Initializes the graph matrix with zeros.
     *
     * @param size The size of the graph matrix.
     * @return The initialized graph matrix.
     */
    private static double[][] initializeGraph(int size) {
        return new double[size][size];
    }

    /**
     * Appends the small graph matrices to form the parent graph matrix.
     *
     * @param graph      The parent graph matrix.
     * @param smallGraph The small graph matrix.
     */
    private static void appendSmallGraphs(double[][] graph, double[][] smallGraph) {
        int smallGraphSize = smallGraph.length;

        for (int i = 0; i < graph.length; i += smallGraphSize) {
            for (int j = 0; j < smallGraphSize; j++) {
                for (int k = 0; k < smallGraphSize; k++) {
                    graph[i + j][i + k] = smallGraph[j][k];
                }
            }
        }
    }

    /**
     * Connects the small graph matrices within the parent graph matrix.
     *
     * @param graph      The parent graph matrix.
     * @param smallGraph The small graph matrix.
     */
    private static void connectSmallGraphs(double[][] graph, double[][] smallGraph) {
        int smallGraphSize = smallGraph.length;
        int numGraphs = graph.length / smallGraphSize;

        for (int i = 0; i < numGraphs - 1; i++) {
            graph[(i + 1) * smallGraphSize - 1][i * smallGraphSize] = 1;
            graph[i * smallGraphSize][(i + 1) * smallGraphSize - 1] = 1;
        }
    }

    /**
     * Adds additional small graph matrices to the parent graph matrix.
     *
     * @param graph      The parent graph matrix.
     * @param smallGraph The small graph matrix.
     * @param numGraphs  The number of smaller graphs.
     */
    private static void addAdditionalGraphs(double[][] graph, double[][] smallGraph, int numGraphs) {
        int smallGraphSize = smallGraph.length;

        for (int i = 0; i < numGraphs - 1; i++) {
            int start = smallGraphSize + (i * smallGraphSize);
            int end = start + smallGraphSize;

            for (int j = start; j < end; j++) {
                for (int k = start; k < end; k++) {
                    graph[j][k] = smallGraph[j - start][k - start];
                }
            }

            graph[end - 1][start] = 1;
            graph[start][end - 1] = 1;
        }
    }

    /**
     * Example usage to generate and print a perfect adjacency matrix.
     *
     * @param args The command-line arguments (ignored).
     */
    public static void main(String[] args) {
        int size = 6;
        int numGraphs = 3;
        boolean connectLastNode = true;

        double[][] adjacencyMatrix = generatePerfectAdjacencyMatrix(size, numGraphs, connectLastNode);

        // Print the adjacency matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
