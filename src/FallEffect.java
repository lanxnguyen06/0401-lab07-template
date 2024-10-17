
/*
 * Created on 2025-10-09
 *
 * Copyright (c) 2025 Nadine von Frankenberg
 */

import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

/******************************
 * NO NEED TO TOUCH THIS FILE *
 ******************************/

public class FallEffect {

    /**
     * Paints trees that are approxitately green
     * 
     * @param pixelArray
     * @param variation  randomness added to each fall color, small variation = more
     *                   uniform tone, larger variation = more colorful
     */
    public static void recolorTrees(int[][][] pixelArray, int variation) {
        Random random = new Random();
        int height = pixelArray.length;
        int width = pixelArray[0].length;
        boolean[][] visited = new boolean[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!visited[y][x] && isTreePixel(pixelArray[y][x])) {
                    int[] referenceColor = pixelArray[y][x]; // hue baseline
                    int[] newColor = randomFallColor(random, variation);
                    floodFill(pixelArray, visited, x, y, referenceColor, newColor);
                }
            }
        }
        expandTreeEdges(pixelArray, 1);
    }

    /**
     * Flood-fill by spatial adjacency + color similarity.
     */
    private static void floodFill(int[][][] pixels, boolean[][] visited,
            int startX, int startY,
            int[] refColor, int[] newColor) {
        int h = pixels.length, w = pixels[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { startX, startY });
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0], y = p[1];
            pixels[y][x] = newColor;

            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx++) {
                    if (dx == 0 && dy == 0)
                        continue;
                    int nx = x + dx, ny = y + dy;
                    if (nx < 0 || nx >= w || ny < 0 || ny >= h)
                        continue;
                    if (visited[ny][nx])
                        continue;
                    int[] neighbor = pixels[ny][nx];
                    if (isTreePixel(neighbor) && colorDistance(neighbor, refColor) < 5) {
                        visited[ny][nx] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
    }

    /**
     * Approximate green detector
     */
    private static boolean isTreePixel(int[] pixel) {
        float red = pixel[0];
        float green = pixel[1];
        float blue = pixel[2];

        float total = red + green + blue;
        if (total == 0) {
            return false;
        }
        float rRatio = red / total;
        float gRatio = green / total;
        float bRatio = blue / total;
        return (gRatio > 0.35 && rRatio < 0.4 && bRatio < 0.4);
    }

    /** Euclidean distance between two RGB colors */
    private static double colorDistance(int[] color1, int[] color2) {
        int dr = color1[0] - color2[0];
        int dg = color1[1] - color2[1];
        int db = color1[2] - color2[2];
        return Math.sqrt(dr * dr + dg * dg + db * db);
    }

    /** Palette + small brightness noise */
    private static int[] randomFallColor(Random random, int variation) {
        int[][] palette = {
                { 128, 118, 19 }, // khaki
                { 255, 106, 77 }, // coral
                { 191, 255, 77 }, // light green
                { 255, 202, 77 }, // yellow
                { 128, 19, 52 }, // red
        };
        int[] base = palette[random.nextInt(palette.length)];
        return new int[] {
                clamp(base[0] + random.nextInt(variation) - variation / 2),
                clamp(base[1] + random.nextInt(variation) - variation / 2),
                clamp(base[2] + random.nextInt(variation) - variation / 2)
        };
    }

    private static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    /**
     * Expands colored regions outward to cover leftover anti-aliased edges
     * Run after recolorTrees()!
     */
    public static void expandTreeEdges(int[][][] pixels, int radius) {
        int h = pixels.length, w = pixels[0].length;
        int[][][] copy = new int[h][w][3];

        // Copy current image
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++)
                copy[y][x] = Arrays.copyOf(pixels[y][x], 3);

        for (int y = radius; y < h - radius; y++) {
            for (int x = radius; x < w - radius; x++) {
                if (isOutlinePixel(copy[y][x])) {
                    int[] dominant = dominantNeighborColor(copy, x, y, radius);
                    pixels[y][x] = dominant;
                }
            }
        }
    }

    private static boolean isOutlinePixel(int[] color) {
        // yellowish or greenish edge detection
        return (color[1] > 100 && color[0] > 80 && color[2] < 100);
    }

    private static int[] dominantNeighborColor(int[][][] pixels, int cx, int cy, int radius) {
        Map<String, int[]> colors = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();

        for (int dy = -radius; dy <= radius; dy++) {
            for (int dx = -radius; dx <= radius; dx++) {
                int[] c = pixels[cy + dy][cx + dx];
                String key = c[0] / 10 + "," + c[1] / 10 + "," + c[2] / 10;
                colors.put(key, c);
                counts.put(key, counts.getOrDefault(key, 0) + 1);
            }
        }

        String dominantKey = Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey();
        return colors.get(dominantKey);
    }
}
