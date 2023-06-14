package main.java.clustering;

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

    public static double calculateDistance(double[][] distanceMatrix, int i, int j) {
        return distanceMatrix[i][j];
    }

    public static double calculateSimilarity(double[][] adjacencyMatrix, int i, int j) {
        return adjacencyMatrix[i][j];
    }

    public static List<Cluster> hierarchicalClusteringFromDistanceMatrix(double[][] distanceMatrix, int k) {
        int n = distanceMatrix.length;
        List<Cluster> clusters = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            clusters.add(new Cluster(i));
        }

        while (clusters.size() > k) {
            int numClusters = clusters.size();
            double minDistance = Double.MAX_VALUE;
            int mergeIndex1 = -1;
            int mergeIndex2 = -1;

            for (int i = 0; i < numClusters; i++) {
                for (int j = i + 1; j < numClusters; j++) {
                    double distance = calculateDistance(distanceMatrix, clusters.get(i).getPoints().get(0),
                            clusters.get(j).getPoints().get(0));
                    if (distance < minDistance) {
                        minDistance = distance;
                        mergeIndex1 = i;
                        mergeIndex2 = j;
                    }
                }
            }

            List<Integer> mergedPoints = new ArrayList<>();
            mergedPoints.addAll(clusters.get(mergeIndex1).getPoints());
            mergedPoints.addAll(clusters.get(mergeIndex2).getPoints());

            double mergedDistance = minDistance / 2.0;

            Cluster mergedCluster = new Cluster(mergedPoints, mergedDistance);
            clusters.add(mergedCluster);

            clusters.remove(mergeIndex2);
            clusters.remove(mergeIndex1);
        }

        return clusters;
    }

    public static List<Cluster> hierarchicalClusteringFromAdjacencyMatrix(double[][] adjacencyMatrix, int k) {
        int n = adjacencyMatrix.length;
        double[][] distances = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = calculateSimilarity(adjacencyMatrix, i, j);
            }
        }

        return hierarchicalClusteringFromDistanceMatrix(distances, k);
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

        List<Cluster> distanceResult = hierarchicalClusteringFromDistanceMatrix(distances, k);

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

        List<Cluster> adjacencyResult = hierarchicalClusteringFromAdjacencyMatrix(adjacencyMatrix, k);

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
