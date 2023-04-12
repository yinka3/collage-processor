package cs3500.filter;

import cs3500.imageutil.IRGBA;
import cs3500.imageutil.RGBA;

/**
 * Applies a red filter to a single pixel. Returns a new pixel with the same red value and
 * alpha value as the original and new blue and green values of 0.
 * Implements the IFilter interface.
 */
public class FilterRed implements IFilter {

  public IRGBA apply(IRGBA input) {
    return new RGBA(input.getRed(), 0, 0 , input.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.FilterRed";
  }
}
