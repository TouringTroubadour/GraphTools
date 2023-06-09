package main.java;

import java.util.*;

/**
 * 
 */
public class MatrixDFS {

    private MatrixDFS() {
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @return
     */
    public static <T extends Number> List<List<Integer>> getAllPaths(T[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<List<Integer>> allPaths = new ArrayList<>();

        dfsAllPaths(matrix, start, end, visited, new LinkedList<>(), allPaths);

        return allPaths;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @return
     */
    public static <T extends Number> List<Integer> getShortestPath(T[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<Integer> shortestPath = new ArrayList<>();

        dfsShortestPath(matrix, start, end, visited, new LinkedList<>(), shortestPath);

        return shortestPath;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @return
     */
    public static <T extends Number> List<Integer> getLongestPath(T[][] matrix, int start, int end) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];
        List<Integer> longestPath = new ArrayList<>();

        dfsLongestPath(matrix, start, end, visited, new LinkedList<>(), longestPath);

        return longestPath;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @return
     */
    public static <T extends Number> List<Integer> getDisconnectedComponentSizes(T[][] matrix) {
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
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @param visited
     * @param pathStack
     * @param allPaths
     */
    private static <T extends Number> void dfsAllPaths(T[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<List<Integer>> allPaths) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end) {
            allPaths.add(new ArrayList<>(pathStack));
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != null && matrix[start][i].doubleValue() != 0.0) {
                dfsAllPaths(matrix, i, end, visited, pathStack, allPaths);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @param visited
     * @param pathStack
     * @param shortestPath
     */
    private static <T extends Number> void dfsShortestPath(T[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<Integer> shortestPath) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end && (shortestPath.isEmpty() || pathStack.size() < shortestPath.size())) {
            shortestPath.clear();
            shortestPath.addAll(pathStack);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != null && matrix[start][i].doubleValue() != 0.0) {
                dfsShortestPath(matrix, i, end, visited, pathStack, shortestPath);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param start
     * @param end
     * @param visited
     * @param pathStack
     * @param longestPath
     */
    private static <T extends Number> void dfsLongestPath(T[][] matrix, int start, int end, boolean[] visited,
            Deque<Integer> pathStack, List<Integer> longestPath) {
        visited[start] = true;
        pathStack.addLast(start);

        if (start == end && (longestPath.isEmpty() || pathStack.size() > longestPath.size())) {
            longestPath.clear();
            longestPath.addAll(pathStack);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[start][i] != null && matrix[start][i].doubleValue() != 0.0) {
                dfsLongestPath(matrix, i, end, visited, pathStack, longestPath);
            }
        }

        pathStack.removeLast();
        visited[start] = false;
    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param vertex
     * @param visited
     * @return
     */
    private static <T extends Number> int dfsDisconnectedComponent(T[][] matrix, int vertex, boolean[] visited) {
        visited[vertex] = true;
        int componentSize = 1;

        for (int i = 0; i < matrix.length; i++) {
            if (!visited[i] && matrix[vertex][i] != null && matrix[vertex][i].doubleValue() != 0.0) {
                componentSize += dfsDisconnectedComponent(matrix, i, visited);
            }
        }

        return componentSize;
    }
}
