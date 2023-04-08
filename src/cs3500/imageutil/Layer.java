package cs3500.imageutil;

import cs3500.filter.FilterNormal;
import cs3500.filter.IBlending;
import cs3500.filter.IFilter;

/**
 * The layer class is essentially where the 2D array of pixels gets modified based on the
 * programmer's needs like filtering and such.
 *
 */
public class Layer {

  private final RGBA[][] rgba2;

  private final int height;

  private final int width;

  private final double alpha;

  private IFilter filter;

  private IBlending blender;

  private final String name;


  /**
   * The constructor of the Layer that has all the necessary contents for a layer
   * to do different things.
   * @param name String value of the layer name being modified.
   * @param height integer of the layer height being modified.
   * @param width integer of the layer width being modified.
   */
  public Layer(String name, int height, int width) {
    this.name = name;
    this.height = height;
    this.width = width;
    this.rgba2 = new RGBA[this.height][this.width];
    this.alpha = 0;
    this.filter = new FilterNormal();
    for (int y = 0; y < this.height; y++) {
      for (int x = 0; x < this.width; x++) {
        this.rgba2[y][x] = new RGBA(255, 255, 255, 0);
      }
    }
  }

  /**
   * get the layer name.
   * @return String name of the layer.
   */
  public String getName() {
    return this.name;
  }

  /**
   * get the alpha component of the layer.
   * @return a double value of the layer alpha.
   */
  public double getAlpha() {
    return alpha;
  }

  /**
   * The method is being setter method for 2D array of pixels by copying
   * the pixels of the layer.
   * @param pixels the 2D array of pixels that are being copied.
   */
  public void setRGBA(RGBA[][] pixels) {
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        this.rgba2[i][j] = pixels[i][j].copy();
      }
    }
  }

  /**
   * This is a setter method to set the filter for the layer.
   * @param filter the filter to be set.
   */
  public void setFilter(IFilter filter) {
    this.filter = filter;
  }

  /**
   * This is a getter method to get the filter for the layer.
   * @return the filter for the layer.
   */
  public IFilter getFilter() {
    return filter;
  }

  /**
   * This the modified getter method to get the filter and be applied onto layer.
   * @param filtered the 2D array of pixel to be set onto layer filtered.
   * @return a new 2D array onto the layer that are filtered.
   */
  public RGBA[][] getFilter(RGBA[][] filtered) {
    for (int j = 0; j < this.height; j++) {
      for (int i = 0; i < this.width; i++) {
        filtered[j][i] = this.filter.apply(filtered[j][i]);
      }
    }
    return filtered;
  }

  /**
   * This is the getter method to get the blending filter to applied onto layer.
   * @return a IBlender object that modifies layers.
   */
  public IBlending getBlend() {
    return this.blender;
  }

  /**
   * This the setter method to set a blending filter for the layer.
   * @param filter a IBlender object that gets set on the layer.
   */
  public void setBlend(IBlending filter) {
    this.blender = filter;
  }

  /**
   * This is a helper methods which copies the contents of a 2D array of pixels.
   * @return a 2D array of pixels that are copies.
   */
  public RGBA[][] getNewRgba2() {
    RGBA[][] temp = new RGBA[this.height][this.width];
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        temp[i][j] = this.rgba2[i][j].copy();
      }
    }
    return temp;
  }

  /**
   * This is used to render out a visual of the filtered 2D array of pixels of the layer.
   * @return a filtered 2D array of pixels of the layer
   */
  public RGBA[][] visualize() {
    return this.getFilter(this.getNewRgba2());
  }

  /**
   * Helper method for main model method that can read in ppm image and gets a possible
   * offset positions.
   * @param imgName String of the ppm image name.
   * @param yOffset integer of the offset position of the y coordinate.
   * @param xOffset integer of the offset position of the x coordinate.
   */
  public void addImage(PPMUtil imgName, int yOffset, int xOffset) {
    if (imgName == null) {
      throw new IllegalArgumentException("no image is here");
    }

    if (yOffset < 0 || yOffset >= this.height || xOffset < 0 || xOffset >= this.width) {
      throw new IllegalArgumentException("Invalid man");
    }

    for (int y = yOffset; y < Math.min(imgName.getHeight() + yOffset, this.height); y++) {
      for (int x = xOffset; x < Math.min(imgName.getWidth() + xOffset, this.width) ; x++) {
        RGBA clone = imgName.getPixelAt(y - yOffset, x - xOffset).copy();
        this.rgba2[y][x] = clone;
      }
    }
  }
}
