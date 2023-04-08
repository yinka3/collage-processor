package cs3500.filter;

import cs3500.imageutil.RGBA;

/**
 * Applies a green filter to a single pixel. Returns a new pixel with the same green value and
 * alpha value as the original and new blue and red values of 0.
 * Implements the IFilter interface.
 */
public class FilterGreen implements IFilter {

  @Override
  public RGBA apply(RGBA rgba) {
    return new RGBA(0, rgba.getGreen(), 0, rgba.getAlpha());
  }

  @Override
  public String toString() {
    return "Filter.FilterGreen";
  }
}



