package main.java.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Utility class for visualizing a matrix as a graph.
 */
public class MatrixVisualizer {

    private MatrixVisualizer() {
        // Private constructor to prevent instantiation
    }

    /**
     * Visualizes the given matrix as a graph and saves it to an image file.
     *
     * @param matrix                 The matrix to visualize.
     * @param scale                  The scale factor for node size.
     * @param outputFilePath         The path of the output image file.
     * @param highlightLeadingDiagonal Determines whether to highlight the leading diagonal elements.
     */
    public static void visualizeGraph(double[][] matrix, int scale, String outputFilePath, boolean highlightLeadingDiagonal) {
        int size = matrix.length;
        int imageSize = size * scale;
        BufferedImage image = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set background color
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, imageSize, imageSize);

        // Draw nodes and leading diagonal
        int nodeSize = scale;
        Font font = new Font("Arial", Font.PLAIN, nodeSize);
        g2d.setFont(font);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int x = j * scale;
                int y = i * scale;
                Color color;
                if (i == j && highlightLeadingDiagonal) {
                    color = Color.RED; // Leading diagonal
                } else {
                    color = getColor(matrix[i][j]);
                }
                g2d.setColor(color);
                g2d.fillRect(x, y, nodeSize, nodeSize);

                // Draw node value
                String value = String.valueOf((int) matrix[i][j]);
                Rectangle bounds = new Rectangle(x, y, nodeSize, nodeSize);
                g2d.setColor(getTextColor(color));
                drawCenteredString(g2d, value, bounds, font);
            }
        }

        g2d.dispose();

        // Save the image to file
        try {
            File outputFile = new File(outputFilePath);
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Determines the color for a node based on its value.
     *
     * @param value The value of the node.
     * @return The color for the node.
     */
    private static Color getColor(double value) {
        if (value == 0) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    /**
     * Determines the text color based on the background color.
     *
     * @param backgroundColor The background color.
     * @return The text color.
     */
    private static Color getTextColor(Color backgroundColor) {
        if (backgroundColor == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * Draws a string at the center of a rectangle.
     *
     * @param g2d   The graphics object.
     * @param text  The text to draw.
     * @param rect  The rectangle to center the text in.
     * @param font  The font for the text.
     */
    private static void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        g2d.drawString(text, x, y);
    }

    public static void main(String[] args) {
        // Example usage
        double[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int scale = 50;
        String outputFilePath = "output.png";
        boolean highlightLeadingDiagonal = true;

        MatrixVisualizer.visualizeGraph(matrix, scale, outputFilePath, highlightLeadingDiagonal);

        System.out.println("Graph visualization saved to: " + outputFilePath);
    }
}
