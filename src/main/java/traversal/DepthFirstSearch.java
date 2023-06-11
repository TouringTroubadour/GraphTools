package main.java.traversal;

import java.util.*;

/**
 * Provides depth-first search (DFS) traversal algorithms.
 */
public class DepthFirstSearch {

    private DepthFirstSearch() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns all paths from a given start vertex to a given end vertex in the
     * matrix.
     *
     * @param matrix the matrix representation of the graph
     * @param start  the start vertex
     * @param end    the end vertex
     * @return a list of all paths from the start vertex to the end vertex
     */
    public static List<List<Integer>> getAllPaths(double[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<List<Integer>> allPaths = new ArrayList<>();

        dfsAllPaths(matrix, start, end, visited, new LinkedList<>(), allPaths);

        return allPaths;
    }

    /**
     * Returns the shortest path from a given start vertex to a given end vertex in
     * the matrix.
     *
     * @param matrix the matrix representation of the graph
     * @param start  the start vertex
     * @param end    the end vertex
     * @return the shortest path from the start vertex to the end vertex
     */
    public static List<Integer> getShortestPath(double[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<Integer> shortestPath = new ArrayList<>();

        dfsShortestPath(matrix, start, end, visited, new LinkedList<>(), shortestPath);

        return shortestPath;
    }

    /**
     * Returns the longest path from a given start vertex to a given end vertex in
     * the matrix.
     *
     * @param matrix the matrix representation of the graph
     * @param start  the start vertex
     * @param end    the end vertex
     * @return the longest path from the start vertex to the end vertex
     */
    public static List<Integer> getLongestPath(double[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<Integer> longestPath = new ArrayList<>();

        dfsLongestPath(matrix, start, end, visited, new LinkedList<>(), longestPath);

        return longestPath;
    }

    /**
     * Returns the sizes of disconnected components in the matrix.
     *
     * @param matrix the matrix representation of the graph
     * @return the sizes of disconnected components
     */
    public static List<Integer> getDisconnectedComponentSizes(double[][] matrix) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<Integer> componentSizes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (!visited[i]) {
                int componentSize = dfsDisconnectedComponent(matrix, i, visited);
                componentSizes.add(componentSize);
            }
        }

        return componentSizes;
    }

    /**
     * Depth-First Search to find all paths from the start vertex to the end vertex.
     *
     * @param matrix    the matrix representation of the graph
     * @param start     the start vertex
     * @param end       the end vertex
     * @param visited   an array to keep track of visited vertices
     * @param pathStack a stack to store the current path
     * @param allPaths  a list to store all found paths
     */
    private static void dfsAllPaths(double[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<List<Integer>> allPaths) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end) {
            allPaths.add(new ArrayList<>(pathStack));
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != 0.0) {
                dfsAllPaths(matrix, i, end, visited, pathStack, allPaths);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * Depth-First Search to find the shortest path from the start vertex to the end
     * vertex.
     *
     * @param matrix       the matrix representation of the graph
     * @param start        the start vertex
     * @param end          the end vertex
     * @param visited      an array to keep track of visited vertices
     * @param pathStack    a stack to store the current path
     * @param shortestPath a list to store the shortest path
     */
    private static void dfsShortestPath(double[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<Integer> shortestPath) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end && (shortestPath.isEmpty() || pathStack.size() < shortestPath.size())) {
            shortestPath.clear();
            shortestPath.addAll(pathStack);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != 0.0) {
                dfsShortestPath(matrix, i, end, visited, pathStack, shortestPath);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * Depth-First Search to find the longest path from the start vertex to the end
     * vertex.
     *
     * @param matrix      the matrix representation of the graph
     * @param start       the start vertex
     * @param end         the end vertex
     * @param visited     an array to keep track of visited vertices
     * @param pathStack   a stack to store the current path
     * @param longestPath a list to store the longest path
     */
    private static void dfsLongestPath(double[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<Integer> longestPath) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end && (longestPath.isEmpty() || pathStack.size() > longestPath.size())) {
            longestPath.clear();
            longestPath.addAll(pathStack);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != 0.0) {
                dfsLongestPath(matrix, i, end, visited, pathStack, longestPath);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * Depth-First Search to find the size of a disconnected component starting from
     * the given vertex.
     *
     * @param matrix  the matrix representation of the graph
     * @param vertex  the starting vertex
     * @param visited an array to keep track of visited vertices
     * @return the size of the disconnected component
     */
    private static int dfsDisconnectedComponent(double[][] matrix, int vertex, boolean[] visited) {
        visited[vertex] = true;
        int componentSize = 1;

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[vertex][i] != 0.0) {
                componentSize += dfsDisconnectedComponent(matrix, i, visited);
            }
        }

        return componentSize;
    }

    /**
     * Example usage of DepthFirstSearch class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] matrix = {
                { 0.0, 1.0, 0.0, 1.0, 0.0 },
                { 1.0, 0.0, 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0, 0.0, 1.0 },
                { 1.0, 0.0, 0.0, 0.0, 1.0 },
                { 0.0, 0.0, 1.0, 1.0, 0.0 }
        };

        int startVertex = 0;
        int endVertex = 4;

        List<List<Integer>> allPaths = DepthFirstSearch.getAllPaths(matrix, startVertex, endVertex);
        System.out.println("All Paths: " + allPaths);

        List<Integer> shortestPath = DepthFirstSearch.getShortestPath(matrix, startVertex, endVertex);
        System.out.println("Shortest Path: " + shortestPath);

        List<Integer> longestPath = DepthFirstSearch.getLongestPath(matrix, startVertex, endVertex);
        System.out.println("Longest Path: " + longestPath);

        List<Integer> componentSizes = DepthFirstSearch.getDisconnectedComponentSizes(matrix);
        System.out.println("Disconnected Component Sizes: " + componentSizes);
    }
}
