package main.java;

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
 * 
 */
public class MatrixVisualizer {

    private MatrixVisualizer() {

    }

    /**
     * 
     * @param matrix
     * @param scale
     * @param outputFilePath
     */
    public static void visualizeGraph(double[][] matrix, int scale, String outputFilePath) {
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
                if (i == j) {
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
     * 
     * @param value
     * @return
     */
    private static Color getColor(double value) {
        if (value == 0) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    /**
     * 
     * @param backgroundColor
     * @return
     */
    private static Color getTextColor(Color backgroundColor) {
        if (backgroundColor == Color.BLACK) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    /**
     * 
     * @param g2d
     * @param text
     * @param rect
     * @param font
     */
    private static void drawCenteredString(Graphics2D g2d, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + (rect.height - metrics.getHeight()) / 2 + metrics.getAscent();
        g2d.drawString(text, x, y);
    }
}
