package com.graphtools.metrics;

public class ConnectivityMeasures {

    private ConnectivityMeasures() {
        // Private constructor to prevent instantiation
    }

    /**
     * Computes the vertex connectivity of a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The vertex connectivity of the graph.
     */
    public static int getVertexConnectivity(double[][] adjacencyMatrix) {
        int minDegree = Integer.MAX_VALUE;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int degree = DegreeMeasures.getVertexOutDegree(adjacencyMatrix, i);
            if (degree < minDegree) {
                minDegree = degree;
            }
        }
        return minDegree;
    }

    /**
     * Computes the edge connectivity of a graph represented by an adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The edge connectivity of the graph.
     */
    public static int getEdgeConnectivity(double[][] adjacencyMatrix) {
        int minDegree = Integer.MAX_VALUE;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            int degree = 0;
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    degree++;
                }
            }
            if (degree < minDegree) {
                minDegree = degree;
            }
        }
        return minDegree;
    }

    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0.0, 1.0, 1.0, 0.0 },
                { 1.0, 0.0, 1.0, 0.0 },
                { 1.0, 1.0, 0.0, 1.0 },
                { 0.0, 0.0, 1.0, 0.0 }
        };

        int vertexConnectivity = getVertexConnectivity(adjacencyMatrix);
        System.out.println("Vertex Connectivity: " + vertexConnectivity);

        int edgeConnectivity = getEdgeConnectivity(adjacencyMatrix);
        System.out.println("Edge Connectivity: " + edgeConnectivity);
    }
}