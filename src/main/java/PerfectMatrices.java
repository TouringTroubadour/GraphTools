package main.java;

/**
 * PerfectMatrices
 * 
 * This class produces "Perfect" Graphs. 
 * We define these as "Fully Connected" Sub-Graphs within a parent Graph
 * 
 * This is useful for experimenting with Fiedler Vectors and other concepts in Graph Theory.
 */
public class PerfectMatrices {

    /**
     * 
     * @param size
     * @param numGraphs
     * @return
     */
    public static double[][] createCustomGraph(int size, int numGraphs) {
        double[][] smallGraph = createCustomSmallGraph(size / numGraphs);
        return createLargeGraph(size, smallGraph, numGraphs);
    }

    /**
     * 
     * @param size
     * @return
     */
    private static double[][] createCustomSmallGraph(int size) {
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
     * 
     * @param size
     * @param smallGraph
     * @param numGraphs
     * @return
     */
    private static double[][] createLargeGraph(int size, double[][] smallGraph, int numGraphs) {
        validateParameters(size, smallGraph.length, numGraphs);
    
        double[][] graph = initializeGraph(size);
    
        appendSmallGraphs(graph, smallGraph);
        connectSmallGraphs(graph, smallGraph);
        addAdditionalGraphs(graph, smallGraph, numGraphs);
    
        return graph;
    }
    
    /**
     * 
     * @param size
     * @param smallGraphSize
     * @param numGraphs
     */
    private static void validateParameters(int size, int smallGraphSize, int numGraphs) {
        if (size % smallGraphSize != 0 || numGraphs > size / smallGraphSize) {
            throw new IllegalArgumentException("Invalid size or number of graphs");
        }
    }
    
    /**
     * 
     * @param size
     * @param smallGraph
     * @return
     */
    private static double[][] initializeGraph(int size) {
        double[][] graph = new double[size][size];
        return graph;
    }
    
    /**
     * 
     * @param graph
     * @param smallGraph
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
     * 
     * @param graph
     * @param smallGraph
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
     * 
     * @param graph
     * @param smallGraph
     * @param numGraphs
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
    

}
