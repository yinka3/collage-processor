package cs3500.imageutil;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * The PPMUtil class is a utility class for a ppm file to be used as an image for modification
 * and then added onto a layer of choice.
 * The PPMUtil class specifically covers for file reading and write for ppm Images.
 */
public class ImageUtil implements IImageUtil {


  private int height;
  private int width;
  private String file;
  private IRGBA[][] pixels;
  private static int maxValue = 255;

  /**
   * A constructor used for reading in ppm files from a filepath from your local filesystem.
   * @param filepath is the absolute file path used to read in.
   */
  public ImageUtil(String filepath) {
    if (filepath.endsWith(".ppm")) {
      this.readPPM(filepath);
    }
    else {
      this.readFiles(filepath);
    }
  }

  /**
   * A constructor is used for saving ppm files and getting in the appropriate dimensions and
   * file contents.
   * @param rgba the file contents to be saved, which are the RGBA values.
   * @param filepath the file path to be used.
   * @param height the height of the image.
   * @param width the width of the image.
   */
  public ImageUtil(IRGBA[][] rgba, String filepath, int height, int width) {
    IRGBA[][] temp = new RGBA[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        temp[y][x] = rgba[y][x].copy();
      }
    }

    this.height = height;
    this.width = width;
    this.pixels = temp;
  }

  /**
   * Gets the height field of a ppm image.
   * @return the height field of class.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the width field of a ppm image.
   * @return the width field of class.
   */
  public int getWidth() {
    return width;
  }

  /**
   * This is used for needing to remove extension from a path ex. ".ppm" ".jpg".
   * @param filePath the filepath to be taken out its extension.
   * @return the filepath without an extension.
   */
  private String removeExtension(String filePath) {
    String fileName = new File(filePath).getName();
    int dotIndex = fileName.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
      fileName = fileName.substring(0, dotIndex);
    }
    return fileName;
  }

  private String getFileExtension(String file) {
    int lastIndexOf = file.lastIndexOf(".");
    if (lastIndexOf == -1) {
      return ""; // empty extension
    }
    return file.substring(lastIndexOf);
  }

  /**
   * This method is used to take in a file path then read it's contents and
   * transform the ppm image to 2D array of RGBA components.
   * @param filename is the filename to be read into the program.
   */
  public void readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File not found: " + filename);
    }
    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.equals("")) {
        continue;
      }
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");

    }
    this.width = sc.nextInt();
    this.height = sc.nextInt();
    int maxValue = sc.nextInt();

    this.pixels = new RGBA[this.height][this.width];

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.pixels[y][x] = new RGBA(r, g, b, maxValue);
      }
    }
  }

  /**
   * This method is used to take in a file of either type "jpg" or "png" and read the
   * contents and transform it to a 2D array of RGBA components.
   * @param filename is the filename to be read and modified.
   */
  public void readFiles(String filename) {
    try {
      File imageFile = new File(filename);
      BufferedImage img = ImageIO.read(imageFile);
      this.width = img.getWidth();
      this.height = img.getHeight();
      IRGBA[][] pixels = new RGBA[height][width];
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          Color c = new Color(img.getRGB(x, y));
          pixels[y][x] = new RGBA(c.getRed(), c.getGreen(), c.getBlue());

        }
      }
      this.pixels = pixels;
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * checks if the x and y coordinates are in bound of the photo.
   * False if it is out and true otherwise.
   * @param y is the y coordinate to search for.
   * @param x is the x coordinate to search for.
   * @return a boolean indicating whether the coordinates are in bound.
   */
  private boolean isOutOfBounds(int y, int x) {
    return y < 0 || x < 0 || x > this.width || y > this.height;
  }

  /**
   * Finds the x and y coordinates of a pixel on a photo.
   * Uses the 2D RGBA of the ppm as a copy to avoid aliasing problems.
   * @param y the y coordinate of the pixel.
   * @param x the x coordinate of the pixel.
   * @return the x and y coordinates of the pixel.
   * @throws IllegalArgumentException if the coordinates are not in bound.
   */
  public IRGBA getPixelAt(int y, int x) throws IllegalArgumentException {
    if (isOutOfBounds(y, x)) {
      throw new IllegalArgumentException("invalid pixel");
    }
    return this.pixels[y][x].copy();
  }

  /**
   * The method is used to get the contents of all the layers photos and make it
   * into a PPM-style format.
   * @param filepath the path to be saved as.
   */
  public void savePPM(String filepath) {
    if (filepath.endsWith(".ppm")) {
      try {
        PrintWriter writer = new PrintWriter(new FileOutputStream(filepath));
        writer.write("P3\n");
        writer.write(this.width + " " + this.height + "\n");
        writer.write("255\n");

        for (int y = 0; y < height; y++) {
          for (int x = 0; x < width; x++) {
            double alpha = this.pixels[y][x].getAlpha();
            int red = (int) (this.pixels[y][x].getRed() * alpha / maxValue);
            int green = (int) (this.pixels[y][x].getGreen() * alpha / maxValue);
            int blue = (int) (this.pixels[y][x].getBlue() * alpha / maxValue);
            writer.write(red + " " + green + " " + blue + " ");
          }
          writer.println();

        }
        writer.close();
        // throws IOException if something goes wrong with the saving process.
      } catch (IOException e) {
        throw new IllegalArgumentException("Error saving file: " + e.getMessage());
      }
    }
  }

  /**
   * The method is used to get the contents of all the layers and format the contents
   * into that of either a jpg or a png image.
   * @param filepath the path to be saved as.
   */
  public void saveFile(String filepath) {
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        double a = pixels[y][x].getAlpha();
        int r = (int) (pixels[y][x].getRed() * a / maxValue);
        int g = (int) (pixels[y][x].getGreen() * a / maxValue);
        int b = (int) (pixels[y][x].getBlue() * a / maxValue);
        Color c = new Color(r, g, b);
        img.setRGB(x, y, c.getRGB());
      }
    }
    File imageFile = new File(filepath);
    if (filepath.endsWith(".png")) {
      try {
        ImageIO.write(img, "png", imageFile);
      } catch (IOException e) {
        throw new IllegalArgumentException("Error saving file: " + e.getMessage());
      }
    } else if (filepath.endsWith("jpg")) {
      try {
        ImageIO.write(img,  "jpg", imageFile);
      } catch (IOException e) {
        throw new IllegalArgumentException("Error saving file: " + e.getMessage());
      }
    }
  }
}

