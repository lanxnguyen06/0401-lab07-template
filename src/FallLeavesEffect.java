/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import java.util.Random;

public class FallLeavesEffect {

    /**
     * Adds random fall leaves (orange, red, yellow) to the image background.
     *
     * @param pixelArray     The 3D pixel array representing the image.
     * @param numberOfLeaves The number of leaves to add.
     */
    public static void addLeaves(int[][][] pixelArray, int numberOfLeaves) {
        Random random = new Random();
        int height = pixelArray.length;
        int width = pixelArray[0].length;

        for (int i = 0; i < numberOfLeaves; i++) {
            int x = random.nextInt(width - 40) + 20;
            int y = random.nextInt(height - 40) + 20;
            int size = random.nextInt(12) + 8; // Randomize leaf size 8–20 px
            int rotation = random.nextInt(360); // Randomized rotation of leaf in degrees

            // Avoid pumpkin area
            if (!isPumpkinPixel(pixelArray[y][x])) {
                int[] color = randomLeafColor(random);
                drawLeaf(pixelArray, x, y, size, rotation, color);
            }
        }
    }

    /**
     * Draws a simple oval-shaped leaf with rotation
     */
    private static void drawLeaf(int[][][] pixelArray, int centerX, int centerY, int size, int rotation, int[] color) {
        double radians = Math.toRadians(rotation);
        int halfWidth = size;
        int halfHeight = size / 2;

        for (int dy = -halfHeight; dy <= halfHeight; dy++) {
            for (int dx = -halfWidth; dx <= halfWidth; dx++) {
                double rx = dx * Math.cos(radians) - dy * Math.sin(radians);
                double ry = dx * Math.sin(radians) + dy * Math.cos(radians);
                double ellipseEq = (rx * rx) / (halfWidth * halfWidth) + (ry * ry) / (halfHeight * halfHeight);

                if (ellipseEq <= 1.0) { // inside oval
                    int px = centerX + dx;
                    int py = centerY + dy;
                    if (isWithinBounds(pixelArray, py, px)) {
                        pixelArray[py][px] = color;
                    }
                }
            }
        }
    }

    /**
     * Random autumn leaf colors
     */
    private static int[] randomLeafColor(Random random) {
        int[][] palette = {
                { 255, 140, 0 }, // orange
                { 255, 69, 0 }, // red-orange
                { 218, 165, 32 }, // gold
                { 205, 92, 92 }, // warm red
                { 255, 215, 0 } // yellow
        };
        return palette[random.nextInt(palette.length)];
    }

    /**
     * Check if pixel is in pumpkin
     */
    private static boolean isPumpkinPixel(int[] pixel) {
        float r = pixel[0], g = pixel[1], b = pixel[2];
        float total = r + g + b;
        if (total == 0)
            return false;
        float rRatio = r / total;
        float gRatio = g / total;
        float bRatio = b / total;
        return (rRatio > 0.45 && gRatio > 0.25 && bRatio < 0.2);
    }

    private static boolean isWithinBounds(int[][][] pixelArray, int y, int x) {
        return y >= 0 && y < pixelArray.length && x >= 0 && x < pixelArray[0].length;
    }
}
