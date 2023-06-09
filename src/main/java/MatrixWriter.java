package main.java;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */
public class MatrixWriter {

    private MatrixWriter() {

    }

    /**
     * 
     * @param <T>
     * @param matrix
     * @param filePath
     * @param separator
     */
    public static <T> void writeMatrixToFile(T[][] matrix, String filePath, String separator) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (T[] row : matrix) {
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
     * 
     * @param <T>
     * @param matrix
     * @param dotFilePath
     * @param addWeighting
     */
    public static <T extends Number> void writeMatrixToDotFile(T[][] matrix, String dotFilePath, boolean addWeighting) {
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
                    double weight = matrix[i][j].doubleValue();
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
}
