package cs3500.filter;

import cs3500.imageutil.RGBA;
import cs3500.imageutil.IRGBA;

/**
 * Applies a blue filter to a single pixel. Returns a new pixel with the same blue value and
 * alpha value as the original and new red and green values of 0.
 * This implements the IFilter interface.
 */
public class FilterBlue implements IFilter {
  @Override
  public IRGBA apply(IRGBA rgba) {
    return new RGBA(0, 0, rgba.getBlue(), rgba.getAlpha());
  }


  @Override
  public String toString() {
    return "Filter.FilterBlue";
  }
}
