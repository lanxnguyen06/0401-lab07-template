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
     *
     */

    public static int[][][] pixelArray;

    public static int[][][] imageToArray(BufferedImage image) { // make return type [][][] to store height width & rgb
        int width = image.getWidth();
        int height = image.getHeight();

        pixelArray = new int[height][width][3]; // 3 values for r, g, b

        for (int h = 0; h < height; h++){
            for (int r = 0; r < width; r++){
                Color color = new Color(image.getRGB(r, h));
                pixelArray[h][r][0] = color.getRed(); // 1st value of red starts at index 0 
                pixelArray[h][r][1] = color.getGreen();
                pixelArray[h][r][2] = color.getBlue();
            }
        }
        return pixelArray;
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
    public static void changeSkyColor(int[][][] pixelArray, int[] newColor) {
        int height = pixelArray.length;
        int width = pixelArray[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isWhite(pixelArray[y][x])){ // if it's true that given color is white
                    pixelArray[y][x][0] = newColor[0]; // changes R
                    pixelArray[y][x][1] = newColor[1]; // changes G
                    pixelArray[y][x][2] = newColor[2]; // changes B
                }
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
        int red = color[0];
        int green = color[1];
        int blue = color[2];
        if (red == 255 && green == 255 && blue == 255){ // set all to 255 to check if color is white
            return true;
        }
        else
        return false;
    }
}
