/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

public class OrangeHueFilter {

    /**
     * Applies a warm orange filter with enhanced contrast and controlled brightness
     * lift.
     * Preserves dark tones, warms midtones, and enriches highlights.
     *
     * @param pixelArray 3D pixel array [height][width][RGB]
     * @param intensity  0–100 (recommended use: 30–60)
     */
    public static void applyOrangeHue(int[][][] pixelArray, int intensity) {
        float blend = Math.max(0f, Math.min(1f, intensity / 100f));
        float contrast = 1.0f + 0.8f * blend;
        int brightnessBoost = Math.round(10 * blend);

        int height = pixelArray.length;
        int width = pixelArray[0].length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = pixelArray[y][x]; //retrieves RGB array for current pixel
                

                float redNormalized = adjustContrast(pixelArray[y][x][0] / 255f, contrast);
                float greenNormalized = adjustContrast(pixelArray[y][x][1] / 255f, contrast);
                float blueNormalized = adjustContrast(pixelArray[y][x][2] / 255f, contrast);

                int red = clamp(Math.round(redNormalized * 255 + brightnessBoost));
                int green = clamp(Math.round(greenNormalized * 255 + brightnessBoost));
                int blue = clamp(Math.round(blueNormalized * 255 + brightnessBoost));

                // Blend toward warm orange tone
                red = blendColor(red, 255, blend);
                green = blendColor(green, 140, blend);
                blue = blendColor(blue, 0, blend);

                pixelArray[y][x][0] = red;
                pixelArray[y][x][1] = green;
                pixelArray[y][x][2] = blue; // writes new RGB values back into pixel array
            }
        }
    }

    // No need to touch the methods below!

    /**
     * Adjusts image contrast while keeping midtones stable:
     * contrast == 1.0: -> no change
     * contrast > 1.0: -> higher contrast
     * contrast < 1.0: -> lower contrast
     * 
     * @param value    brightness value between 0.0 (black) and 1.0 (white)
     * @param contrast multiplier controlling how far pixel values are from 0.5
     * @return contrast-adjusted brightness of a single color channel
     */
    private static float adjustContrast(float value, float contrast) {
        // Contrast curve centered around 0.5
        return Math.max(0f, Math.min(1f, (value - 0.5f) * contrast + 0.5f));
    }

    /**
     * Clamp restricts a numeric value to a defined range
     * 
     * @param value to be restricted
     * @return 0 if value < 0, return 255 if value > 255, else return the value
     */
    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    /**
     * Linearly mixes two color values: original pixel value & a target color
     * value by a specified ratio (= blend)
     * If blend = 0, the result is 100% original color
     * If blend = 1, the result is 100% target color
     * If blend = 0.5, the result is a 50/50 mix
     * 
     * @param original value of a color channel
     * @param target   value of a color channel
     * @param blend    what percentage to mix the two colors
     * @return blended color value
     */
    private static int blendColor(int original, int target, float blend) {
        return clamp(Math.round(original * (1 - blend) + target * blend));
    }
}
