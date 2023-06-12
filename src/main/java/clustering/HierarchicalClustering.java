package main.java.clustering;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that implements hierarchical clustering algorithm.
 */
public class HierarchicalClustering {

    private HierarchicalClustering() {
        // Private constructor to prevent instantiation
    }

    /**
     * Inner class representing a cluster.
     */
    private static class Cluster {
        private List<Integer> points; // List of points in the cluster
        private double distance; // Distance between the points in the cluster

        /**
         * Constructor for a single-point cluster.
         * 
         * @param point The initial point in the cluster.
         */
        public Cluster(int point) {
            this.points = new ArrayList<>();
            this.points.add(point);
            this.distance = 0.0;
        }

        /**
         * Constructor for a merged cluster.
         * 
         * @param points   The list of points in the cluster.
         * @param distance The distance between the points in the cluster.
         */
        public Cluster(List<Integer> points, double distance) {
            this.points = points;
            this.distance = distance;
        }

        public List<Integer> getPoints() {
            return points;
        }

        public double getDistance() {
            return distance;
        }
    }

    /**
     * Method to calculate the distance between two points using a distance matrix.
     * 
     * @param distances The distance matrix.
     * @param i         The index of the first point.
     * @param j         The index of the second point.
     * @return The distance between the two points.
     */
    private static double calculateDistance(double[][] distances, int i, int j) {
        return distances[i][j];
    }

    /**
     * Perform hierarchical clustering on the given distance matrix.
     * 
     * @param distances The distance matrix.
     * @param k         The desired number of clusters.
     * @return The list of clusters.
     */
    public static List<Cluster> hierarchicalClustering(double[][] distances, int k) {
        int n = distances.length;

        // Initialize each point as an individual cluster
        List<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            clusters.add(new Cluster(i));
        }

        // Perform hierarchical clustering until the desired number of clusters is
        // reached
        while (clusters.size() > k) {
            int numClusters = clusters.size();
            double minDistance = Double.MAX_VALUE;
            int mergeIndex1 = -1;
            int mergeIndex2 = -1;

            // Find the two closest clusters to merge
            for (int i = 0; i < numClusters; i++) {
                for (int j = i + 1; j < numClusters; j++) {
                    double distance = calculateDistance(distances, clusters.get(i).getPoints().get(0),
                            clusters.get(j).getPoints().get(0));
                    if (distance < minDistance) {
                        minDistance = distance;
                        mergeIndex1 = i;
                        mergeIndex2 = j;
                    }
                }
            }

            // Merge the two closest clusters
            List<Integer> mergedPoints = new ArrayList<>();
            mergedPoints.addAll(clusters.get(mergeIndex1).getPoints());
            mergedPoints.addAll(clusters.get(mergeIndex2).getPoints());

            double mergedDistance = minDistance / 2.0;

            Cluster mergedCluster = new Cluster(mergedPoints, mergedDistance);
            clusters.add(mergedCluster);

            // Remove the original clusters
            clusters.remove(mergeIndex2);
            clusters.remove(mergeIndex1);
        }

        return clusters;
    }

    /**
     * Performs a tree cut on the hierarchical clustering result to obtain a
     * specified number of clusters.
     *
     * @param clusters The list of clusters from hierarchical clustering.
     * @param k        The desired number of clusters.
     * @return The list of cut clusters.
     */
    public static List<List<Integer>> treeCut(List<Cluster> clusters, int k) {
        List<List<Integer>> cutClusters = new ArrayList<>();

        // Sort the clusters by their distances in descending order
        clusters.sort((c1, c2) -> Double.compare(c2.getDistance(), c1.getDistance()));

        // Perform the tree cut by selecting the top-k clusters
        for (int i = 0; i < k && i < clusters.size(); i++) {
            cutClusters.add(clusters.get(i).getPoints());
        }

        return cutClusters;
    }

    /**
     * Main method to demonstrate the usage of hierarchical clustering.
     * 
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Example usage
        double[][] distances = {
                { 0.0, 2.5, 4.2, 3.1 },
                { 2.5, 0.0, 1.7, 2.9 },
                { 4.2, 1.7, 0.0, 1.8 },
                { 3.1, 2.9, 1.8, 0.0 }
        };

        int k = 2; // Desired number of clusters

        List<Cluster> result = hierarchicalClustering(distances, k);

        // Print the resulting clusters and their distances
        for (int i = 0; i < result.size(); i++) {
            Cluster cluster = result.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster.getPoints());
            System.out.println("Distance: " + cluster.getDistance());
        }

        List<List<Integer>> cutResult = treeCut(result, k);

        // Print the resulting cut clusters
        for (int i = 0; i < cutResult.size(); i++) {
            List<Integer> cluster = cutResult.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster);
        }
    }
}
