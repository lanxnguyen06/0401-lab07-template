/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageProcessor {

    /**
     * Converts an image to a 3D array representing RGB values.
     * 
     * @param image BufferedImage to convert.
     * @return 3D array of pixels [height][width][RGB].
     */
    public static /* TODO needs return type */ void imageToArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        // The image dimensions should represent the following: height x width x RGB
        // TODO: Decide on an appropriate data type
        // TODO: pixelArray = new ...;

        // TODO: Iterate through the array and retrieve the individual RGB values
        // To access the individual RGB values, you can use the following:
        // x and y represent the image coordinates (regarding height, width)
        // Color color = new Color(image.getRGB(x, y));
        // color.getRed();
        // color.getGreen();
        // color.getBlue();

        // TODO: Return the array
        return;
    }

    /**
     * Converts a 3D array back into a BufferedImage
     * 
     * @param pixelArray 3D array [height][width][RGB] containing image data
     * @return BufferedImage representation
     */
    public static BufferedImage arrayToImage(int[][][] pixelArray) {
        int height = pixelArray.length;
        int width = pixelArray[0].length;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = pixelArray[y][x][0];
                int g = pixelArray[y][x][1];
                int b = pixelArray[y][x][2];
                Color color = new Color(r, g, b);
                image.setRGB(x, y, color.getRGB());
            }
        }
        return image;
    }

    /**
     * Changes the sky color from green to a specified new color using a 3D array
     * 
     * @param pixelArray The 3D array of pixel data
     * @param newColor   The new RGB color values
     */
    // TODO: Change the sky color from blue to a given color
    public static void changeskyColor(/* TODO type pixelArray, */ int[] newColor) {
        int height = 0; // TODO height of the pixelArray
        int width = 0; // TODO width of the pixelArray

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // TODO: If the current pixel is white, change the color (RGB)
                // if (isWhite(pixelArray[y][x])) {
                // ...
                // }
            }
        }
    }

    /**
     * Checks if a given color is approximately white
     * 
     * @param color RGB array
     * @return True if the color is green, otherwise false
     */
    private static boolean isWhite(int[] color) {
        // TODO: check if the given pixel is white
        // You need to check all three color channels
        // -> white = rgb(255, 255, 255)
        return false;
    }
}
