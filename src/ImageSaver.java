/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/******************************
 * NO NEED TO TOUCH THIS FILE *
 ******************************/

public final class ImageSaver {
    /**
     * Saves a BufferedImage to a specified path
     * 
     * @param image      BufferedImage to be saved
     * @param outputPath Location where the image will be saved to
     * @throws IOException
     */
    public static String saveImage(BufferedImage image, String path) throws IOException {
        String outputPath = buildOutputPath(path);
        ImageIO.write(image, "png", new File(outputPath));
        return outputPath;
    }

    private static String buildOutputPath(String inputPath) {
        return inputPath.replace(".png", "-filtered.png");
    }
}