package main.java.traversal;

import java.util.*;

/**
 * This class implements Dijkstra's algorithm to find the shortest path between
 * two vertices in a graph.
 */
public class Dijkstra {

    private Dijkstra() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the shortest path between a source and destination vertex in a
     * graph represented by an adjacency matrix.
     *
     * @param adjacencyMatrix the adjacency matrix representing the graph
     * @param source          the source vertex
     * @param destination     the destination vertex
     * @return the shortest path as an array of vertices
     */
    public static double[] shortestPath(double[][] adjacencyMatrix, int source, int destination) {
        int n = adjacencyMatrix.length;
        double[] distances = new double[n]; // Array to store distances from the source vertex to all other vertices
        int[] predecessors = new int[n]; // Array to store predecessors of each vertex in the shortest path
        boolean[] visited = new boolean[n]; // Array to track visited vertices

        // Initialize arrays
        for (int i = 0; i < n; i++) {
            distances[i] = Double.MAX_VALUE; // Set distances to infinity initially
            predecessors[i] = -1; // Set predecessors to -1 initially
            visited[i] = false; // Mark all vertices as unvisited
        }

        distances[source] = 0; // Distance from source to source is 0

        // Find shortest path using Dijkstra's algorithm
        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(distances, visited); // Find the vertex with the minimum distance

            visited[u] = true; // Mark the selected vertex as visited

            // Update distances and predecessors of adjacent vertices
            for (int v = 0; v < n; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] > 0 && distances[u] != Double.MAX_VALUE
                        && distances[u] + adjacencyMatrix[u][v] < distances[v]) {
                    distances[v] = distances[u] + adjacencyMatrix[u][v]; // Update the distance
                    predecessors[v] = u; // Update the predecessor
                }
            }
        }

        // Build the shortest path from the destination vertex to the source vertex
        List<Integer> shortestPath = new ArrayList<>();
        int currentVertex = destination;
        while (currentVertex != -1) {
            shortestPath.add(currentVertex);
            currentVertex = predecessors[currentVertex];
        }

        Collections.reverse(shortestPath); // Reverse the path to get it from the source to the destination

        // Convert the list of vertices to an array of doubles
        double[] shortestPathArray = new double[shortestPath.size()];
        for (int i = 0; i < shortestPath.size(); i++) {
            shortestPathArray[i] = shortestPath.get(i);
        }

        return shortestPathArray;
    }

    /**
     * Finds the index of the vertex with the minimum distance from the source
     * vertex among the unvisited vertices.
     *
     * @param distances the array of distances from the source vertex to each vertex
     * @param visited   the array indicating whether a vertex has been visited or
     *                  not
     * @return the index of the vertex with the minimum distance
     */
    private static int minDistance(double[] distances, boolean[] visited) {
        double min = Double.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= min) {
                min = distances[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    /**
     * Main method to demonstrate the usage of the Dijkstra's algorithm.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0, 1, 3, 0, 0 },
                { 1, 0, 1, 4, 0 },
                { 3, 1, 0, 1, 2 },
                { 0, 4, 1, 0, 2 },
                { 0, 0, 2, 2, 0 }
        };

        int source = 0;
        int destination = 4;

        double[] shortestPath = shortestPath(adjacencyMatrix, source, destination);

        System.out.println("Shortest Path: " + Arrays.toString(shortestPath));
    }
}
