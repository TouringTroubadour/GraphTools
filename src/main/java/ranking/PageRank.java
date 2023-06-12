package main.java.ranking;

import java.util.Arrays;

import main.java.metrics.DegreeMeasures;

/**
 * This class implements the PageRank algorithm to calculate the importance
 * scores of vertices in a graph.
 */
public class PageRank {

    private PageRank() {
        // Private constructor to prevent instantiation
    }

    /**
     * Calculates the PageRank scores for vertices in a graph represented by an
     * adjacency matrix.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @param dampingFactor   The damping factor used in the PageRank calculation.
     * @param maxIterations   The maximum number of iterations for the PageRank
     *                        calculation.
     * @return The PageRank scores as a double array.
     */
    public static double[] calculate(double[][] adjacencyMatrix, double dampingFactor, int maxIterations) {
        int vertexCount = adjacencyMatrix.length;
        double[] pageRankScores = new double[vertexCount];

        // Initialize PageRank scores
        Arrays.fill(pageRankScores, 1.0 / vertexCount);

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            double[] newPageRankScores = new double[vertexCount];
            double dampingValue = (1.0 - dampingFactor) / vertexCount;

            for (int i = 0; i < vertexCount; i++) {
                for (int j = 0; j < vertexCount; j++) {
                    if (adjacencyMatrix[j][i] > 0) {
                        newPageRankScores[i] += dampingFactor * pageRankScores[j]
                                / DegreeMeasures.getVertexOutDegree(adjacencyMatrix, j);
                    }
                }
                newPageRankScores[i] += dampingValue;
            }

            pageRankScores = newPageRankScores;
        }

        return pageRankScores;
    }

    /**
     * Main method to demonstrate the usage of the PageRank calculation.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        double[][] adjacencyMatrix = {
                { 0, 1, 1, 0, 0 },
                { 1, 0, 0, 1, 0 },
                { 1, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 1 },
                { 0, 0, 0, 1, 0 }
        };

        double dampingFactor = 0.85;
        int maxIterations = 100;

        double[] pageRankScores = calculate(adjacencyMatrix, dampingFactor, maxIterations);

        System.out.println("PageRank Scores: " + Arrays.toString(pageRankScores));
    }
}
