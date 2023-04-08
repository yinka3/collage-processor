package cs3500.imageutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The PPMUtil class is a utility class for a ppm file to be used as an image for modification
 * and then added onto a layer of choice.
 * The PPMUtil class specifically covers for file reading and write for ppm Images.
 */
public class PPMUtil {


  private int height;
  private int width;
  private final String file;
  private RGBA[][] pixels;

  /**
   * A constructor used for reading in ppm files from a filepath from your local filesystem.
   * @param filepath is the absolute file path used to read in.
   */
  public PPMUtil(String filepath) {
    this.file = this.removeExtension(filepath);
    this.readPPM(filepath);
  }

  /**
   * A constructor is used for saving ppm files and getting in the appropriate dimensions and
   * file contents.
   * @param rgba the file contents to be saved, which are the RGBA values.
   * @param filepath the file path to be used.
   * @param height the height of the image.
   * @param width the width of the image.
   */
  public PPMUtil(RGBA[][] rgba, String filepath, int height, int width) {
    RGBA[][] temp = new RGBA[height][width];
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        temp[y][x] = rgba[y][x].copy();
      }
    }

    this.file = this.removeExtension(filepath);
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
  public String removeExtension(String filePath) {
    String fileName = new File(filePath).getName();
    int dotIndex = fileName.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
      fileName = fileName.substring(0, dotIndex);
    }
    return fileName;
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
  public RGBA getPixelAt(int y, int x) throws IllegalArgumentException {
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
    try {
      PrintWriter writer = new PrintWriter(new FileOutputStream(filepath));
      writer.write("P3\n");
      writer.write(this.width + " " + this.height + "\n");
      writer.write("255\n");

      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          double alpha = this.pixels[y][x].getAlpha() ;
          int red = (int) (this.pixels[y][x].getRed() * alpha / 255);
          int green = (int) (this.pixels[y][x].getGreen() * alpha / 255);
          int blue = (int) (this.pixels[y][x].getBlue() * alpha / 255);
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
