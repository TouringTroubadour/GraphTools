package com.graphtools.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MatrixReader {

    private MatrixReader() {
        // Private constructor to prevent instantiation
    }

    /**
     * Reads a double matrix from a file.
     *
     * @param filePath  The path of the file to read.
     * @param separator The separator used to split values in each line.
     * @return The double matrix read from the file.
     */
    public static double[][] readDoubleMatrixFromFile(String filePath, String separator) {
        List<double[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(separator);
                double[] row = createDoubleRow(tokens.length);
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = Double.parseDouble(tokens[i]);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numRows = rows.size();
        double[][] matrix = createDoubleMatrix(numRows);
        for (int i = 0; i < numRows; i++) {
            matrix[i] = rows.get(i);
        }

        return matrix;
    }

    /**
     * Creates a new double array with the specified length.
     *
     * @param length The length of the array.
     * @return The newly created double array.
     */
    private static double[] createDoubleRow(int length) {
        return new double[length];
    }

    /**
     * Creates a new double matrix with the specified number of rows.
     *
     * @param numRows The number of rows in the matrix.
     * @return The newly created double matrix.
     */
    private static double[][] createDoubleMatrix(int numRows) {
        return new double[numRows][];
    }

    /**
     * Example usage of MatrixReader.
     */
    public static void main(String[] args) {
        // Specify the file path and separator
        String filePath = "path/to/your/file.txt";
        String separator = ",";

        // Read the double matrix from the file
        double[][] matrix = MatrixReader.readDoubleMatrixFromFile(filePath, separator);

        // Display the read matrix
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
