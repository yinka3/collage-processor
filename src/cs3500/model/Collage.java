package cs3500.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import cs3500.Filter.IFilter;
import cs3500.ImageUtil.Layer;
import cs3500.ImageUtil.PPMUtil;
import cs3500.ImageUtil.RGBA;


public class Collage implements ICollage {


  private HashMap<String, Layer> knownImages;

  private int height;

  private int width;

  private boolean isStarted;

  public Collage() {
    this.isStarted = false;
  }


  public void createProject(int height, int width) {
    if (this.isStarted) {
      throw new IllegalStateException("project has already started");
    }
    this.height = height;
    this.width = width;
    this.knownImages = new HashMap<>();
    this.isStarted = true;
  }

  public int getHeight() {
    return height;
  }

  public int getWidth() {
    return width;
  }


  public RGBA[][] applyTrans(RGBA[][] curr, RGBA[][] bg) {
    RGBA[][] finalImage = new RGBA[this.height][this.width];
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        finalImage[j][i] = curr[j][i].transparency(bg[j][i]);
      }
    }
    return finalImage;
  }


  public void addLayer(String layer) throws IllegalArgumentException, IllegalStateException {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }

    if (this.knownImages.get(layer) == null) {
      this.knownImages.put(layer, new Layer(layer, this.height, this.width));
    } else {
      throw new IllegalArgumentException("layer name already exists");
    }
  }


  @Override
  public void addImageToLayer(String LayerName, String imgName, int yOffset, int xOffset) throws IllegalArgumentException {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }

    this.knownImages.get(LayerName).addImageToLayer(new PPMUtil(imgName), yOffset, xOffset);
  }


 public RGBA[][] finalPixel() {
   if (!this.isStarted) {
     throw new IllegalStateException("project has not started");
   }
   RGBA[][] finalImage = new RGBA[this.height][this.width];
   for (int i = 0; i < this.height; i++) {
     for (int j = 0; j < this.width; j++) {
       finalImage[i][j] = new RGBA(255, 255, 255, 0);
     }
   }
   for (Layer layer : this.knownImages.values()) {
     finalImage = this.applyTrans(layer.visualize(), finalImage);
   }
   return finalImage;
 }


  public void savePPMImage(String filepath) {
    new PPMUtil(this.finalPixel(), filepath, this.height, this.width).savePPM(filepath);
  }

  public void setFilter(String currentLayer, IFilter filterName) {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }
    this.knownImages.get(currentLayer).updateFilter(filterName);
  }


  public void saveProject(String filepath) {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }
    try {
      File file = new File(filepath);
      PrintWriter writer = new PrintWriter(file);

      writer.println("C1");
      writer.print(this.height);
      writer.println(this.width);

      for (Layer layer : this.knownImages.values()) {
        writer.print(layer.getName());
        writer.print(layer.getAlpha());
        writer.println(layer.filter.toString());
        RGBA[][] pixels = layer.visualize();
        for (RGBA[] row : pixels) {
          for (RGBA pixel : row) {
            writer.print(pixel.getRed());
            writer.print(pixel.getGreen());
            writer.print(pixel.getBlue());
            writer.print(pixel.getAlpha());
          }
        }
      }
      writer.close();
    } catch (FileNotFoundException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public void loadProject(String filepath) {
    try {
      File file = new File(filepath);
      Scanner scan = new Scanner(file);
      int height = scan.nextInt();
      int width = scan.nextInt();
      Collage project = new Collage();
      project.createProject(height, width);
      while (scan.hasNextLine()) {
        String layerName = scan.nextLine();
        double alpha = scan.nextInt();
        RGBA[][] pixels = new RGBA[height][width];
        for (int i = 0; i < height; i++) {
          for (int j = 0; j < width; j++) {
            int red = scan.nextInt();
            int green = scan.nextInt();
            int blue = scan.nextInt();
            int alphaValue = scan.nextInt();
            pixels[i][j] = new RGBA(red, green, blue, alphaValue);
          }
        }
        Layer l1 = new Layer(layerName, height, width);
        l1.setPixels(pixels);
        project.knownImages.put(layerName, l1);
        project.knownImages.get(layerName).applyAlpha(alpha);
      }
      scan.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}



