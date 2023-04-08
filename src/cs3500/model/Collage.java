package cs3500.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import cs3500.filter.IBlending;
import cs3500.filter.IFilter;
import cs3500.imageutil.Layer;
import cs3500.imageutil.PPMUtil;
import cs3500.imageutil.RGBA;


/**
 * This class is used for the functionality of a collage processor that takes in all the layers
 * and do the main processing of the layers.
 * This class implements the ICollage interface.
 */
public class Collage implements ICollage {


  private Map<String, Layer> knownImages;

  private int height;

  private int width;

  private boolean isStarted;

  /**
   * An empty constructor where the collage project has not started yet.
   */
  public Collage() {
    this.isStarted = false;
  }


  /**
   * This initializes the collage project to be started and creates an empty collage with
   * a specified height and width.
   *
   * @param height is the integer height of the collage.
   * @param width  is the integer width of the collage.
   */
  public void createProject(int height, int width) {
    if (this.isStarted) {
      throw new IllegalStateException("project has already started");
    }
    this.height = height;
    this.width = width;
    this.knownImages = new LinkedHashMap<>();
    this.isStarted = true;
  }

  /**
   * This is a getter method for the height of the collage.
   *
   * @return the height of the collage.
   */
  public int getHeight() {
    return height;
  }

  /**
   * This is a getter method for the width of the collage.
   *
   * @return the width of the collage.
   */
  public int getWidth() {
    return width;
  }

  /**
   * This is the transparency method for the collage that allows for the images to be combined,
   * however it also allows for a possibility for blending filter to be applied.
   *
   * @param curr  is the 2D array of pixels of the current layer.
   * @param bg    is the 2D array of pixels of the background layer.
   * @param blend is the blending filter to be used on the combined layers.
   * @return a 2D array of pixels of combined layers.
   */
  private RGBA[][] applyTrans(RGBA[][] curr, RGBA[][] bg, IBlending blend) {
    RGBA[][] finalImage = new RGBA[this.height][this.width];
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        if (blend != null) {
          finalImage[j][i] = blend.apply(curr[j][i], bg[j][i]);
        } else {
          finalImage[j][i] = curr[j][i].transparency(bg[j][i]);
        }
      }
    }
    return finalImage;
  }


  /**
   * This method adds a new empty layer to the collage.
   *
   * @param layer is the String name of the layer.
   * @throws IllegalArgumentException if the layer name already exists in the collage.
   * @throws IllegalStateException    if you add a layer before starting a project.
   */
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


  /**
   * Adds an image to a layer at specified offset cartesian coordinates in the layer.
   *
   * @param layerName the layer being added to.
   * @param imgName   image being added.
   * @param yOffset   y placement of the image onto the layer.
   * @param xOffset   x placement of the image onto the layer.
   * @throws IllegalArgumentException if the project has not started before placing an image.
   */
  @Override
  public void addImageToLayer(String layerName, String imgName, int yOffset, int xOffset)
          throws IllegalArgumentException {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }

    this.knownImages.get(layerName).addImage(new PPMUtil(imgName), yOffset, xOffset);
  }

  /**
   * Returns the final RGBAs that make up a layer with commands applied.
   *
   * @return a 2d array of RGBAs that make up a composite image of all the layers.
   */
  public RGBA[][] finalPixel() {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }
    RGBA[][] finalImage = new RGBA[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        finalImage[i][j] = new RGBA(0, 0, 0, 0);
      }
    }
    for (Layer layer : this.knownImages.values()) {
      finalImage = this.applyTrans(layer.visualize(), finalImage, layer.getBlend());
    }
    return finalImage;
  }


  public void savePPMImage(String filepath) {
    new PPMUtil(this.finalPixel(), filepath, this.height, this.width).savePPM(filepath);
  }

  /**
   * Sets a filter option to the given layer based on the layer name.
   *
   * @param currentLayer the layer the filter is being added to.
   * @param filterName   the filter being added.
   */
  public void setFilter(String currentLayer, IFilter filterName) {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }
    this.knownImages.get(currentLayer).setFilter(filterName);
  }

  /**
   * Sets a blend filter to all the layers below from a given layer name.
   *
   * @param currentLayer name of the layer the blend is applied to.
   * @param blend        the blend being applied.
   */
  public void setBlend(String currentLayer, IBlending blend) {
    if (!this.isStarted) {
      throw new IllegalStateException("project has not started");
    }
    this.knownImages.get(currentLayer).setBlend(blend);
  }

  /**
   * Saves a project to a given filepath.
   *
   * @param filepath the filepath the project will be saved to.
   */
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
        writer.println(layer.getFilter().toString());
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
        l1.setRGBA(pixels);
        project.knownImages.put(layerName, l1);
      }
      scan.close();
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Returns a map of strings to layers of the layers in the project.
   *
   * @return a string key to layer value hashmap.
   */
  public Map<String, Layer> getKnownImages() {
    Map<String, Layer> copy = new HashMap<>();
    Set<Map.Entry<String, Layer>> entries = knownImages.entrySet();

    for (Map.Entry<String, Layer> mapEntry : entries) {
      copy.put(mapEntry.getKey(), mapEntry.getValue());
    }
    return copy;
  }
}
