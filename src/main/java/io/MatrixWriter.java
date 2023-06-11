package main.java.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MatrixWriter {

    private MatrixWriter() {
        // Private constructor to prevent instantiation
    }

    /**
     * Writes a matrix to a file.
     *
     * @param matrix    The matrix to write.
     * @param filePath  The path of the file to write.
     * @param separator The separator to use between values.
     */
    public static void writeMatrixToFile(double[][] matrix, String filePath, String separator) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (double[] row : matrix) {
                for (int i = 0; i < row.length; i++) {
                    writer.print(row[i]);
                    if (i < row.length - 1) {
                        writer.print(separator);
                    }
                }
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes a matrix to a DOT file for visualization.
     *
     * @param matrix       The matrix to write.
     * @param dotFilePath  The path of the DOT file to write.
     * @param addWeighting Determines whether to add edge weight labels.
     */
    public static void writeMatrixToDotFile(double[][] matrix, String dotFilePath, boolean addWeighting) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dotFilePath))) {
            writer.write("graph G {");
            writer.newLine();

            int numVertices = matrix.length;
            for (int i = 0; i < numVertices; i++) {
                writer.write("  " + i + ";");
                writer.newLine();
            }

            for (int i = 0; i < numVertices; i++) {
                for (int j = i + 1; j < numVertices; j++) {
                    double weight = matrix[i][j];
                    if (weight != 0.0) {
                        if (addWeighting) {
                            writer.write("  " + i + " -- " + j + " [label=\"" + weight + "\"];");
                        } else {
                            writer.write("  " + i + " -- " + j + ";");
                        }
                        writer.newLine();
                    }
                }
            }

            writer.write("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Example usage of MatrixWriter.
     */
    public static void main(String[] args) {
        // Example usage of writeMatrixToFile
        double[][] matrix = {
                { 1.0, 2.0, 3.0 },
                { 4.0, 5.0, 6.0 },
                { 7.0, 8.0, 9.0 }
        };
        String filePath = "path/to/your/file.txt";
        String separator = ",";

        MatrixWriter.writeMatrixToFile(matrix, filePath, separator);

        // Example usage of writeMatrixToDotFile
        double[][] weightedMatrix = {
                { 0.0, 1.0, 2.0 },
                { 1.0, 0.0, 0.0 },
                { 2.0, 0.0, 0.0 }
        };
        String dotFilePath = "path/to/your/graph.dot";
        boolean addWeighting = true;

        MatrixWriter.writeMatrixToDotFile(weightedMatrix, dotFilePath, addWeighting);
    }
}
