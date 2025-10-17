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
        if (args.length == 0){
            System.out.println("No image file provided.");
            return; // if no image exit the program
        }

        String filename = args[0]; // gets the image from command line
    
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error: File '" + filename + "' not found!");
            return;
        }
        System.out.println("Loading image: " + filename);
        processImage(filename);
    }

    private static void processImage(String path) {

        try {
            System.out.println(path);

            BufferedImage originalImage = ImageIO.read(new File(path)); // loads the image
            System.out.println("Image loaded successfully.");

            int [][][] pixelArray = ImageProcessor.imageToArray(originalImage); // convert image into 3d array

            int [] newSkyColor = {45, 53, 122};
            ImageProcessor.changeSkyColor(pixelArray, newSkyColor);
            System.out.println("Sky color changed: " + Arrays.toString(newSkyColor));

            FallEffect.recolorTrees(pixelArray, 25); // recolors trees
            
            OrangeHueFilter.applyOrangeHue(pixelArray, 40); // adds orange hue

            FallLeavesEffect.addLeaves(pixelArray, 100); // adds leaves

            BufferedImage modifiedImage = ImageProcessor.arrayToImage(pixelArray); // convert array back to image

            String outputPath = ImageSaver.saveImage(modifiedImage, path); // saves modified image
            System.out.println("Modified image saved as: " + outputPath);

        } catch (IOException e) {
            System.err.println("Error processing the image: " + e.getMessage());
        }
    }
}
