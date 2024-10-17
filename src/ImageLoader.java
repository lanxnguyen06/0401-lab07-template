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

public final class ImageLoader {
    /**
     * Loads an image from a specified path
     * 
     * @param filePath Path to the image
     * @return Loaded image as BufferedImage
     * @throws IOException
     */
    public static BufferedImage loadImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }
}