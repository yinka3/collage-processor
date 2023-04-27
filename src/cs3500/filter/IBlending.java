package cs3500.filter;

import cs3500.imageutil.IRGBA;


/**
 * An interface for blending filters. Blending filters use RGBAs from multiple layers to apply the
 * filter.
 * This interface will be used through the layer class.
 */
public interface IBlending {

  /**
   * Applies the blending filter to two RGBAs.
   * @param rgba1 first RGBA being used.
   * @param rgba2 second RGBA being used.
   * @return a new RGBA with filter applied.
   */
  IRGBA apply(IRGBA rgba1, IRGBA rgba2);

  /**
   * Overrides the to String to create a representation of a blend filter.
   * @return a string representation of the filter.
   */
  @Override
  String toString();
}
