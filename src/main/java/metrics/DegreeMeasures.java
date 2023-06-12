package main.java.metrics;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class DegreeMeasures {

    private DegreeMeasures() {
        // Private constructor to prevent instantiation
    }

    /**
     * Computes the degree of a specific vertex in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param vertex          The vertex for which to compute the degree.
     * @return The degree of the specified vertex.
     */
    public static int getVertexDegree(double[][] adjacencyMatrix, int vertex) {
        int degree = 0;
        int vertexCount = adjacencyMatrix.length;

        for (int i = 0; i < vertexCount; i++) {
            if (adjacencyMatrix[vertex][i] > 0) {
                degree++;
            }
        }

        return degree;
    }

    /**
     * Computes the degrees of all vertices in a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The list of vertex degrees.
     */
    public static List<Integer> getVertexDegrees(double[][] adjacencyMatrix) {
        List<Integer> vertexDegrees = new ArrayList<>();
        int numVertices = adjacencyMatrix.length;
        for (int vertex = 0; vertex < numVertices; vertex++) {
            int degree = getVertexInDegree(adjacencyMatrix, vertex) + getVertexOutDegree(adjacencyMatrix, vertex);
            vertexDegrees.add(degree);
        }
        return vertexDegrees;
    }

    /**
     * Computes the in-degree of a vertex in a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param vertex          The index of the vertex.
     * @return The in-degree of the vertex.
     */
    public static int getVertexInDegree(double[][] adjacencyMatrix, int vertex) {
        int inDegree = 0;
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (adjacencyMatrix[i][vertex] != 0) {
                inDegree++;
            }
        }
        return inDegree;
    }

    /**
     * Computes the out-degree of a vertex in a graph represented by an adjacency
     * matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param vertex          The index of the vertex.
     * @return The out-degree of the vertex.
     */
    public static int getVertexOutDegree(double[][] adjacencyMatrix, int vertex) {
        int outDegree = 0;
        for (int j = 0; j < adjacencyMatrix[vertex].length; j++) {
            if (adjacencyMatrix[vertex][j] != 0) {
                outDegree++;
            }
        }
        return outDegree;
    }

    public static double calculateClusteringCoefficient(double[][] adjMatrix) {
        int numNodes = adjMatrix.length;
        int[] triangles = new int[numNodes];
        int numTriangles = 0;
        for (int i = 0; i < numNodes; i++) {
            for (int j = i + 1; j < numNodes; j++) {
                for (int k = j + 1; k < numNodes; k++) {
                    if (adjMatrix[i][j] != 0 && adjMatrix[i][k] != 0 && adjMatrix[j][k] != 0) {
                        triangles[i]++;
                        triangles[j]++;
                        triangles[k]++;
                        numTriangles++;
                    }
                }
            }
        }
        return numTriangles / (numNodes * (numNodes - 1) * (double) (numNodes - 2) / 6);
    }

    // Example usage
    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0.0, 1.0, 0.0, 1.0 },
                { 1.0, 0.0, 1.0, 0.0 },
                { 0.0, 1.0, 0.0, 1.0 },
                { 1.0, 0.0, 1.0, 0.0 }
        };

        int vertex = 2;

        int vertexDegree = getVertexDegree(adjacencyMatrix, vertex);
        System.out.println("Vertex Degree: " + vertexDegree);

        List<Integer> vertexDegrees = getVertexDegrees(adjacencyMatrix);
        System.out.println("Vertex Degrees: " + vertexDegrees);

        int inDegree = getVertexInDegree(adjacencyMatrix, vertex);
        System.out.println("In-Degree of Vertex " + vertex + ": " + inDegree);

        int outDegree = getVertexOutDegree(adjacencyMatrix, vertex);
        System.out.println("Out-Degree of Vertex " + vertex + ": " + outDegree);

        double clusteringCoefficient = calculateClusteringCoefficient(adjacencyMatrix);
        System.out.println("Clustering Coefficient: " + clusteringCoefficient);

    }

}
