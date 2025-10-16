/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class PumpkinPix {

    public static void main(String[] args) {
        BufferedImage image = ImageIO.read(new File(path));
        // TODO: Configure commandline arguments to read in file names
        // Create a launch.json file & add "pumpkin.png" to the args
        // You can add your own images, too (no need to upload them)!
        // Reminder: Ensure proper error handling!

        for (String inputPath : args) {
            processImage(inputPath);
        }
    }

    private static void processImage(String path) {

        try {
            System.out.println(path);

            // First, we need to load the image
            BufferedImage originalImage = ImageIO.read(new File(path));
            System.out.println("Image loaded successfully.");

            // TODO Task 1a: Convert image to 3D array
            // Make use of ImageProcessor.imageToArray
            // pixelArray = ... ;

            // TODO 1b: Change the sky color from white to a new color
            // Define a newSkyColor

            // TODO 1b: Uncomment
            // ImageProcessor.changeSkyColor(pixelArray, newSkyColor);
            // System.out.println("Sky color changed: " + Arrays.toString(newSkyColor));

            // TODO 2: Add effects
            // Add orange hue

            // TODO 2: Recolor trees
            // Use FallEffect!

            // TODO 2: apply orange filter
            // Use OrangeHueFilter

            // TODO 2: Add leaves
            // Use FallLeavesEffect to add leaves

            // Convert back to image
            BufferedImage modifiedImage = ImageProcessor.arrayToImage(pixelArray);

            // Save the modified image
            String outputPath = ImageSaver.saveImage(modifiedImage, path);
            System.out.println("Modified image saved as: " + outputPath);

        } catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }
}
