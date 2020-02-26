package imageprocessor;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Alex
 */
public class myImage extends BufferedImage {

    public int width;
    public int height;
    public int imageType;
    public boolean wasModified = false;
    float[][] ogPixelMatrix;
    float[][] pixelMatrix;
    Color[][] ogColorMatrix;
    Color[][] colorMatrix;

    public myImage(int width, int height, int imageType) {
        super(width, height, imageType);

        this.width = width;
        this.height = height;
        this.imageType = imageType;
        processPicture();
    }

    public void ApplyConvolutionFilter(float[][] kernel) {

        BufferedImage outputImage = new BufferedImage(width, height, imageType);

        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {

                Color pixelColor = ApplyKernel(kernel, i, j);
                outputImage.setRGB(i, j, pixelColor.getRGB());

            }
        }

        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {

                int rgb = outputImage.getRGB(i, j);

                this.setRGB(i, j, rgb);
            }
        }

        wasModified = true;
    }

    private Color ApplyKernel(float[][] kernel, int row, int column) {
        float red = 0.0f;
        float green = 0.0f;
        float blue = 0.0f;

        int minIndexH = -(kernel.length / 2);
        int maxIndexH = minIndexH + kernel.length;
        int minIndexV = -(kernel[0].length / 2);
        int maxIndexV = minIndexV + kernel[0].length;

        for (int i = minIndexH; i < maxIndexH; ++i) {
            for (int j = minIndexV; j < maxIndexV; ++j) {
                int indexH = WrapHorizontalIndex(row + i);
                int indexV = WrapVerticalIndex(column + j);
                Color col = new Color(this.getRGB(indexH, indexV));

                red += col.getRed() * kernel[i - minIndexH][j - minIndexV];

                green += col.getGreen() * kernel[i - minIndexH][j - minIndexV];

                blue += col.getBlue() * kernel[i - minIndexH][j - minIndexV];

            }
        }

        red = Math.min(Math.max(red, 0.0f), 255.0f);
        green = Math.min(Math.max(green, 0.0f), 255.0f);
        blue = Math.min(Math.max(blue, 0.0f), 255.0f);

        return new Color((int) red, (int) green, (int) blue);
    }

    private int WrapHorizontalIndex(int i) {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + width) % width;
    }

    private int WrapVerticalIndex(int i) {
        // This takes care of negative and positive indices beyond the image resolution
        return (i + height) % height;
    }

    private void processPicture() {
       
        ogPixelMatrix = new float[height][width];
        pixelMatrix = new float[height][width];
        ogColorMatrix = new Color[height][width];
        colorMatrix = new Color[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ogColorMatrix[y][x] = new Color(this.getRGB(x, y));
                colorMatrix[y][x] = new Color(this.getRGB(x, y));
                ogPixelMatrix[y][x] = this.getRGB(x, y);
                pixelMatrix = new float[height][width];
            }

        }

    }

}
