package cs3500.ImageUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PPMUtil {

  int height;
  int width;
  String file;
  RGBA[][] pixels;

  public PPMUtil(String filepath) {

    this.file = this.removeExtension(filepath);
    this.readPPM(filepath);
  }


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


  public int getHeight() {
    return height;
  }


  public int getWidth() {
    return width;
  }

  public String removeExtension(String filePath) {
    String fileName = new File(filePath).getName();
    int dotIndex = fileName.lastIndexOf('.');
    if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
      fileName = fileName.substring(0, dotIndex);
    }
    return fileName;
  }

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

  private boolean isOutOfBounds(int y, int x) {
    return y < 0 || x < 0 || x > this.width || y > this.height;
  }


  public RGBA getPixelAt(int y, int x) throws IllegalArgumentException {
    if (isOutOfBounds(y, x)) {
      throw new IllegalArgumentException("invalid pixel");
    }
    return this.pixels[y][x].copy();
  }

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
      } catch (IOException e) {
        throw new IllegalArgumentException("Error saving file: " + e.getMessage());
      }
    }
  }
