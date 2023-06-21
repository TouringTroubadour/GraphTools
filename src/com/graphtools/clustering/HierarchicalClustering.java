package com.graphtools.clustering;

import java.util.ArrayList;
import java.util.List;

public class HierarchicalClustering {

    private HierarchicalClustering() {
        // Private constructor to prevent instantiation
    }

    public static class Cluster {
        private List<Integer> points;
        private double distance;

        public Cluster(int point) {
            this.points = new ArrayList<>();
            this.points.add(point);
            this.distance = 0.0;
        }

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

    public static double calculateDistance(double[][] matrix, int i, int j) {
        return matrix[i][j];
    }

    public static List<Cluster> hierarchicalClustering(double[][] matrix, int k) {
        int n = matrix.length;

        // Initialize each point as an individual cluster
        List<Cluster> clusters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            clusters.add(new Cluster(i));
        }

        // Perform hierarchical clustering until the desired number of clusters is
        // reached
        while (clusters.size() > k || k == 0) {
            int numClusters = clusters.size();
            double minDistance = Double.MAX_VALUE;
            int mergeIndex1 = -1;
            int mergeIndex2 = -1;

            // Find the two closest clusters to merge
            for (int i = 0; i < numClusters; i++) {
                for (int j = i + 1; j < numClusters; j++) {
                    double distance = calculateDistance(matrix, clusters.get(i).getPoints().get(0),
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

    public static List<List<Integer>> treeCut(List<Cluster> clusters, int k) {
        List<List<Integer>> cutClusters = new ArrayList<>();

        clusters.sort((c1, c2) -> Double.compare(c2.getDistance(), c1.getDistance()));

        for (int i = 0; i < k && i < clusters.size(); i++) {
            cutClusters.add(clusters.get(i).getPoints());
        }

        return cutClusters;
    }

    public static void main(String[] args) {
        double[][] distances = {
                { 0.0, 2.5, 4.2, 3.1 },
                { 2.5, 0.0, 1.7, 2.9 },
                { 4.2, 1.7, 0.0, 1.8 },
                { 3.1, 2.9, 1.8, 0.0 }
        };

        double[][] adjacencyMatrix = {
                { 0.0, 1.0, 1.0, 0.0 },
                { 1.0, 0.0, 1.0, 1.0 },
                { 1.0, 1.0, 0.0, 0.0 },
                { 0.0, 1.0, 0.0, 0.0 }
        };

        int k = 2;

        List<Cluster> distanceResult = hierarchicalClustering(distances, k);

        for (int i = 0; i < distanceResult.size(); i++) {
            Cluster cluster = distanceResult.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster.getPoints());
            System.out.println("Distance: " + cluster.getDistance());
        }

        List<List<Integer>> distanceCutResult = treeCut(distanceResult, k);

        for (int i = 0; i < distanceCutResult.size(); i++) {
            List<Integer> cluster = distanceCutResult.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster);
        }

        List<Cluster> adjacencyResult = hierarchicalClustering(adjacencyMatrix, k);

        for (int i = 0; i < adjacencyResult.size(); i++) {
            Cluster cluster = adjacencyResult.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster.getPoints());
            System.out.println("Distance: " + cluster.getDistance());
        }

        List<List<Integer>> adjacencyCutResult = treeCut(adjacencyResult, k);

        for (int i = 0; i < adjacencyCutResult.size(); i++) {
            List<Integer> cluster = adjacencyCutResult.get(i);
            System.out.println("Cluster " + (i + 1) + ": " + cluster);
        }
    }
}
