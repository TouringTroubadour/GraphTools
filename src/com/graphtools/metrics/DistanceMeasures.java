package com.graphtools.metrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class DistanceMeasures {

    private DistanceMeasures() {
        // Private constructor to prevent instantiation
    }

    /**
     * Computes the shortest path between two vertices in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param sourceVertex    The index of the source vertex.
     * @param targetVertex    The index of the target vertex.
     * @return The shortest path between the source and target vertices.
     */
    public static List<Integer> getShortestPath(double[][] adjacencyMatrix, int sourceVertex, int targetVertex) {
        int numVertices = adjacencyMatrix.length;
        boolean[] visited = new boolean[numVertices];
        int[] previous = new int[numVertices];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(sourceVertex);
        visited[sourceVertex] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                if (adjacencyMatrix[currentVertex][neighbor] != 0 && !visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                    previous[neighbor] = currentVertex;

                    if (neighbor == targetVertex) {
                        return reconstructPath(previous, sourceVertex, targetVertex);
                    }
                }
            }
        }

        return new ArrayList<>(); // No path found
    }

    /**
     * Reconstructs the shortest path from the source to the target vertex using the
     * previous array.
     *
     * @param previous     The array containing the previous vertex for each vertex.
     * @param sourceVertex The index of the source vertex.
     * @param targetVertex The index of the target vertex.
     * @return The reconstructed shortest path.
     */
    private static List<Integer> reconstructPath(int[] previous, int sourceVertex, int targetVertex) {
        List<Integer> path = new ArrayList<>();
        int currentVertex = targetVertex;

        while (currentVertex != sourceVertex) {
            path.add(0, currentVertex);
            currentVertex = previous[currentVertex];
        }

        path.add(0, sourceVertex);
        return path;
    }

    /**
     * Computes the distance matrix of a graph represented by an adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The distance matrix of the graph.
     */
    public static double[][] getDistanceMatrix(double[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;
        double[][] distanceMatrix = new double[numVertices][numVertices];

        for (double[] row : distanceMatrix) {
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        }

        for (int vertex = 0; vertex < numVertices; vertex++) {
            distanceMatrix[vertex][vertex] = 0;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(vertex);

            while (!queue.isEmpty()) {
                int currentVertex = queue.poll();

                for (int neighbor = 0; neighbor < numVertices; neighbor++) {
                    if (adjacencyMatrix[currentVertex][neighbor] != 0
                            && distanceMatrix[vertex][neighbor] == Double.POSITIVE_INFINITY) {
                        distanceMatrix[vertex][neighbor] = distanceMatrix[vertex][currentVertex] + 1;
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distanceMatrix;
    }

    /**
     * Computes the eccentricity of a vertex in a graph represented by an adjacency
     * matrix.
     *
     * @param distanceMatrix The distance matrix of the graph.
     * @param vertex         The index of the vertex.
     * @return The eccentricity of the vertex.
     */
    public static int getVertexEccentricity(double[][] distanceMatrix, int vertex) {
        int eccentricity = 0;
        for (int otherVertex = 0; otherVertex < distanceMatrix.length; otherVertex++) {
            if (distanceMatrix[vertex][otherVertex] != Double.POSITIVE_INFINITY
                    && distanceMatrix[vertex][otherVertex] > eccentricity) {
                eccentricity = (int) distanceMatrix[vertex][otherVertex];
            }
        }
        return eccentricity;
    }

    /**
     * Computes the radius of a graph represented by an adjacency matrix.
     *
     * @param distanceMatrix The distance matrix of the graph.
     * @return The radius of the graph.
     */
    public static int getGraphRadius(double[][] distanceMatrix) {
        int radius = Integer.MAX_VALUE;
        for (double[] row : distanceMatrix) {
            int eccentricity = (int) Arrays.stream(row).max().orElse(Double.POSITIVE_INFINITY);
            if (eccentricity < radius) {
                radius = eccentricity;
            }
        }
        return radius;
    }

    /**
     * Computes the diameter of a graph represented by an adjacency matrix.
     *
     * @param distanceMatrix The distance matrix of the graph.
     * @return The diameter of the graph.
     */
    public static int getGraphDiameter(double[][] distanceMatrix) {
        int diameter = 0;
        for (double[] row : distanceMatrix) {
            int eccentricity = (int) Arrays.stream(row).max().orElse(Double.POSITIVE_INFINITY);
            if (eccentricity > diameter) {
                diameter = eccentricity;
            }
        }
        return diameter;
    }

    // Example usage
    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0.0, 1.0, 1.0, 0.0 },
                { 1.0, 0.0, 1.0, 0.0 },
                { 1.0, 1.0, 0.0, 1.0 },
                { 0.0, 0.0, 1.0, 0.0 }
        };

        int sourceVertex = 0;
        int targetVertex = 3;

        List<Integer> shortestPath = getShortestPath(adjacencyMatrix, sourceVertex, targetVertex);
        System.out.println("Shortest Path from vertex " + sourceVertex + " to " + targetVertex + ": " + shortestPath);

        double[][] distanceMatrix = getDistanceMatrix(adjacencyMatrix);
        System.out.println("Distance Matrix:");
        for (double[] row : distanceMatrix) {
            System.out.println(Arrays.toString(row));
        }

        int vertexEccentricity = getVertexEccentricity(distanceMatrix, 0);
        System.out.println("Eccentricity of vertex 0: " + vertexEccentricity);

        int graphRadius = getGraphRadius(distanceMatrix);
        System.out.println("Graph Radius: " + graphRadius);

        int graphDiameter = getGraphDiameter(distanceMatrix);
        System.out.println("Graph Diameter: " + graphDiameter);
    }
}
