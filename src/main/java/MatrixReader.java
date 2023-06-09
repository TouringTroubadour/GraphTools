package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class MatrixReader {

    private MatrixReader() {

    }

    /**
     * 
     * @param <T>
     * @param filePath
     * @param separator
     * @param elementType
     * @return
     */
    public static <T> T[][] readMatrixFromFile(String filePath, String separator, Class<T> elementType) {
        List<T[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(separator);
                T[] row = createRow(tokens.length, elementType);
                for (int i = 0; i < tokens.length; i++) {
                    row[i] = parseElement(tokens[i], elementType);
                }
                rows.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numRows = rows.size();
        T[][] matrix = createMatrix(numRows, elementType);
        for (int i = 0; i < numRows; i++) {
            matrix[i] = rows.get(i);
        }

        return matrix;
    }

    /**
     * 
     * @param <T>
     * @param length
     * @param elementType
     * @return
     */
    private static <T> T[] createRow(int length, Class<T> elementType) {
        @SuppressWarnings("unchecked")
        T[] row = (T[]) java.lang.reflect.Array.newInstance(elementType, length);
        return row;
    }

    /**
     * 
     * @param <T>
     * @param numRows
     * @param elementType
     * @return
     */
    private static <T> T[][] createMatrix(int numRows, Class<T> elementType) {
        @SuppressWarnings("unchecked")
        T[][] matrix = (T[][]) java.lang.reflect.Array.newInstance(elementType, numRows, 0);
        return matrix;
    }

    /**
     * 
     * @param <T>
     * @param token
     * @param elementType
     * @return
     */
    private static <T> T parseElement(String token, Class<T> elementType) {
        if (elementType.equals(Integer.class)) {
            return elementType.cast(Integer.parseInt(token));
        } else if (elementType.equals(Double.class)) {
            return elementType.cast(Double.parseDouble(token));
        } else if (elementType.equals(Short.class)) {
            return elementType.cast(Short.parseShort(token));
        }
        // Add support for other element types as needed
        return null;
    }
    
}
