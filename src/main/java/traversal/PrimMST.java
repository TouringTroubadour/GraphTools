package main.java.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Prim's Minimum Spanning Tree algorithm implementation.
 */
public class PrimMST {

    private PrimMST() {
        // Private constructor to prevent creating instances
    }

    /**
     * Represents an edge in the graph.
     */
    private static class Edge {
        private int source;
        private int destination;
        private int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    /**
     * Represents a node in the graph.
     */
    private static class Node {
        private int id;
        private List<Edge> edges;

        public Node(int id) {
            this.id = id;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int destination, int weight) {
            edges.add(new Edge(id, destination, weight));
        }
    }

    /**
     * Finds the Minimum Spanning Tree of a graph using Prim's algorithm.
     *
     * @param adjacencyMatrix The adjacency matrix representing the graph.
     * @return The list of edges in the Minimum Spanning Tree.
     */
    public static List<Edge> findMST(int[][] adjacencyMatrix) {
        int numNodes = adjacencyMatrix.length;

        // Create nodes
        Node[] nodes = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            nodes[i] = new Node(i);
        }

        // Populate edges
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (adjacencyMatrix[i][j] > 0) {
                    nodes[i].addEdge(j, adjacencyMatrix[i][j]);
                    nodes[j].addEdge(i, adjacencyMatrix[i][j]);
                }
            }
        }

        // Perform Prim's algorithm
        List<Edge> mst = new ArrayList<>();
        boolean[] visited = new boolean[numNodes];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        // Start with node 0 as the initial node
        visited[0] = true;
        addEdgesToHeap(nodes[0], minHeap, visited);

        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int destination = edge.destination;

            if (visited[destination]) {
                continue; // Skip if the destination node is already visited
            }

            // Add the edge to the Minimum Spanning Tree
            mst.add(edge);
            visited[destination] = true;
            addEdgesToHeap(nodes[destination], minHeap, visited);
        }

        return mst;
    }

    /**
     * Adds the edges of a node to the priority queue.
     *
     * @param node     The node.
     * @param minHeap  The priority queue.
     * @param visited  An array to track visited nodes.
     */
    private static void addEdgesToHeap(Node node, PriorityQueue<Edge> minHeap, boolean[] visited) {
        for (Edge edge : node.edges) {
            int destination = edge.destination;
            if (!visited[destination]) {
                minHeap.offer(edge);
            }
        }
    }

    public static void main(String[] args) {
        // Example usage
        int[][] adjacencyMatrix = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        List<Edge> mst = findMST(adjacencyMatrix);

        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : mst) {
            System.out.println(edge.source + " - " + edge.destination + ", weight: " + edge.weight);
        }
    }
}
