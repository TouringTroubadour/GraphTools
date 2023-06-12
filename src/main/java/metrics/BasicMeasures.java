package main.java.metrics;

/**
 * 
 */
public class BasicMeasures {

    private BasicMeasures() {
        // Private constructor to prevent instantiation
    }

    /**
     * Computes the total number of vertices in a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The number of vertices in the graph.
     */
    public static int getVertexCount(double[][] adjacencyMatrix) {
        return adjacencyMatrix.length;
    }

    /**
     * Computes the total number of edges in a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The number of edges in the graph.
     */
    public static int getEdgeCount(double[][] adjacencyMatrix) {
        int edgeCount = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    edgeCount++;
                }
            }
        }
        return edgeCount / 2; // Divide by 2 since each edge is counted twice in the adjacency matrix
    }

    /**
     * Calculates the density of a graph represented by an adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The density of the graph.
     */
    public static double calculateDensity(double[][] adjacencyMatrix) {
        int vertexCount = adjacencyMatrix.length;
        int edgeCount = 0;

        // Count the number of edges in the graph
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    edgeCount++;
                }
            }
        }

        // Calculate the maximum number of edges in a graph with the given vertex count
        int maxEdges = vertexCount * (vertexCount - 1);

        // Calculate the density as the ratio of the actual number of edges to the
        // maximum possible edges
        return (double) edgeCount / maxEdges;
    }

    // Example usage
    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0.0, 1.0, 0.0, 1.0 },
                { 1.0, 0.0, 1.0, 0.0 },
                { 0.0, 1.0, 0.0, 1.0 },
                { 1.0, 0.0, 1.0, 0.0 }
        };

        int vertexCount = getVertexCount(adjacencyMatrix);
        System.out.println("Vertex Count: " + vertexCount);

        int edgeCount = getEdgeCount(adjacencyMatrix);
        System.out.println("Edge Count: " + edgeCount);

        double density = calculateDensity(adjacencyMatrix);
        System.out.println("Graph Density: " + density);

    }
}
