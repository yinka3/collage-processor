package cs3500.imageutil;

import cs3500.filter.IBlending;
import cs3500.filter.IFilter;

/**
 * The Layer interface allows for the modification of the 2D representation of pixels. This then
 * builds onto the college.
 */
public interface ILayer {

  /**
   * get the layer name.
   * @return String name of the layer.
   */
  String getName();

  /**
   * get the alpha component of the layer.
   * @return a double value of the layer alpha.
   */
  double getAlpha();

  /**
   * The method is being setter method for 2D array of pixels by copying
   * the pixels of the layer.
   * @param pixels the 2D array of pixels that are being copied.
   */
  void setRGBA(IRGBA[][] pixels);

  /**
   * This is a setter method to set the filter for the layer.
   * @param filter the filter to be set.
   */
  void setFilter(IFilter filter);

  /**
   * This the modified getter method to get the filter and be applied onto layer.
   * @param filtered the 2D array of pixel to be set onto layer filtered.
   * @return a new 2D array onto the layer that are filtered.
   */
  IRGBA[][] applyFilter(IRGBA[][] filtered);

  /**
   * This the setter method to set a blending filter for the layer.
   * @param filter a IBlender object that gets set on the layer.
   */
  void setBlend(IBlending filter);

  /**
   * This is the getter method to get the blending filter to applied onto layer.
   * @return a IBlender object that modifies layers.
   */
  IBlending getBlend();

  /**
   * This is a getter method to get the filter for the layer.
   * @return the filter for the layer.
   */
  IFilter getFilter();

  /**
   * This is used to render a visual of the filtered 2D array of pixels of the layer.
   * @return a filtered 2D array of pixels of the layer
   */
  IRGBA[][] visualize();

  /**
   * Helper method for main model method that can read in an image and gets a possible
   * offset positions.
   * @param imgName String of the image name.
   * @param yOffset integer of the offset position of the y coordinate.
   * @param xOffset integer of the offset position of the x coordinate.
   */
  void addImage(IImageUtil imgName, int yOffset, int xOffset);

}
